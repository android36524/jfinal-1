package com.xiaoan.wlt.controller.erp.jxc;

import org.apache.shiro.authz.annotation.RequiresUser;

import com.xiaoan.wlt.common.Response;
import com.xiaoan.wlt.controller.BaseController;
import com.xiaoan.wlt.model.erp.jxc.Customer;
import com.xiaoan.wlt.model.erp.jxc.Depot;
import com.xiaoan.wlt.utils.ShiroUtils;

/**
 * 仓库管理
 * @author liangjiahong
 * @date 2016年11月14日
 */
@RequiresUser
public class DepotController extends BaseController{

	private static String LIST = "/erp/jxc/depot/list.jsp";
	
	public void list(){
		Depot d = this.getModel(Depot.class);
		d.setPageNumber(getParaToInt("pageNumber") == null ? 1:getParaToInt("pageNumber"));
		d.setSId(Integer.valueOf(ShiroUtils.getPrincipalProperty("shopsInfo").toString()));
		this.setPageAttr(Depot.dao.findPageList(d));
		render(LIST);
	}
	
	/**
	 * 编辑用	
	 */
	public void edit(){
		this.renderJson();
		try {
			Integer id = getParaToInt("id");
			if(id != null && id > 0){
				setAttr("re",new Response("success","操作成功",Depot.dao.findById(id)));
			}
		} catch (Exception e) {
			setAttr("re",new Response("err","操作失败",null));
		}
	}
	
	public void update(){
		Depot cu = getModel(Depot.class);
		try {
			cu.setSId(Integer.valueOf(ShiroUtils.getPrincipalProperty("shopsInfo").toString()));
			if(cu != null && cu.getId() > 0){
				cu.update();
			}else{
				cu.save();
			}
		} catch (Exception e) {
			e.printStackTrace();
			setSysErr();
		}
		list();
	}
}
