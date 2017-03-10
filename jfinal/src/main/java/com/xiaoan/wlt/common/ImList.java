package com.xiaoan.wlt.common;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


/**
 * im \使用列表
 * @author liangjiahong
 * @date 2016年11月1日
 */
public class ImList {

//	{
//	  "code": 0
//	  ,"msg": ""
//	  ,"data": {
//	    "mine": {  //自己
//	      "username": "纸飞机"
//	      ,"id": "100000"
//	      ,"status": "online"
//	      ,"sign": "在深邃的编码世界，做一枚轻盈的纸飞机"
//	      ,"avatar": "http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg"
//	    }
//	    ,"friend": [{ //朋友
//	      "groupname": "前端码屌"
//	      ,"id": 1
//	      ,"online": 2
//	      ,"list": [{
//	        "username": "贤心"
//	        ,"id": "100001"
//	        ,"avatar": "http://tp1.sinaimg.cn/1571889140/180/40030060651/1"
//	        ,"sign": "这些都是测试数据，实际使用请严格按照该格式返回"
//	      }]
//	    }]
//	  }
//	}
	private JsonObject json = new JsonObject();
	private JsonObject data = new JsonObject();
	private JsonObject mine = new JsonObject();
	private JsonArray friend = new JsonArray();
	private JsonArray friendList = new JsonArray();
	
	public void setJson(String code,String msg) {
		this.json.addProperty("code", code);
		this.json.addProperty("msg", msg);
		this.json.add("data", getData());
	}
	
	/**
	 * 
	 * @param username  名字
	 * @param id 唯一数字id
	 * @param status  online/offline
	 * @param sign  签名
	 * @param avatar 头像
	 */
	public void setMine(String username,String id,String status,String sign,String avatar) {
		this.mine.addProperty("username", username);
		this.mine.addProperty("id", id);
		this.mine.addProperty("status", status);
		this.mine.addProperty("sign", sign);
		this.mine.addProperty("avatar", avatar);
	}
	
	/**
	 * 
	 * @param groupname  分组名字
	 * @param id	分组 唯一数字id
	 * @param online   在线人数，可不传
	 * @param list  朋友信息
	 */
	public void setFriend(String groupname,String id,JsonArray list) {
		JsonObject jo = new JsonObject();
		jo.addProperty("groupname", groupname);
		jo.addProperty("id", id);
		jo.add("list", list);
		this.friend.add(jo);
	}
	
	public void setFriendList(String username,String id,String avatar,String sign) {
		JsonObject jo = new JsonObject();
		jo.addProperty("username", username);
		jo.addProperty("id", id);
		jo.addProperty("avatar", avatar);
		jo.addProperty("sign", sign);
		this.friendList.add(jo);
	}
	
	public JsonObject getJson() {
		return json;
	}
	public JsonObject getData() {
		this.data = new JsonObject();
		this.data.add("mine", getMine());
		this.data.add("friend", getFriend());
		return data;
	}
	public JsonObject getMine() {
		return mine;
	}
	public JsonArray getFriend() {
		return friend;
	}

	public JsonArray getFriendList() {
		return friendList;
	}

}
