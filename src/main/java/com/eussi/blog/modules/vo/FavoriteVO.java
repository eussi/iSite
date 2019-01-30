package com.eussi.blog.modules.vo;


import com.eussi.blog.modules.po.Favorite;

/**
 * @author wagnxm on 2019/1/30.
 */
public class FavoriteVO extends Favorite {
    // extend
    private PostVO post;

    public PostVO getPost() {
        return post;
    }

    public void setPost(PostVO post) {
        this.post = post;
    }
}
