package com.eussi.blog.modules.service.impl;

import com.eussi.blog.base.exception.BlogException;
import com.eussi.blog.base.lang.Consts;
import com.eussi.blog.base.lang.EntityStatus;
import com.eussi.blog.base.modules.Page;
import com.eussi.blog.base.utils.CommonUtils;
import com.eussi.blog.base.utils.MD5;
import com.eussi.blog.modules.dao.UserMapper;
import com.eussi.blog.modules.po.User;
import com.eussi.blog.modules.service.MessageService;
import com.eussi.blog.modules.service.UserService;
import com.eussi.blog.modules.utils.BeanMapUtils;
import com.eussi.blog.modules.vo.AccountProfile;
import com.eussi.blog.modules.vo.BadgesCount;
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
@Transactional(readOnly = false)
@CacheConfig(cacheNames = "usersCaches")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MessageService messageService;

    @Override
    public Page<UserVO> paging(Page page, String name) {
        return null;
    }

    @Override
    public Map<Long, UserVO> findMapByIds(Set<Long> ids) {
        User user = new User();
        user.setIsIn(CommonUtils.concatInQuery("id", ids, Consts.IN));

        List<User> list = userMapper.findAllByQuery(user);

        Map<Long, UserVO> rets = new HashMap<Long, UserVO>();
        for(User u : list) {
            UserVO uservo = BeanMapUtils.copy(u);
            rets.put(u.getId(), uservo);
        }
        return rets;
    }

    @Override
    public AccountProfile login(String username, String password) {
        User po = userMapper.findByUsername(username);
        AccountProfile u = null;

        Assert.notNull(po, "账户不存在");

//		Assert.state(po.getStatus() != Const.STATUS_CLOSED, "您的账户已被封禁");

        Assert.state(StringUtils.equals(po.getPassword(), password), "密码错误");

        po.setLastLogin(Calendar.getInstance().getTime());
        userMapper.updateByPrimaryKeySelective(po);
        u = BeanMapUtils.copyPassport(po);

        BadgesCount badgesCount = new BadgesCount();
        badgesCount.setMessages(messageService.unread4Me(u.getId()));

        u.setBadgesCount(badgesCount);
        return u;

    }

    @Override
    public AccountProfile findProfile(String username) {
        return null;
    }

    @Override
    public UserVO register(UserVO user) {
        Assert.notNull(user, "Parameter user can not be null!");

        Assert.hasLength(user.getUsername(), "用户名不能为空!");
        Assert.hasLength(user.getPassword(), "密码不能为空!");

        User check = userMapper.findByUsername(user.getUsername());

        Assert.isNull(check, "用户名已经存在!");

        User po = new User();

        BeanUtils.copyProperties(user, po);

        if (StringUtils.isBlank(po.getName())) {
            po.setName(user.getUsername());
        }

        Date now = Calendar.getInstance().getTime();
        po.setPassword(MD5.md5(user.getPassword()));
        po.setStatus(EntityStatus.ENABLED);
        po.setCreated(now);
        //设置初始角色

        userMapper.insert(po);

        return BeanMapUtils.copy(po);
    }

    @Override
    public AccountProfile update(UserVO user) {
        User po = userMapper.selectByPrimaryKey(user.getId());
        po.setName(user.getName());
        po.setSignature(user.getSignature());

        userMapper.updateByPrimaryKeySelective(po);
        return BeanMapUtils.copyPassport(po);
    }

    @Override
    public AccountProfile updateEmail(long id, String email) {
        User po = userMapper.selectByPrimaryKey(id);

        if (email.equals(po.getEmail())) {
            throw new BlogException("邮箱地址没做更改");
        }

        User queryEmail = new User();
        queryEmail.setEmail(email);
        List<User> check = userMapper.findAllByQuery(queryEmail);

        if (check != null && check.size()>0) {
            throw new BlogException("该邮箱地址已经被使用了");
        }
        po.setEmail(email);
        userMapper.updateByPrimaryKeySelective(po);
        return BeanMapUtils.copyPassport(po);
    }

    @Override
    public UserVO get(long id) {
        User po = userMapper.selectByPrimaryKey(id);
        UserVO userVO = BeanMapUtils.copy(po);
        return userVO;
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
        User po = userMapper.selectByPrimaryKey(id);
        po.setAvatar(path);
        userMapper.updateByPrimaryKeySelective(po);
        return BeanMapUtils.copyPassport(po);
    }

    @Override
    public void updatePassword(long id, String newPassword) {
        User po = userMapper.selectByPrimaryKey(id);

        Assert.hasLength(newPassword, "密码不能为空!");

        po.setPassword(MD5.md5(newPassword));
        userMapper.updateByPrimaryKeySelective(po);
    }

    @Override
    public void updatePassword(long id, String oldPassword, String newPassword) {
        User po = userMapper.selectByPrimaryKey(id);
        Assert.hasLength(newPassword, "密码不能为空!");

        Assert.isTrue(MD5.md5(oldPassword).equals(po.getPassword()), "当前密码不正确");
        po.setPassword(MD5.md5(newPassword));
        userMapper.updateByPrimaryKeySelective(po);
    }

    @Override
    public void updateStatus(long id, int status) {

    }

    @Override
    public long count() {
        return userMapper.getTotalCount(new User());
    }
}
