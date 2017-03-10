package com.xiaoan.wlt.common;

import com.xiaoan.wlt.utils.JsonUtil;

/**
 * 发送的消息
 * @author liangjiahong
 *
 */
public class ImMsg {
//	json.addProperty("username","系统");
//	json.addProperty("avatar", "");
//	json.addProperty("id",-1);
//	json.addProperty("type", "friend");
//	json.addProperty("content", "我只是个系统消息！");
//	json.addProperty("mine", false);
//	json.addProperty("timestamp", System.currentTimeMillis());
	private String username;
	private String avatar;//图片完整路径
	private String id;
	private String type;
	private String content;
	private Boolean mine;
	private Long timestamp = System.currentTimeMillis();
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Boolean getMine() {
		return mine;
	}
	public void setMine(Boolean mine) {
		this.mine = mine;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public String toJsonString(){
		return JsonUtil.toJson(this);
	}
}