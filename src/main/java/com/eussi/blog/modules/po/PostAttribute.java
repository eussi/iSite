package com.eussi.blog.modules.po;

import com.eussi.blog.base.modules.BaseDomain;

import java.io.Serializable;

/**
 * @author wangxm
 *
 */
public class PostAttribute extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 7829351358884064647L;

    private long id;

    /**
     * 内容
     */
    private String content; // 内容

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
