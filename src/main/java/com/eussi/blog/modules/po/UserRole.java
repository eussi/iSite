package com.eussi.blog.modules.po;

import com.eussi.blog.base.modules.BaseDomain;

import java.io.Serializable;

/**
 * 用户角色映射表
 *
 * @author wangxm
 *
 */
public class UserRole extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -2908144287976184011L;
	
    private Long id;

	private Long userId;

    private Long roleId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
