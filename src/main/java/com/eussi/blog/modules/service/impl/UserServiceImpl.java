package com.eussi.blog.modules.service.impl;

import com.eussi.blog.base.modules.Page;
import com.eussi.blog.modules.repository.UserRepository;
import com.eussi.blog.modules.service.UserService;
import com.eussi.blog.modules.vo.AccountProfile;
import com.eussi.blog.modules.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;

@Service
@Transactional(readOnly = true)
@CacheConfig(cacheNames = "usersCaches")
public class UserServiceImpl implements UserService {

    @Override
    public Page<UserVO> paging(Page page, String name) {
        return null;
    }

    @Override
    public Map<Long, UserVO> findMapByIds(Set<Long> ids) {
        return null;
    }

    @Override
    public AccountProfile login(String username, String password) {
        return null;
    }

    @Override
    public AccountProfile findProfile(String username) {
        return null;
    }

    @Override
    public UserVO register(UserVO user) {
        return null;
    }

    @Override
    public AccountProfile update(UserVO user) {
        return null;
    }

    @Override
    public AccountProfile updateEmail(long id, String email) {
        return null;
    }

    @Override
    public UserVO get(long id) {
        return null;
    }

    @Override
    public UserVO getByUsername(String username) {
        return null;
    }

    @Override
    public UserVO getByEmail(String email) {
        return null;
    }

    @Override
    public AccountProfile updateAvatar(long id, String path) {
        return null;
    }

    @Override
    public void updatePassword(long id, String newPassword) {

    }

    @Override
    public void updatePassword(long id, String oldPassword, String newPassword) {

    }

    @Override
    public void updateStatus(long id, int status) {

    }

    @Override
    public long count() {
        return 0;
    }
}
