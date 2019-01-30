package com.eussi.blog.modules.po;

import com.eussi.blog.base.modules.BaseDomain;

import java.util.Date;

/**
 * 评论
 * @author wangxm
 *
 */
public class Comment extends BaseDomain {
	private long id;

	/**
	 * 所属内容ID
	 */
	private long toId;

	/**
	 * 父评论ID
	 */
	private long pid;

	/**
	 * 评论内容
	 */
	private String content;
	
	private Date created;
	
	private long authorId;
	
	private int status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getToId() {
		return toId;
	}

	public void setToId(long toId) {
		this.toId = toId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

}
