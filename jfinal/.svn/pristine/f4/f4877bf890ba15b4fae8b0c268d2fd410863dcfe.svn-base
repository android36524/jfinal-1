package com.xiaoan.wlt.controller.erp.jxc;

import org.apache.shiro.authz.annotation.RequiresUser;

import com.google.gson.JsonArray;
import com.xiaoan.wlt.controller.BaseController;
import com.xiaoan.wlt.model.erp.jxc.Category;
import com.xiaoan.wlt.shiro.UserContext;

/**
 * 进销存中使用的商品分类
 * @author liangjiahong
 * @date 2016年11月10日
 */
@RequiresUser
public class CategoryController extends BaseController {

	private static String TREELIST = "/erp/jxc/category/treeList.jsp";
	
	public void treeList(){
		JsonArray ja = Category.dao.findTreeList();
		if(ja == null || ja.size() == 0){
			Category c = new Category();
			c.setParentId(null);
			c.setName("商品分类");
			c.setSId(UserContext.getShopsInfo());
			c.setState("USE");
			c.save();
			ja = Category.dao.findTreeList();
		}
		System.out.println(ja.toString());
		this.setAttr("json",ja);
		this.render(TREELIST);
	}
	
	public void saveTree(){
//		"pId":treeNode[0].pId,"name":treeNode[0].name},
		Integer pId = getParaToInt("pId");
		String name = getPara("name");
		Category c = new Category();
		c.setParentId(pId);
		c.setName(name);
		c.setSId(UserContext.getShopsInfo());
		c.setState("USE");
		c.save();
		this.renderJson(c);
	}
	
	public void updateTree(){
		try {
			Integer id = getParaToInt("id");
			String name = getPara("name");
			Category c = Category.dao.findById(id);
			c.setName(name);
			c.setVersion(c.getVersion()+1);
			c.update();
			this.setAttrMsg("修改成功！");
		} catch (Exception e) {
			this.setAttrMsg("修改失败!:"+e.getMessage());
		}
		this.renderJson();
	}
	
}
