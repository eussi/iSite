package com.eussi.blog.modules.po;

import com.eussi.blog.base.modules.BaseDomain;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 *
 * @author wangxm
 *
 */
public class User extends BaseDomain implements Serializable {
	private static final long serialVersionUID = -3629784071225214858L;

	private long id;

	private String username; // 用户名

	private String password; // 密码

	private String avatar;  // 头像

	private String name;  // 昵称

	private int gender;   // 性别

	private String email;  // 邮箱

	private int posts; // 文章数

	private int comments; // 发布评论数

	private Date created;  // 注册时间

	private Date lastLogin;

	private String signature; // 个性签名

	private int status; // 用户状态

	public User() {

	}

	public User(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getPosts() {
		return posts;
	}

	public void setPosts(int posts) {
		this.posts = posts;
	}

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
}
