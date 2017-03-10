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
public abstract class BaseRolePath<M extends BaseRolePath<M>> extends BaseModel<M> implements IBean {

	public void setId(java.lang.Long id) {
		set("id", id);
	}

	public java.lang.Long getId() {
		return get("id");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("createTime", createTime);
	}

	public java.util.Date getCreateTime() {
		return get("createTime");
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

	public void setFunctionCde(java.lang.String functionCde) {
		set("functionCde", functionCde);
	}

	public java.lang.String getFunctionCde() {
		return get("functionCde");
	}

	public void setPath(java.lang.String path) {
		set("path", path);
	}

	public java.lang.String getPath() {
		return get("path");
	}

	public void setRoleId(java.lang.Long roleId) {
		set("roleId", roleId);
	}

	public java.lang.Long getRoleId() {
		return get("roleId");
	}

	public static final String TABLE = " sys_role_path ";
	public WhereAndParas getWhereAndParas(BaseRolePath rolePath){
		StringBuffer sql = new StringBuffer();
		List<Object> list = new ArrayList<Object>();
		WhereAndParas wap = new WhereAndParas();
		if(rolePath.getId() != null && rolePath.getId() > 0){
			sql.append(" and o.id = ?");
			list.add(rolePath.getId());
		}
		if(rolePath.getVersion() != null && rolePath.getVersion() > 0){
			sql.append(" and o.version = ?");
			list.add(rolePath.getVersion());
		}
		if(StringUtils.isNotEmpty(rolePath.getFunctionCde())){
			sql.append(" and o.functionCde like ?");
			list.add("%"+rolePath.getFunctionCde()+"%");
		}
		if(StringUtils.isNotEmpty(rolePath.getPath())){
			sql.append(" and o.path like ?");
			list.add("%"+rolePath.getPath()+"%");
		}
		if(rolePath.getRoleId() != null && rolePath.getRoleId() > 0){
			sql.append(" and o.roleId = ?");
			list.add(rolePath.getRoleId());
		}
		wap.setWhere(sql.toString());
		wap.setParas(list.toArray());
		return wap;
	}
}