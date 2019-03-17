package com.eussi.blog.web.interceptor.hook;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PreDestroy;

/**
 * 拦截类钩子支持类
 *
 * @author wangxm 2019/3/18
 */
public abstract class InterceptorHookSupport implements InterceptorHook {
    @Autowired
    protected InterceptorHookManager interceptorHookManager;

    @PreDestroy
    public void destroy() {
        interceptorHookManager.removeInterceptorHook(this);
    }
}
