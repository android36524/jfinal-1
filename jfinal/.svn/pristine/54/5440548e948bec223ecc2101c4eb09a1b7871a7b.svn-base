package com.xiaoan.wlt.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresUser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jfinal.kit.PropKit;
import com.xiaoan.wlt.common.ImList;
import com.xiaoan.wlt.common.Response;
import com.xiaoan.wlt.model.sys.Function;
import com.xiaoan.wlt.model.sys.User;
import com.xiaoan.wlt.utils.ShiroUtils;

/**
 * 方面控制器
 * 
 * @author liangjiahong
 * @date 2016年10月29日
 */
@RequiresUser
public class IndexController extends BaseController {

	private static String INDEX = "/admin/index.jsp";
	private static String MAININDEX = "/admin/main-index.jsp";

	/**
	 * 菜单框架
	 */
	public void index() {
		Response pes = Function.dao.findMenuList();
		setAttr("list", pes.getData());
		render(INDEX);
	}

	public void getList() {
		// 获取用户分组和列表
		List<User> userList = User.dao.findAllList();
		ImList im = new ImList();
		String head = PropKit.get("HEAD");
		im.setMine(ShiroUtils.getPrincipalProperty("userName").toString(),
				ShiroUtils.getPrincipalProperty("id").toString(), "online",
				ShiroUtils.getPrincipalProperty("userCode").toString(),
				head + ShiroUtils.getPrincipalProperty("userCode").toString()+".jpg");
		//机器人
		JsonObject jo = new JsonObject();
		jo.addProperty("username", "robot");
		jo.addProperty("id", "-1");
		jo.addProperty("avatar", "../static/img/robot.jpg");
		jo.addProperty("sign", "robot");
		JsonArray ja = new JsonArray();
		ja.add(jo);
		im.setFriend("机器人", "1",ja);
		for (User u : userList) {
			im.setFriendList(u.getUserName(), u.getId() + "", head + u.getUserCode()+".jpg", u.getUserCode());
		}
		im.setFriend("默认分组", "2", im.getFriendList());
		im.setJson("0", "");
		renderJson(im.getJson().toString());
	}

	public void mainIndex() {
		render(MAININDEX);
	}
}