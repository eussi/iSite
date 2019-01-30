package com.eussi.blog.modules.po;


import com.eussi.blog.base.modules.BaseDomain;

/**
 * 系统配置
 * @author wangxm
 *
 */
public class Options extends BaseDomain {
	private long id;
	
	private int type;
	
	private String key;
	
	private String value;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
