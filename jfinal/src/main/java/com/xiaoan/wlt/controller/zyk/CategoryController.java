package com.xiaoan.wlt.controller.zyk;

import org.apache.commons.lang.math.NumberUtils;

import com.jfinal.plugin.activerecord.Page;
import com.xiaoan.wlt.controller.BaseController;
import com.xiaoan.wlt.model.zyk.Category;

/**
 * 资源库-分类
 * 
 * @author LeeSin
 *
 */
public class CategoryController extends BaseController {
	private static final String LIST = "/zyk/category/list.jsp";
	private static final String EDIT = "/zyk/category/edit.jsp";

	public void index() {
		list();
	}

	public void list() {
		Category category = getModel(Category.class);
		Integer pageNumber = getParaToInt("pageNumber");
		if (pageNumber != null) {
			category.setPageNumber(pageNumber);
		}
		Page<Category> page = Category.dao.findPageList(category);
		this.setAttr("category", category);
		this.setAttr("page", page);
		this.render(LIST);
	}
	// 图标上传
	public void edit() {
		String id = this.getPara("id");
		if (NumberUtils.isNumber(id)) {
			this.setAttr("act", "修改");
			setAttr("category", Category.dao.findById(id));
			setAttr("pcatList", Category.dao.getCatList(id));
		} else {
			this.setAttr("act", "添加");
			setAttr("pcatList", Category.dao.getCatList(null));
		}
		this.render(EDIT);
	}

	public void update() {
		Category cat = getModel(Category.class);
		if (cat.getId() != null && cat.getId() > 0) {
			cat.update();
		} else {
			cat.save();
		}
		// 更新parent category
		if (cat.getPid() != null && cat.getPid() > 0) {
			Category parent = Category.dao.findById(cat.getPid());
			if (parent.getIsParent() == null || parent.getIsParent() == 0) {
				parent.setIsParent(1);
				parent.update();
			}
		}
		list();
	}
}
