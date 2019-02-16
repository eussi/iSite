package com.eussi.blog.config;

import com.eussi.blog.base.lang.Consts;
import com.eussi.blog.modules.po.Options;
import com.eussi.blog.modules.service.ChannelService;
import com.eussi.blog.modules.service.OptionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 加载配置信息到系统
 *
 */
@Component
@Slf4j
public class ContextStartup implements ApplicationRunner, Ordered, ServletContextAware {
    @Autowired
    private OptionsService optionsService;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private SiteOptions siteOptions;

    private ServletContext servletContext;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        Timer timer = new Timer("startup");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(log.isInfoEnabled()) {
                    log.info("initialization ...");
                }

                resetSetting(true);
                resetSiteConfig();
                resetChannels();

                if(log.isInfoEnabled()) {
                    log.info("OK, completed");
                }
            }
        }, 1 * Consts.TIME_MIN);
    }

    @Override
    public int getOrder() {
        return 2;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    /**
     * 重置站点配置
     */
    public void resetSetting(boolean exit) {
        List<Options> options = optionsService.findAll();

        if (null == options || options.isEmpty()) {
            try {
                Resource resource = new ClassPathResource("/config/db/db_mblog.sql");
                optionsService.initSettings(resource);
            } catch (Exception e) {
                if(log.isErrorEnabled()) {
                    log.error("------------------------------------------------------------");
                    log.error("-  ERROR:The SQL file is not imported. (sql/db_mblog.sql)  -");
                    log.error("-         Please import the SQL file and try again.        -");
                    log.error("------------------------------------------------------------");
                    log.error(e.getMessage(), e);
                }

                if (exit) {
                    System.exit(1);
                }
            }
        } else {
            options.forEach(conf -> {
                siteOptions.getOptions().put(conf.getKey(), conf.getValue());
                servletContext.setAttribute(conf.getKey(), conf.getValue());
            });

            servletContext.setAttribute("options", siteOptions.getOptions());
        }
    }

    /**
     * 重置栏目缓存
     */
    public void resetChannels() {
        servletContext.setAttribute("channels", channelService.findAll(Consts.STATUS_NORMAL));
    }

    public void resetSiteConfig() {
        servletContext.setAttribute("site", siteOptions);
    }
}
