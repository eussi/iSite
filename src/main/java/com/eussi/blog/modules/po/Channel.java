package com.eussi.blog.modules.po;

import com.eussi.blog.base.modules.BaseDomain;

import java.io.Serializable;

/**
 * 模块/内容分组
 * @author wangxm
 *
 */
public class Channel extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 2436696690653745208L;

	private int id;

	/**
	 * 名称
	 */
	private String name;

	private String key;

	private int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
