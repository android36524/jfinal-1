package com.xiaoan.wlt.controller.erp.jxc;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.authz.annotation.RequiresUser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jfinal.plugin.activerecord.Page;
import com.xiaoan.wlt.common.Response;
import com.xiaoan.wlt.controller.BaseController;
import com.xiaoan.wlt.model.erp.jxc.Customer;
import com.xiaoan.wlt.model.erp.jxc.Depot;
import com.xiaoan.wlt.model.erp.jxc.Out;
import com.xiaoan.wlt.model.erp.jxc.OutIntoList;
import com.xiaoan.wlt.model.erp.jxc.OutList;
import com.xiaoan.wlt.model.erp.jxc.Product;
import com.xiaoan.wlt.model.erp.jxc.Stock;
import com.xiaoan.wlt.shiro.UserContext;
import com.xiaoan.wlt.utils.DateUtils;

@RequiresUser
public class OutController extends BaseController {

	private static String LIST = "/erp/jxc/out/list.jsp";
	private static String EDIT = "/erp/jxc/out/edit.jsp";
	private static String VIEW = "/erp/jxc/out/view.jsp";
	
	public void list(){
		Out out = new Out();
		try {
			out = this.getModel(Out.class);
			out.setSId(UserContext.getShopsInfo());
			out.setcName(getPara("cName"));
			out.setPageNumber(getParaToInt("pageNumber") == null ? 1:getParaToInt("pageNumber"));
			out.setStartTime(getParaToDate("startTime"));
			out.setEndTime(getParaToDate("endTime"));
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrMsg("系统异常："+e.getMessage());
		}
		this.setPageAttr(Out.dao.findPageList2(out));
		this.setAttr("out",out);
		render(LIST);
	}
	
	public void edit(){
		try {
			Customer customer = new Customer();
			customer.setSId(UserContext.getShopsInfo());
			customer.setPageSize(999999);
			setAttr("customer",Customer.dao.findPageList(customer).getList());
			Depot depot = new Depot();
			depot.setSId(UserContext.getShopsInfo());
			depot.setPageSize(999999);
			setAttr("depot",Depot.dao.findPageList(depot).getList());
			setAttr("product",getProduct());
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
			Out out = Out.dao.findInfoById(id);
			List<OutList> list = OutList.dao.findInfoByIntoId(out.getId());
			setAttr("list",list);
			setAttr("out",out);
		} catch (Exception e) {
			e.printStackTrace();
			this.setAttrErrMsg(e.getMessage());
			list();
		}
		render(VIEW);
	}
	
	/**
	 * 取消，作废
	 */
	public void cancelUpdate(){
		String id = getPara("ids").split(",")[0];
		try {
			Out.dao.cancelById(id);
		} catch (Exception e) {
			this.setAttrErrMsg(e.getMessage());
			e.printStackTrace();
		}
		list();
	}
	
	/**
	 * 出货单
	 */
	public void saveOut(){
		System.out.println(getPara());
		try {
			//保存失败必须全部回滚
			//1、保存主表
			Out out = addOut();
			//2、保存从表
			addOutList(out.getId());
			//3.出库
			Stock.dao.outStock(out);
			//4.价格对照表
			OutIntoList.dao.addByOut(out);
		} catch (Exception e) {
			this.setAttrErrMsg(e.getMessage());
			throw e;
		}
		list();
	}
	private void addOutList(Integer outId) {
		String[] id = getParaValues("id");
		String[] num = getParaValues("num");
		String[] unit = getParaValues("unit");
		String[] price = getParaValues("price");
		String[] totalPrice = getParaValues("totalPrice");//单个商品的总额
		
		Integer depotId = getParaToInt("depotId");
		for (int i = 0; i < id.length; i++) {
			if(NumberUtils.isNumber(id[i])){
				OutList outList = new OutList();
				outList.setDepotId(depotId);
				outList.setOutId(outId);
				outList.setNum(Double.valueOf(num[i]));
				outList.setPriceOut(Double.valueOf(price[i]));
				outList.setPriceTotal(Double.valueOf(totalPrice[i]));
				outList.setUnit(unit[i]);
				outList.setProductId(Integer.valueOf(id[i]));
				outList.save();
			}
		}
	}
	private Out addOut() {
		Integer customerId = getParaToInt("customerId");
		String remarks = getPara("remarks");
		String user = getPara("user");
		String code = getPara("code");

		Date time = DateUtils.parse(getPara("date")+" "+getPara("time"),DateUtils.DATE_FULL_STR2);
		
		String priceTotal = getPara("priceTotal");//整 个单的总金额
		Integer depotId = getParaToInt("depotId");
		
		Out out = new Out();
		out.setSId(UserContext.getShopsInfo());
		out.setCode(code);
		out.setOutUser(user);
		if(NumberUtils.isNumber(priceTotal)){
			out.setPriceTotal(Double.valueOf(priceTotal));
		}
		out.setRemarks(remarks);
		out.setState("OUT");
		out.setOutTime(time);
		out.setCId(customerId);
		out.setDepotId(depotId);
		out.save();
		return out;
	}
	
	private JsonObject getProduct(){
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
			j.addProperty("price_retail", p.getPriceRetail());
			j.addProperty("unit", p.getUnit());
			ja.add(j);
		}
		jo.add("value",ja);
		return jo;
	}
	
	public void getProductById(){
		try {
			Integer pId = getParaToInt("id");
			Integer depotId = getParaToInt("depotId");
			Product p = Product.dao.findById(pId);
			//出货单上把库存也查出来
			//code/
			
			String code = UserContext.getShopsInfo()+"-"+depotId+"-"+pId;
			Stock stock = Stock.dao.findByCode(code);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("product", p);
			map.put("stock", stock.getNum());
			setAttr("re",new Response("ok", "", map));
		} catch (Exception e) {
			e.printStackTrace();
			setAttr("re",new Response("errer", "获取数据失败",null));
		}
		this.renderJson();
	}
	
}
