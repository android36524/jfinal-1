package com.xiaoan.wlt.controller.erp.jxc;

import com.xiaoan.wlt.controller.BaseController;
import com.xiaoan.wlt.model.erp.jxc.Product;
import com.xiaoan.wlt.model.erp.jxc.Supplier;
import com.xiaoan.wlt.utils.ShiroUtils;

/**
 * 商家 供货商
 * @author liangjiahong
 * @date 2016年11月14日
 */
public class SupplierController extends BaseController{

	private static String LIST = "/erp/jxc/supplier/list.jsp";
	private static String EDIT = "/erp/jxc/supplier/edit.jsp";
	
	public void list(){
		Supplier customer = this.getModel(Supplier.class);
		customer.setPageNumber(getParaToInt("pageNumber") == null ? 1:getParaToInt("pageNumber"));
		customer.setSId(Integer.valueOf(ShiroUtils.getPrincipalProperty("shopsInfo").toString()));
		this.setPageAttr(Supplier.dao.findPageList(customer));
		render(LIST);
	}
	
	/**
	 * 编辑用	
	 */
	public void edit(){
		Integer id = getParaToInt("id");
		if(id != null && id > 0){
			Supplier p = Supplier.dao.findById(id);
			setAttr("supplier",p);
			setAttr("act","修改");
		}else{
			setAttr("act","添加");
		}
		render(EDIT);
	}
	
	public void update(){
		Supplier p = getModel(Supplier.class);
		if(p.getId() != null && p.getId() > 0){
			p.update();
		}else{
			p.setSId(Integer.valueOf(ShiroUtils.getPrincipalProperty("shopsInfo").toString()));
			p.save();
		}
		this.redirect("/erp/jxc/supplier/list");
	}
	
}
