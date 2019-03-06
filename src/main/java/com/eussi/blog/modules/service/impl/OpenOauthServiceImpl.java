package com.eussi.blog.modules.service.impl;

import com.eussi.blog.base.utils.MD5;
import com.eussi.blog.modules.dao.UserMapper;
import com.eussi.blog.modules.dao.UserOauthMapper;
import com.eussi.blog.modules.po.User;
import com.eussi.blog.modules.po.UserOauth;
import com.eussi.blog.modules.service.OpenOauthService;
import com.eussi.blog.modules.utils.BeanMapUtils;
import com.eussi.blog.modules.vo.OpenOauthVO;
import com.eussi.blog.modules.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 第三方登录授权管理
 * @author wangxm on 2019/3/6.
 */
@Service
@Transactional
public class OpenOauthServiceImpl implements OpenOauthService {
    @Autowired
    private UserOauthMapper userOauthMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserVO getUserByOauthToken(String oauth_token) {
        UserOauth thirdToken = userOauthMapper.findByAccessToken(oauth_token);
        User po = userMapper.selectByPrimaryKey(thirdToken.getId());
        return BeanMapUtils.copy(po);
    }

    @Override
    public OpenOauthVO getOauthByToken(String oauth_token) {
        UserOauth po = userOauthMapper.findByAccessToken(oauth_token);
        OpenOauthVO vo = null;
        if (po != null) {
            vo = new OpenOauthVO();
            BeanUtils.copyProperties(po, vo);
        }
        return vo;
    }

    @Override
    public OpenOauthVO getOauthByUid(long userId) {
        UserOauth po = userOauthMapper.findByUserId(userId);
        OpenOauthVO vo = null;
        if (po != null) {
            vo = new OpenOauthVO();
            BeanUtils.copyProperties(po, vo);
        }
        return vo;
    }

    @Override
    public boolean checkIsOriginalPassword(long userId) {
        UserOauth po = userOauthMapper.findByUserId(userId);
        if (po != null) {
            User user = userMapper.selectByPrimaryKey(userId);

            String pwd = MD5.md5(po.getAccessToken());
            // 判断用户密码 和 登录状态
            if (user!=null && pwd.equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void saveOauthToken(OpenOauthVO oauth) {
        UserOauth po = new UserOauth();
        BeanUtils.copyProperties(oauth, po);
        userOauthMapper.insert(po);
    }

	@Override
	public OpenOauthVO getOauthByOauthUserId(String oauthUserId) {
		UserOauth po = userOauthMapper.findByOauthUserId(oauthUserId);
        OpenOauthVO vo = null;
        if (po != null) {
            vo = new OpenOauthVO();
            BeanUtils.copyProperties(po, vo);
        }
        return vo;
	}

}
