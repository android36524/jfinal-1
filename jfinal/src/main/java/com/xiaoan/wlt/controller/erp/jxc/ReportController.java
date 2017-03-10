package com.xiaoan.wlt.controller.erp.jxc;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresUser;

import com.xiaoan.wlt.controller.BaseController;
import com.xiaoan.wlt.model.erp.jxc.Into;
import com.xiaoan.wlt.model.erp.jxc.Out;

@RequiresUser
public class ReportController extends BaseController { 

	private static final String INTO = "/erp/jxc/report/into.jsp";
	
	private static final String OUT = "/erp/jxc/report/out.jsp";
	
	/**
	 * 进货报表
	 * <th>日期</th>
		<th>进货笔数</th>
		<th>进货额(元)</th>
		<th>操作</th>
		按单据
	 */
	public void into(){
		Into most = Into.dao.findMostProduct();
		Into into = Into.dao.findTotalNumPrice();
		Into total = Into.dao.findTotalSupplierPrice();
		Into maxP = Into.dao.findMaxPriceProduct();
		
		setAttr("maxP",maxP);
		setAttr("total",total);
		setAttr("most",most);
		setAttr("into",into);
		List<Into> list = Into.dao.findByIntoTimeGroup();
		setAttr("list", list);
		render(INTO);
	}
	
	/**
	 * 出货报表
	 */
	public void out(){
		Out most = Out.dao.findMostProduct();
		Out into = Out.dao.findTotalNumPrice();
		Out total = Out.dao.findTotalCustomerPrice();
		Out maxP = Out.dao.findMaxPriceProduct();
		
		setAttr("maxP",maxP);
		setAttr("total",total);
		setAttr("most",most);
		setAttr("into",into);
		List<Out> list = Out.dao.findByOutTimeGroup();
		setAttr("list", list);
		render(OUT);
	}
	
}
