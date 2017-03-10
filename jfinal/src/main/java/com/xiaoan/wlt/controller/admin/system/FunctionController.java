package com.xiaoan.wlt.controller.admin.system;

import java.util.Date;

import org.apache.shiro.authz.annotation.RequiresUser;

import com.xiaoan.wlt.common.Response;
import com.xiaoan.wlt.controller.BaseController;
import com.xiaoan.wlt.model.sys.Function;

@RequiresUser
public class FunctionController extends BaseController{

	private static String TREELIST = "/admin/system/function/treeList.jsp";
	
	public void treeList(){
		Response re = null ;
		re =  Function.dao.findTreeList();
		System.out.println(re.getData().toString());
		this.setAttr("json",re.getData());
		Response re1 = Function.dao.findMenuList();
		this.setAttr("menu",re1.getData());
		this.render(TREELIST);
	}
	
	public void list() {
		
	}

	public void del() {
		String id = this.getPara("id");
		try {
			boolean b = Function.dao.deleteById(id);
			if(b)
				setAttr("re",new Response("success","删除成功",null));
			else
				setAttr("re",new Response("errer","删除失败",null));
		} catch (Exception e) {
			e.printStackTrace();
			setAttr("re",new Response("errer","删除失败，可能存在关联数据！",null));
		}
		renderJson();
	}

	public void edit() {
		String id = this.getPara("id");
		try {
			this.setAttr("re",new Response("success","",Function.dao.findById(id)));
		} catch (Exception e) {
			e.printStackTrace();
			this.setAttr("re",new Response("err","读出数据出错了。",null));
		}
		renderJson();
	}

	public void save() {
		Function p = this.getModel(Function.class);
		
		try {
			p.save();
		} catch (Exception e) {
			setAttr("function", p);
			setAttrMsg(e.getMessage());
			e.printStackTrace();
		}
		treeList();
	}

	public void update() {
		Function p = this.getModel(Function.class);
		try {
			p.update();
		} catch (Exception e) {
			setAttr("function", p);
			setAttrMsg(e.getMessage());
			e.printStackTrace();
		}
		treeList();
	}
	
}
