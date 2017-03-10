package com.xiaoan.wlt.controller.zyk;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.jfinal.plugin.activerecord.Page;
import com.xiaoan.wlt.controller.BaseController;
import com.xiaoan.wlt.model.sys.User;
import com.xiaoan.wlt.model.zyk.Category;
import com.xiaoan.wlt.model.zyk.ResourceTemplate;

/**
 * 资源库-资源模板
 * 
 * @author LeeSin
 *
 */
public class ResourceTemplateController extends BaseController {
	private static final String LIST = "/zyk/template/list.jsp";
	private static final String EDIT = "/zyk/template/edit.jsp";

	public void index() {
		list();
	}

	public void list() {
		ResourceTemplate resourceTemplate = getModel(ResourceTemplate.class);
		Integer pageNumber = getParaToInt("pageNumber");
		if (pageNumber != null) {
			resourceTemplate.setPageNumber(pageNumber);
		}
		Page<ResourceTemplate> page = ResourceTemplate.dao
				.findPageList(resourceTemplate);
		this.setAttr("resourceTemplate", resourceTemplate);
		this.setAttr("page", page);
		this.render(LIST);
	}

	public void edit() {
		String id = this.getPara("id");
		if (NumberUtils.isNumber(id)) {
			this.setAttr("act", "修改");
			setAttr("resourceTemplate", ResourceTemplate.dao.findById(id));
		} else {
			this.setAttr("act", "添加");
		}
		setAttr("pcatList", Category.dao.getCatList(null));
		this.render(EDIT);
	}

	public void update() {
		ResourceTemplate resourceTemplate = getModel(ResourceTemplate.class);
		if (resourceTemplate.getAuthorId() == null) {
			Subject subject = SecurityUtils.getSubject();
			User user = (User) subject.getPrincipals().iterator().next();
			resourceTemplate.setAuthorId(user.getId());
			resourceTemplate.setAuthor(user.getUserName());
		}
		if (resourceTemplate.getId() != null && resourceTemplate.getId() > 0) {
			resourceTemplate.update();
		} else {
			resourceTemplate.save();
		}
		list();
	}
}
