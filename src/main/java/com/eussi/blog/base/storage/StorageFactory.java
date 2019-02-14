package com.eussi.blog.base.storage;

import com.eussi.blog.base.storage.impl.NativeStorageImpl;
import com.eussi.blog.config.SiteOptions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * created by wangxm
 * on 2019/1/31
 */
@Component
public class StorageFactory {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private SiteOptions siteOptions;

    private Map<String, Storage> fileRepoMap = new HashMap<>();

    @PostConstruct
    public void init() {
        //暂时只支持本地存储
        fileRepoMap.put("native", applicationContext.getBean(NativeStorageImpl.class));
    }

    public Storage get() {
        String scheme = siteOptions.getOptions().get("storage_scheme");
        if (StringUtils.isBlank(scheme)) {
            scheme = "native";
        }
        return fileRepoMap.get(scheme);
    }
}
