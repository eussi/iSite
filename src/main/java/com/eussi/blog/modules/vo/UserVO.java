package com.eussi.blog.modules.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.eussi.blog.base.modules.BaseDomain;
import com.eussi.blog.modules.po.Role;
import com.eussi.blog.modules.po.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserVO extends User implements Serializable {
	private static final long serialVersionUID = 107193816173103116L;

    @JSONField(serialize = false)
	private String password;

	@JSONField(serialize = false)
	private List<Role> roles = new ArrayList<Role>();

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
