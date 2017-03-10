package com.xiaoan.wlt.common;


/**
 * sql where 条件 和 参数
 * @author liangjiahong
 * @date 2016年10月25日
 */
public class WhereAndParas {
	
	private String where;
	private Object[] paras;
	private String order;
	
	public Object[] getParas() {
		return paras;
	}
	public void setParas(Object[] paras) {
		this.paras = paras;
	}
	public String getWhere() {
		return where;
	}
	public void setWhere(String where) {
		this.where = where;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
}
