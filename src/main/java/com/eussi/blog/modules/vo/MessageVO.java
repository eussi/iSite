package com.eussi.blog.modules.vo;


import com.eussi.blog.modules.po.Message;

/**
 * @author wangxm on 2019/2/15.
 */
public class MessageVO extends Message {
    // extend
    private UserVO from;
    private PostVO post;

    public UserVO getFrom() {
        return from;
    }

    public void setFrom(UserVO from) {
        this.from = from;
    }

    public PostVO getPost() {
        return post;
    }

    public void setPost(PostVO post) {
        this.post = post;
    }
}
