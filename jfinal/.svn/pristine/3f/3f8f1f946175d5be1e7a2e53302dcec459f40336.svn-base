package com.xiaoan.wlt.controller.erp.jxc;

import com.xiaoan.wlt.controller.BaseController;
import com.xiaoan.wlt.model.erp.jxc.Depot;
import com.xiaoan.wlt.model.erp.jxc.ShopsInfo;
import com.xiaoan.wlt.model.sys.RoleUser;
import com.xiaoan.wlt.model.sys.User;
import com.xiaoan.wlt.utils.MD5Util;

/**
 * 商家信息
 * @author liangjiahong
 * @date 2016年11月10日
 */
public class ShopsInfoController extends BaseController{

	private static String LIST = "/erp/jxc/shopsInfo/list.jsp";
	
	public void list(){
		ShopsInfo shopsInfo = this.getModel(ShopsInfo.class);
		try {
			Integer pageNumber = getParaToInt("pageNumber");
			if (pageNumber != null) {
				shopsInfo.setPageNumber(pageNumber);
			}
			setAttr("shopsInfo",shopsInfo);
			setPageAttr(ShopsInfo.dao.findPageList2(shopsInfo));
		} catch (Exception e) {
			e.printStackTrace();
			setSysErr();
		}
		this.render(LIST);
	}
	
	/**
	 * 同步更新商家信息
	 */
	public void sync(){
		try {
			Integer index = ShopsInfo.dao.sync();
			setAttrMsg("本次一共更新了："+index+"条数据！");
		} catch (Exception e) {
			e.printStackTrace();
			setSysErr();
		}
		list();
	}

	/**
	 * 根据商家生成系统用户
	 */
	public void authorization(){
		int shopsId = this.getParaToInt("id");
		String accunt = getPara("accunt");
		ShopsInfo shopsInfo = ShopsInfo.dao.findById(shopsId);
		User user = User.dao.findByCode(shopsInfo.getMobile().trim());
		
		if(user != null && user.getUserCode().equals(accunt.trim())){
			setAttrErrMsg("账号已存在！请更改账号重试");
			list();
			return;
		}
		user = new User();
		user.setDelState("N");
		user.setUserCode(accunt.trim());
		user.setPhone(shopsInfo.getMobile());
		user.setPwd(MD5Util.getMD5String("888888"));
		user.setUserType("SHOPSINFO");
		user.setUserName(shopsInfo.getAppellation());
		user.setState("USE");
		user.setShopsInfo(shopsId);
		
		try {
			//角色
			user.save();
			RoleUser.dao.updateRoleUser("19",user.getId()+",");
			//添加默认仓库
			Depot d = new Depot();
			d.setAddr("");
			d.setSId(shopsId);
			d.setName("默认仓库");
			d.save();
			setAttrMsg("操作成功");
		} catch (Exception e) {
			setAttrErrMsg(e.getMessage());
			throw e;
		}
		list();
	}
	
}
