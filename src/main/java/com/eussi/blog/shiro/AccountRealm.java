package com.eussi.blog.shiro;

import com.eussi.blog.base.lang.Consts;
import com.eussi.blog.modules.po.Permission;
import com.eussi.blog.modules.po.Role;
import com.eussi.blog.modules.service.UserRoleService;
import com.eussi.blog.modules.service.UserService;
import com.eussi.blog.modules.vo.AccountProfile;
import com.eussi.blog.modules.vo.UserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by wangxm on 2019/1/31.
 */
public class AccountRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    public AccountRealm() {
        super(new AllowAllCredentialsMatcher());
        setAuthenticationTokenClass(UsernamePasswordToken.class);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        AccountProfile profile = (AccountProfile) SecurityUtils.getSubject().getPrincipal();
        if (profile != null) {
            UserVO user = userService.get(profile.getId());
            if (user != null) {
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                List<Role> roles = userRoleService.listRoles(user.getId());

                //赋予角色
                for(Role role : roles){
                    info.addRole(role.getName());
                    //赋予权限
                    if(role.getPermissions()!=null) {//新添加用户可能权限为null，登录会报错
                        for(Permission permission : role.getPermissions()) {
                            info.addStringPermission(permission.getName());
                        }
                    }
                }
                return info;
            }
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //认证步骤
        AccountProfile profile = getAccount(userService, token);

        if (profile.getStatus() == Consts.STATUS_CLOSED) {
            throw new LockedAccountException(profile.getName());
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(profile, token.getCredentials(), getName());

        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("profile", profile);

        return info;
    }

    protected AccountProfile getAccount(UserService userService, AuthenticationToken token) {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        return userService.login(upToken.getUsername(), String.valueOf(upToken.getPassword()));
    }
}
