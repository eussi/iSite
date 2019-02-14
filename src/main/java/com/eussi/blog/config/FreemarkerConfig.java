package com.eussi.blog.config;


import com.eussi.blog.modules.freemarker.directive.ContentsDirective;
import com.eussi.blog.modules.freemarker.directive.ControlsDirective;
import com.eussi.blog.modules.freemarker.directive.ResourceDirective;
import com.eussi.blog.modules.freemarker.method.TimeAgoMethod;
import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by wangxm on 2019/1/31.
 */
@Component
public class FreemarkerConfig {
    @Autowired
    private Configuration configuration;
    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void setSharedVariable() throws TemplateModelException {
        configuration.setSharedVariable("contents", applicationContext.getBean(ContentsDirective.class));
        configuration.setSharedVariable("controls", applicationContext.getBean(ControlsDirective.class));
        configuration.setSharedVariable("resource", applicationContext.getBean(ResourceDirective.class));

        configuration.setSharedVariable("timeAgo", new TimeAgoMethod());
    }
}
