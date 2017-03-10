package com.xiaoan.wlt.controller.admin.system;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;

import com.jfinal.plugin.activerecord.Page;
import com.xiaoan.wlt.controller.BaseController;
import com.xiaoan.wlt.model.sys.Role;
import com.xiaoan.wlt.model.sys.RoleUser;
import com.xiaoan.wlt.model.sys.User;
import com.xiaoan.wlt.shiro.UserContext;
import com.xiaoan.wlt.utils.MD5Util;

/**
 * 
 * @author liangjiahong
 * @date 2016年10月25日
 */
@RequiresUser
public class UserController extends BaseController{
	
	private static final String USERINFO = "/admin/system/user/userInfo.jsp";
	private static final String EDITPAW = "/admin/system/user/editPaw.jsp";
	private static final String LIST = "/admin/system/user/list.jsp"; 
	private static final String EDIT = "/admin/system/user/edit.jsp";
	
	/**
	 * userList
	 */
	@RequiresPermissions("SYS_USER")
	public void list(){
		User user = getModel(User.class);
		Integer pageNumber = getParaToInt("pageNumber");
		if (pageNumber != null) {
			user.setPageNumber(pageNumber);
		}
		Page<User> page = User.dao.findPageList(user);
		this.setAttr("user",user);
		this.setAttr("page",page);
		this.render(LIST);
	}

	@RequiresPermissions("SYS_USER_DEL")
	public void del() {
		
	}

	@RequiresPermissions("SYS_USER_ADD")
	public void save() {
		System.out.println("=======save====");
		this.setAttr("msg","save成功");
		User user = getModel(User.class);
		user.getUserCode();
		try {
			user.setPwd(MD5Util.getMD5String("888888"));
			user.save();
			list();
		} catch (Exception e) {
			e.printStackTrace();
			setAttr("user",user);
			setAttrMsg(e.getMessage());
			edit();
		}
	}

	@RequiresPermissions("SYS_USER_ADD")
	public void update() {
		System.out.println("=======update====");
		User user = getModel(User.class);
		try {
			user.update();
			setAttrMsg("更新成功");
		} catch (Exception e) {
			e.printStackTrace();
			setAttr("user",user);
			setErrMsg(e.getMessage());
			edit();
		}
		list();
	}

	@RequiresPermissions("SYS_USER_ADD")
	public void edit() {
		String userId = this.getPara("id");
		if(NumberUtils.isNumber(userId)){
			this.setAttr("act","修改");
			setAttr("user",User.dao.findById(userId));
			setAttr("roleUserList",RoleUser.dao.getUserRoles(userId));
		}else{
			this.setAttr("act","添加");
		}
		this.setAttr("roleList",Role.dao.findList());
		this.render(EDIT);
	}
	
	/**
	 * 个人信息
	 */
	public void userInfo(){
		setAttr("user",User.dao.findByCode(UserContext.getUserCode()));
		this.render(USERINFO);
	}
	public void updateUserInfo(){
		User user = getModel(User.class);
		try {
			user.update();
			setAttrMsg("更新成功");
		} catch (Exception e) {
			e.printStackTrace();
			setAttr("user",user);
			setErrMsg(e.getMessage());
		}
		userInfo();
	}
	/**
	 * 修改密码
	 */
	public void editPaw(){
		this.render(EDITPAW);
	}
	/**
	 * 更新密码
	 */
	public void updatePaw(){
		renderJson();
		String pwd = getPara("pwd");
		String pwd1 = getPara("pwd1");
		String pwd2 = getPara("pwd2");
		if(pwd == null || pwd1 == null || pwd2 == null){
			setAttrMsg("密码不能为空！");
			return;
		}
		if(!pwd1.equals(pwd2)){
			setAttrMsg("两次输入的密码不一致！");
			return;
		}
		
		User user = User.dao.findByCode(UserContext.getUserCode());
		if(!user.getPwd().equals(pwd)){
			setAttrMsg("原密码错误！");
			return;
		}
		
		user.setVersion(user.getVersion()+1);
		user.setPwd(pwd2);
		user.update();
		setAttrMsg("密码更新成功！");
		return;
	}
}
