package com.xiaoan.wlt.model.erp.jxc;

import org.apache.commons.lang3.StringUtils;

import com.xiaoan.wlt.common.WhereAndParas;

import java.util.ArrayList;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;

import com.xiaoan.wlt.model.base.erp.jxc.BaseBeginStock;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class BeginStock extends BaseBeginStock<BeginStock> {
	public static final BeginStock dao = new BeginStock();
	private int pageNumber = 1;
	private int pageSize = 15;
	public int getPageSize() {return pageSize;}
	public void setPageSize(int pageSize) {this.pageSize = pageSize;}
	public int getPageNumber() {return pageNumber;}
	public void setPageNumber(int pageNumber) {this.pageNumber = pageNumber;}
	public Page<BeginStock> findPageList(BeginStock beginStock) {
		WhereAndParas wp = getWhereAndParas(beginStock);
		return dao.paginate(beginStock.getPageNumber(),beginStock.getPageSize(),"select * ","from"+ BeginStock.TABLE + "where 1=1 " +wp.getWhere(),wp.getParas());
	}
	public static final String TABLE = " erp_jxc_begin_stock ";
	public WhereAndParas getWhereAndParas(BeginStock beginStock){
		StringBuffer sql = new StringBuffer();
		List<Object> list = new ArrayList<Object>();
		WhereAndParas wap = new WhereAndParas();
		if(beginStock.getId() != null && beginStock.getId() > 0){
			sql.append(" and id = ?");
			list.add(beginStock.getId());
		}
		if(beginStock.getSId() != null && beginStock.getSId() > 0){
			sql.append(" and s_id = ?");
			list.add(beginStock.getSId());
		}
		if(beginStock.getVersion() != null && beginStock.getVersion() > 0){
			sql.append(" and version = ?");
			list.add(beginStock.getVersion());
		}
		if(beginStock.getPId() != null && beginStock.getPId() > 0){
			sql.append(" and p_id = ?");
			list.add(beginStock.getPId());
		}
		if(StringUtils.isNotEmpty(beginStock.getRemarks())){
			sql.append(" and remarks like ?");
			list.add("%"+beginStock.getRemarks()+"%");
		}
		wap.setWhere(sql.toString());
		wap.setParas(list.toArray());
		return wap;
	}
}