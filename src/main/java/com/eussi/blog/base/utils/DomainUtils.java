package com.eussi.blog.base.utils;

import com.eussi.blog.base.lang.Consts;
import com.eussi.blog.modules.po.Post;
import com.eussi.blog.modules.vo.UserVO;

/**
 * Created by wangxueming on 2019/2/19.
 */
public class DomainUtils {
    public static void fillZero(Post post) {
        //将需要设置为0的值填充
        if(post.getComments()==null)
            post.setComments(Consts.ZERO);
        if(post.getFavors()==null)
            post.setFavors(Consts.ZERO);
        if(post.getFeatured()==null)
            post.setFeatured(Consts.ZERO);
        if(post.getViews()==null)
            post.setViews(Consts.ZERO);
        if(post.getWeight()==null)
            post.setWeight(Consts.ZERO);
    }

    public static void fillZero(UserVO post) {
        if(post.getComments()==null)
            post.setComments(Consts.ZERO);
        if(post.getPosts()==null)
            post.setPosts(Consts.ZERO);
    }
}
