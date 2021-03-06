package com.xiaoan.wlt.controller.erp.jxc;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresUser;

import com.xiaoan.wlt.controller.BaseController;
import com.xiaoan.wlt.model.erp.jxc.BeginStock;
import com.xiaoan.wlt.model.erp.jxc.Category;
import com.xiaoan.wlt.model.erp.jxc.Product;
import com.xiaoan.wlt.model.erp.jxc.Stock;
import com.xiaoan.wlt.shiro.UserContext;
import com.xiaoan.wlt.utils.ShiroUtils;

/**
 * 商家商品
 * @author liangjiahong
 * @date 2016年11月11日
 */
@RequiresUser
public class ProductController extends BaseController{

	private static String LIST = "/erp/jxc/product/list.jsp";
	private static String EDIT = "/erp/jxc/product/edit.jsp";
	
	public void list(){
		Product p = getModel(Product.class);
		p.setPageNumber(getParaToInt("pageNumber") == null ? 1 : getParaToInt("pageNumber"));
		try {
			p.setSId(Integer.valueOf(ShiroUtils.getPrincipalProperty("shopsInfo").toString()));
			setAttr("category", Category.dao.findList());
			setPageAttr(Product.dao.findPageList(p));
			setAttr("product", p);
		} catch (Exception e) {
			e.printStackTrace();
			setSysErr();
		}
		render(LIST);
	}
	
	public void edit(){
		Integer id = getParaToInt("id");
		if(id != null && id > 0){
			Product p = Product.dao.findById(id);
			/*List<BeginStock> list = BeginStock.dao.findByPId(p.getId());
			setAttr("beginStock",list != null && list.size() > 0 ? list.get(0):null);*/
			setAttr("product",p);
			setAttr("act","修改");
		}else{
			setAttr("act","添加");
		}
		setAttr("category", Category.dao.findList());
		render(EDIT);
	}
	
	public void update(){
		Product p = getModel(Product.class);
		BeginStock b = getModel(BeginStock.class);
		b.setPId(p.getId());
		b.setSId(UserContext.getShopsInfo());
		try {
			if(p.getId() != null && p.getId() > 0){
				p.update();
			}else{
				p.setSId(Integer.valueOf(ShiroUtils.getPrincipalProperty("shopsInfo").toString()));
				p.getCode();
				Product f = Product.dao.findbyCode(p.getCode());
				if(f != null){
					setAttrMsg("该编号已存在！");
					setAttr("product",p);
					edit();
					return;
				}
				p.save();
			}
			b.saveOrUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			setErrMsg(e.getMessage());
			list();
		}
		list();
	}
}
