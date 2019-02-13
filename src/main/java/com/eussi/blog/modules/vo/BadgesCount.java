package com.eussi.blog.modules.vo;

import com.eussi.blog.base.modules.BaseDomain;

import java.io.Serializable;

/**
 * @author wangxm on 2019/1/3.
 */
public class BadgesCount extends BaseDomain implements Serializable {
    private static final long serialVersionUID = 8276459939240769498L;

    private int messages; // 消息数量

    public int getMessages() {
        return messages;
    }

    public void setMessages(int messages) {
        this.messages = messages;
    }
}
