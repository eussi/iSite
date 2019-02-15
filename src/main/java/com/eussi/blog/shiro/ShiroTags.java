package com.eussi.blog.shiro;

import com.eussi.blog.shiro.tags.HasPermissionTag;
import freemarker.template.SimpleHash;

/**
 * Shortcut for injecting the tags into Freemarker
 * <p>Usage: cfg.setSharedVeriable("shiro", new ShiroTags());</p>
 */
public class ShiroTags extends SimpleHash {

    public ShiroTags() {
        put("hasPermission", new HasPermissionTag());
    }

}
