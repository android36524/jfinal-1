package com.xiaoan.wlt.controller.erp.jxc;

import org.apache.shiro.authz.annotation.RequiresUser;

import com.xiaoan.wlt.common.Response;
import com.xiaoan.wlt.controller.BaseController;
import com.xiaoan.wlt.model.erp.jxc.Customer;
import com.xiaoan.wlt.utils.ShiroUtils;

/**
 * 商家客户
 * @author liangjiahong
 * @date 2016年11月11日
 */
@RequiresUser
public class CustomerController extends BaseController{

	private static String LIST = "/erp/jxc/customer/list.jsp";
	
	public void list(){
		Customer customer = this.getModel(Customer.class);
		customer.setPageNumber(getParaToInt("pageNumber") == null ? 1:getParaToInt("pageNumber"));
		customer.setSId(Integer.valueOf(ShiroUtils.getPrincipalProperty("shopsInfo").toString()));
		this.setPageAttr(Customer.dao.findPageList(customer));
		this.render(LIST);
	}
	
	public void save(){
		Customer cu = getModel(Customer.class);
		cu.setState("USE");
		try {
			System.out.println(cu.get("createTime"));
			cu.setSId(Integer.valueOf(ShiroUtils.getPrincipalProperty("shopsInfo").toString()));
			cu.save();
		} catch (Exception e) {
			e.printStackTrace();
			setSysErr();
		}
		list();
	}
	
	/**
	 * 编辑用	
	 */
	public void edit(){
		this.renderJson();
		try {
			Integer id = getParaToInt("id");
			if(id != null && id > 0){
				setAttr("re",new Response("success","操作成功",Customer.dao.findById(id)));
			}
		} catch (Exception e) {
			setAttr("re",new Response("err","操作失败",null));
		}
	}
		
}
