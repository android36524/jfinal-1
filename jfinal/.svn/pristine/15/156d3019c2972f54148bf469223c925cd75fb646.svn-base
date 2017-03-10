package com.xiaoan.wlt.controller.zyk;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.jfinal.plugin.activerecord.Page;
import com.xiaoan.wlt.controller.BaseController;
import com.xiaoan.wlt.model.erp.jxc.ShopsInfo;
import com.xiaoan.wlt.model.sys.User;
import com.xiaoan.wlt.model.zyk.Category;
import com.xiaoan.wlt.model.zyk.ResourceTemplate;
import com.xiaoan.wlt.model.zyk.ShopsResource;

/**
 * 资源库-商家资源
 * 
 * @author LeeSin
 *
 */
public class ShopsResourceController extends BaseController {
	private static final String LIST = "/zyk/shopsResource/list.jsp";
	private static final String EDIT = "/zyk/shopsResource/edit.jsp";

	public void index() {
		list();
	}

	public void list() {
		ShopsResource shopsResource = getModel(ShopsResource.class);
		Integer pageNumber = getParaToInt("pageNumber");
		if (pageNumber != null) {
			shopsResource.setPageNumber(pageNumber);
		}
		Page<ShopsResource> page = ShopsResource.dao
				.findPageList(shopsResource);
		this.setAttr("shopsResource", shopsResource);
		this.setAttr("page", page);
		this.render(LIST);
	}

	public void edit() {
		String id = this.getPara("id");
		if (NumberUtils.isNumber(id)) {
			this.setAttr("act", "修改");
			setAttr("shopsResource", ShopsResource.dao.findById(id));
		} else {
			this.setAttr("act", "添加");
		}
		setAttr("pcatList", Category.dao.getCatList(null));
		setAttr("templateList", ResourceTemplate.dao.getAll());
		this.render(EDIT);
	}

	public void update() {
		ShopsResource shopsResource = getModel(ShopsResource.class);
		if (shopsResource.getShopsId() == null) {
			Subject subject = SecurityUtils.getSubject();
			User user = (User) subject.getPrincipals().iterator().next();
			ShopsInfo shopsInfo = ShopsInfo.dao.findById(user.getShopsInfo());
			if (shopsInfo != null) {
				shopsResource.setShopsId((long) shopsInfo.getId());
				shopsResource.setShopsName(shopsInfo.getName());
			}
		}
		if (shopsResource.getId() != null && shopsResource.getId() > 0) {
			shopsResource.update();
		} else {
			shopsResource.save();
		}
		list();
	}

	/**
	 * 获取模板内容
	 */
	public void templateContent() {
		String templateId = getPara("tid");
		JSONObject result = new JSONObject();
		if (StringUtils.isNumeric(templateId)) {
			ResourceTemplate template = ResourceTemplate.dao
					.findById(templateId);
			if (template != null) {
				result.put("success", true);
				result.put("content", template.getContent());
				renderJson(result);
				return;
			}
		}
		result.put("success", false);
		result.put("msg", "未找到该模板");
		renderJson(result);
		return;
	}
}
