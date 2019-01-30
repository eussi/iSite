package com.eussi.blog.modules.po;

import com.eussi.blog.base.modules.BaseDomain;

import java.util.Date;

/**
 * 喜欢/收藏
 * @author wangxm
 *
 */
public class Favorite extends BaseDomain {
    private long id;

    /**
     * 所属用户
     */
    private long ownId;

    /**
     * 内容ID
     */
    private long postId;

    private Date created;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOwnId() {
        return ownId;
    }

    public void setOwnId(long ownId) {
        this.ownId = ownId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
