package com.eussi.blog.modules.utils;

import com.eussi.blog.modules.po.Comment;
import com.eussi.blog.modules.po.Favorite;
import com.eussi.blog.modules.po.Post;
import com.eussi.blog.modules.po.User;
import com.eussi.blog.modules.vo.*;
import org.springframework.beans.BeanUtils;

/**
 * @author wangxm
 *
 */
public class BeanMapUtils {
	public static String[] USER_IGNORE = new String[]{"password", "extend", "roles"};

	public static String[] POST_IGNORE_LIST = new String[]{"markdown", "content"};

	public static UserVO copy(User po) {
		if (po == null) {
			return null;
		}
		UserVO ret = new UserVO();
		BeanUtils.copyProperties(po, ret, USER_IGNORE);
		return ret;
	}

	public static AccountProfile copyPassport(User po) {
		AccountProfile passport = new AccountProfile(po.getId(), po.getUsername());
		passport.setName(po.getName());
		passport.setEmail(po.getEmail());
		passport.setAvatar(po.getAvatar());
		passport.setLastLogin(po.getLastLogin());
		passport.setStatus(po.getStatus());
		return passport;
	}

    public static CommentVO copy(Comment po) {
        CommentVO ret = new CommentVO();
        BeanUtils.copyProperties(po, ret);
        return ret;
    }

	public static PostVO copy(Post po) {
		PostVO d = new PostVO();
        BeanUtils.copyProperties(po, d);
		return d;
	}


	public static FavoriteVO copy(Favorite po) {
		FavoriteVO ret = new FavoriteVO();
		BeanUtils.copyProperties(po, ret);
		return ret;
	}

}
