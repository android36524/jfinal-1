package com.xiaoan.wlt.controller.erp.jxc;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresUser;

import com.alibaba.fastjson.JSONArray;
import com.jfinal.plugin.activerecord.Page;
import com.xiaoan.wlt.controller.BaseController;
import com.xiaoan.wlt.model.erp.jxc.Stock;
import com.xiaoan.wlt.shiro.UserContext;

/**
 * 库存查询
 * @author liangjiahong
 * @date 2016年11月16日
 */
@RequiresUser
public class StockController extends BaseController{

	private static String LIST = "/erp/jxc/stock/list.jsp";
	
	public void list(){
		Stock stock = this.getModel(Stock.class);
		stock.setPageNumber(getParaToInt("pageNumber") == null ? 1:getParaToInt("pageNumber"));
		stock.setSId(UserContext.getShopsInfo());
		Page<Stock> page = Stock.dao.findPageList2(stock);
		this.setPageAttr(page);
		/*Map<String, JSONArray> map = Stock.dao.findToMapJsonArray();
		System.out.println(map.get("stock"));
		System.out.println(map.get("stockInto"));
		System.out.println(map.get("stockBegin"));
		setAttr("stockJson",map.get("stock"));
		setAttr("stockIntoJson",map.get("stockInto"));
		setAttr("stockBeginJson",map.get("stockBegin"));*/
		
		render(LIST);
	}
}
