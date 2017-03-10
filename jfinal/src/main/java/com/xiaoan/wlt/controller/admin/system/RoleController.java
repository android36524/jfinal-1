package com.xiaoan.wlt.controller.admin.system;

import java.util.Set;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jfinal.plugin.activerecord.Page;
import com.xiaoan.wlt.common.Response;
import com.xiaoan.wlt.controller.BaseController;
import com.xiaoan.wlt.model.sys.Function;
import com.xiaoan.wlt.model.sys.Role;
import com.xiaoan.wlt.model.sys.RolePerm;
import com.xiaoan.wlt.model.sys.RoleUser;
import com.xiaoan.wlt.model.sys.User;
import com.xiaoan.wlt.utils.Common;

@RequiresUser
public class RoleController extends BaseController {

	private static String LIST = "/admin/system/role/list.jsp";
	private static String EDIT = "/admin/system/role/edit.jsp";
	private static String AUTHORIZATION = "/admin/system/role/authorization.jsp";
	private static String ROLETOUSER = "/admin/system/role/roleToUser.jsp";
	
	
	
	@RequiresPermissions("SYS_ROLE")
	public void list() {
		Role role = this.getModel(Role.class);
		try {
			Integer pageNumber = getParaToInt("pageNumber");
			if (pageNumber != null) {
				role.setPageNumber(pageNumber);
			}
			setPageAttr(Role.dao.findPageList(role));
		} catch (Exception e) {
			e.printStackTrace();
			setAttrErrMsg("系统异常");
			throw e;
		}
		render(LIST);
	}

	@RequiresPermissions("SYS_ROLE_ADD")
	public void edit() {
		Long id = getParaToLong("id");
		if (Common.checkLong(id)) {
			setAttr("act", "修改");
			setAttr("role", Role.dao.findById(id));
		} else {// add
			setAttr("act", "添加");
		}
		render(EDIT);
	}

	@RequiresPermissions("SYS_USER_DEL")
	public void del() {
		String ids = getPara("ids");
		if (Common.checkIds(ids)) {
			try {
				Role.dao.deleteById(ids);
			} catch (Exception e) {
				e.printStackTrace();
				setAttrErrMsg("可能存关联数据！");
			}
		}
		list();
	}

	@RequiresPermissions("SYS_ROLE_ADD")
	public void update() {
		try {
			Role role = getModel(Role.class);
			if (Common.checkLong(role.getId())) {
				role.update();
				setAttrMsg("修改成功");
			} else {
				role.save();
				setAttrMsg("保存成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			setErrMsg("系统异常");
			throw e;
		}
		list();
	}

	public void authorization() {
		try {
			String id = getPara("id");
			if (NumberUtils.isNumber(id)) {
				Role role = Role.dao.findById(id);
				Response re = Function.dao.findTreeList();
				Set<String> set  = RolePerm.dao.findPermByRoleId(id);
				
				JsonArray ja = (JsonArray) re.getData();
				for(JsonElement je : ja){
					JsonObject jo = je.getAsJsonObject();
					if(set.contains(jo.get("id").getAsString())){
						jo.addProperty("checked", true );
					}
				}
				setAttr("json", ja);
				setAttr("role", role);
			}
		} catch (Exception e) {
			e.printStackTrace();
			setSysErr();
			list();
		}
		render(AUTHORIZATION);
	}
	
	public void authorizeUpate(){
		//1.删除旧有的权限
		String id = getPara("roleId");
		String ids = getPara("ids");
		try {
			if(NumberUtils.isNumber(id)){
				RolePerm.dao.deleteByRoleId(id);
				for(String FunctionId : ids.split(",")){
					RolePerm rp = new RolePerm();
					rp.setRoleId(Long.parseLong(id));
					rp.setFunctionId(FunctionId);
					//保存新的权限
					rp.save();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			setSysErr();
			list();
		}
		list();
	}

	public void roleToUser(){
		User user = getModel(User.class);
		Integer pageNumber = getParaToInt("pageNumber");
		if (pageNumber != null) {
			user.setPageNumber(pageNumber);
		}
		Page<User> page = User.dao.findPageList(user);
		Long roleId = getParaToLong("roleId");
		
		this.setAttr("roleId",roleId);
		this.setAttr("page",page);
		render(ROLETOUSER);
	}
	
	public void roleToUserUpdate(){
		try {
			String roleId = getPara("roleId");
			String userIds = getPara("ids");
			RoleUser.dao.updateRoleUser(roleId,userIds);
			setAttrMsg("配置成功");
		} catch (Exception e) {	
			e.printStackTrace();	
			setSysErr();
			list();
			throw e;
		}
		list();
	}
}
