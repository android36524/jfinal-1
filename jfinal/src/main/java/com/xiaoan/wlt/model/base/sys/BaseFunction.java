package com.xiaoan.wlt.model.base.sys;

import com.xiaoan.wlt.common.jfinal.BaseModel;
import com.jfinal.plugin.activerecord.IBean;

import org.apache.commons.lang3.StringUtils;

import com.xiaoan.wlt.common.WhereAndParas;

import java.util.ArrayList;

import java.util.List;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseFunction<M extends BaseFunction<M>> extends BaseModel<M> implements IBean {

	public void setFuncCde(java.lang.String funcCde) {
		set("funcCde", funcCde);
	}

	public java.lang.String getFuncCde() {
		return get("funcCde");
	}

	public void setClevel(java.lang.Integer clevel) {
		set("clevel", clevel);
	}

	public java.lang.Integer getClevel() {
		return get("clevel");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("createTime", createTime);
	}

	public java.util.Date getCreateTime() {
		return get("createTime");
	}

	public void setFuncName(java.lang.String funcName) {
		set("funcName", funcName);
	}

	public java.lang.String getFuncName() {
		return get("funcName");
	}

	public void setLink(java.lang.String link) {
		set("link", link);
	}

	public java.lang.String getLink() {
		return get("link");
	}

	public void setMenuType(java.lang.String menuType) {
		set("menuType", menuType);
	}

	public java.lang.String getMenuType() {
		return get("menuType");
	}

	public void setPerLink(java.lang.String perLink) {
		set("perLink", perLink);
	}

	public java.lang.String getPerLink() {
		return get("perLink");
	}

	public void setSort(java.lang.Integer sort) {
		set("sort", sort);
	}

	public java.lang.Integer getSort() {
		return get("sort");
	}

	public void setState(java.lang.String state) {
		set("state", state);
	}

	public java.lang.String getState() {
		return get("state");
	}

	public void setUpdateTime(java.util.Date updateTime) {
		set("updateTime", updateTime);
	}

	public java.util.Date getUpdateTime() {
		return get("updateTime");
	}

	public void setVersion(java.lang.Integer version) {
		set("version", version);
	}

	public java.lang.Integer getVersion() {
		return get("version");
	}

	public void setParentCde(java.lang.String parentCde) {
		set("parentCde", parentCde);
	}

	public java.lang.String getParentCde() {
		return get("parentCde");
	}

	public static final String TABLE = " sys_function ";
	public WhereAndParas getWhereAndParas(BaseFunction function){
		StringBuffer sql = new StringBuffer();
		List<Object> list = new ArrayList<Object>();
		WhereAndParas wap = new WhereAndParas();
		if(StringUtils.isNotEmpty(function.getFuncCde())){
			sql.append(" and o.funcCde like ?");
			list.add("%"+function.getFuncCde()+"%");
		}
		if(function.getClevel() != null && function.getClevel() > 0){
			sql.append(" and o.clevel = ?");
			list.add(function.getClevel());
		}
		if(StringUtils.isNotEmpty(function.getFuncName())){
			sql.append(" and o.funcName like ?");
			list.add("%"+function.getFuncName()+"%");
		}
		if(StringUtils.isNotEmpty(function.getLink())){
			sql.append(" and o.link like ?");
			list.add("%"+function.getLink()+"%");
		}
		if(StringUtils.isNotEmpty(function.getMenuType())){
			sql.append(" and o.menuType like ?");
			list.add("%"+function.getMenuType()+"%");
		}
		if(StringUtils.isNotEmpty(function.getPerLink())){
			sql.append(" and o.perLink like ?");
			list.add("%"+function.getPerLink()+"%");
		}
		if(function.getSort() != null && function.getSort() > 0){
			sql.append(" and o.sort = ?");
			list.add(function.getSort());
		}
		if(StringUtils.isNotEmpty(function.getState())){
			sql.append(" and o.state like ?");
			list.add("%"+function.getState()+"%");
		}
		if(function.getVersion() != null && function.getVersion() > 0){
			sql.append(" and o.version = ?");
			list.add(function.getVersion());
		}
		if(StringUtils.isNotEmpty(function.getParentCde())){
			sql.append(" and o.parentCde like ?");
			list.add("%"+function.getParentCde()+"%");
		}
		wap.setWhere(sql.toString());
		wap.setParas(list.toArray());
		return wap;
	}
}
