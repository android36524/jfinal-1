package com.xiaoan.wlt.controller.erp.jxc;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.authz.annotation.RequiresUser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jfinal.plugin.activerecord.Page;
import com.xiaoan.wlt.common.Response;
import com.xiaoan.wlt.controller.BaseController;
import com.xiaoan.wlt.model.erp.jxc.Depot;
import com.xiaoan.wlt.model.erp.jxc.Into;
import com.xiaoan.wlt.model.erp.jxc.IntoList;
import com.xiaoan.wlt.model.erp.jxc.Product;
import com.xiaoan.wlt.model.erp.jxc.Stock;
import com.xiaoan.wlt.model.erp.jxc.Supplier;
import com.xiaoan.wlt.shiro.UserContext;
import com.xiaoan.wlt.utils.DateUtils;

/**
 * 入库单
 * @author liangjiahong
 * @date 2016年11月14日
 */
@RequiresUser
public class IntoController extends BaseController {

	private static String LIST = "/erp/jxc/into/list.jsp";
	private static String EDIT = "/erp/jxc/into/edit.jsp";
	private static String VIEW = "/erp/jxc/into/view.jsp";
	
	public void list(){
		Into into = this.getModel(Into.class);
		into.setPageNumber(getParaToInt("pageNumber") == null ? 1:getParaToInt("pageNumber"));
		into.setSId(UserContext.getShopsInfo());
		into.setStartTime(getParaToDate("startTime"));
		into.setEndTime(getParaToDate("endTime"));
		setAttr("supplier", Supplier.dao.findList());
		setPageAttr(Into.dao.findPageList2(into));
		setAttr("into",into);
		render(LIST);
	}
	
	public void edit(){
		try {
			Supplier supplier = new Supplier();
			supplier.setSId(UserContext.getShopsInfo());
			supplier.setPageSize(999999);
			setAttr("supplier",Supplier.dao.findPageList(supplier).getList());
			Depot depot = new Depot();
			depot.setSId(UserContext.getShopsInfo());
			depot.setPageSize(99999);
			setAttr("depot",Depot.dao.findPageList(depot).getList());
			setAttr("product",getProduct());
			if(getPara("id") != null){
				Into into = Into.dao.findInfoById(getPara("id"));
				List<IntoList> list = IntoList.dao.findInfoByIntoId(into.getId());
				setAttr("list",list);
				setAttr("into",into);
			}
			render(EDIT);
		} catch (Exception e) {
			e.printStackTrace();
			setSysErr();
			list();
		}
	}
	/**
	 * 查看
	 */
	public void view(){
		String id = getPara("ids").split(",")[0];
		try {
			Into into = Into.dao.findInfoById(id);
			List<IntoList> list = IntoList.dao.findInfoByIntoId(into.getId());
			setAttr("list",list);
			setAttr("into",into);
		} catch (Exception e) {
			this.setAttrErrMsg(e.getMessage());
			list();
		}
		render(VIEW);
	}
	
	public void cancelUpdate(){
		String id = getPara("ids").split(",")[0];
		try {
			Into.dao.cancelById(id);
		} catch (Exception e) {
			this.setAttrErrMsg(e.getMessage());
			e.printStackTrace();
		}
		list();
	}
	
	/**
	 * 保存入库记录。并修改库存
	 */
	public void saveInto(){
		System.out.println(getPara());
//		supplierId=5  intoTime=2016-11-23  num[]={1,15,,,,}  id[]={2,2,,,,}  price[]={156,156,,,,}  totalPrice[]={156,2340,,,,}  remarks=wqe  
		try {
			//1、保存主表
			Into into = addInto();
			//2、保存从表
			addIntoList(into.getId());
//			//3.入库
//			Stock.dao.intoStock(into);
		} catch (Exception e) {
			this.setAttrErrMsg(e.getMessage());
			throw e;
		}
		list();
	}
	
	//3.入库
	public void saveIntoStock(){
		Into into = Into.dao.findById(getPara("id"));
		Stock.dao.intoStock(into);
		into.setState("PUT");
		into.update();
		list();
	}

	private void addIntoList(Integer intoId) {
		String[] pid = getParaValues("pid");
		String[] id = getParaValues("id");
		String[] num = getParaValues("num");
		String[] unit = getParaValues("unit");
		String[] price = getParaValues("price");
		String[] totalPrice = getParaValues("totalPrice");//单个商品的总额
		Integer depotId = getParaToInt("depotId");
		for (int i = 0; i < pid.length; i++) {
			if(NumberUtils.isNumber(pid[i])){
				IntoList intoList = new IntoList();
				if(id != null && id[i] != null && NumberUtils.isNumber(id[i])){
					intoList.setId(Integer.valueOf(id[i]));
				}
				intoList.setDepotId(depotId);
				intoList.setIntoId(intoId);
				intoList.setNum(Double.valueOf(num[i]));
				intoList.setPriceInto(Double.valueOf(price[i]));
				intoList.setPriceTotal(Double.valueOf(totalPrice[i]));
				intoList.setUnit(unit[i]);
				intoList.setProductId(Integer.valueOf(pid[i]));
				intoList.saveOrUpdate();
			}
		}
	}

	private Into addInto() {
		Integer supplierId = getParaToInt("supplierId");
		String remarks = getPara("remarks");
		String intoUser = getPara("intoUser");
		String code = getPara("code");
		Date intoTime = DateUtils.parse(getPara("date"),DateUtils.DATE_FULL_STR);
		String priceTotal = getPara("priceTotal");//整 个单的总金额
		Integer depotId = getParaToInt("depotId");
		//保存失败必须全部回滚
		
		Into into = getModel(Into.class);
		into.setSId(UserContext.getShopsInfo());
		into.setCode(code);
		into.setIntoUser(intoUser);
		if(NumberUtils.isNumber(priceTotal)){
			into.setPriceTotal(Double.valueOf(priceTotal));
		}
		into.setRemarks(remarks);
		into.setState("CREATE");
		into.setIntoTime(intoTime);
		into.setSupplierId(supplierId);
		into.setDepotId(depotId);
		
		into.saveOrUpdate();
		return into;
	}
	
	public void getProductById(){
		try {
			Integer pId = getParaToInt("id");
			Product p = Product.dao.findById(pId);
			setAttr("re",new Response("ok", "", p));
		} catch (Exception e) {
			setAttr("re",new Response("errer", "获取数据失败",null));
		}
		this.renderJson();
	}
	
	private JsonObject getProduct(){
//		{
//			"message": "",
//			"value": [
//				{
//					"userName": "淳芸",
//					"shortAccount": "chunyun",
//					"userId": 20001
//				}
//			],
//			"code": 200,
//			"redirect": ""
//		}
		JsonObject jo = new JsonObject();
		jo.addProperty("message","");
		jo.addProperty("code",200);
		jo.addProperty("redirect", "");
		
		Product product = new Product();
		product.setSId(UserContext.getShopsInfo());
		product.setPageSize(999999);
		Page<Product> page = Product.dao.findPageList(product);
		JsonArray ja = new JsonArray();
		for(Product p : page.getList()){
			JsonObject j = new JsonObject();
			j.addProperty("id",p.getId());
			j.addProperty("code", p.getCode());
			j.addProperty("name", p.getName());
			j.addProperty("price_reference", p.getPriceReference());
			j.addProperty("unit", p.getUnit());
			ja.add(j);
		}
		jo.add("value",ja);
		return jo;
	}
	
	/**
	 * 退货单
	 */
	public void listReturn(){
		
	}
}
