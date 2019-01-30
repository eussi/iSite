package com.eussi.blog.modules.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.eussi.blog.base.lang.Consts;
import com.eussi.blog.modules.po.Channel;
import com.eussi.blog.modules.po.Post;
import com.eussi.blog.modules.po.PostAttribute;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author wangxm
 * 
 */
public class PostVO extends Post implements Serializable {
	private static final long serialVersionUID = -1144627551517707139L;

	private String content;

	private UserVO author;
	private Channel channel;
	
	@JSONField(serialize = false)
	private PostAttribute attribute;
	
	public String[] getTagsArray() {
		if (StringUtils.isNotBlank(super.getTags())) {
			return super.getTags().split(Consts.SEPARATOR);
		}
		return null;
	}

	public UserVO getAuthor() {
		return author;
	}

	public void setAuthor(UserVO author) {
		this.author = author;
	}

	public PostAttribute getAttribute() {
		return attribute;
	}

	public void setAttribute(PostAttribute attribute) {
		this.attribute = attribute;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
}
