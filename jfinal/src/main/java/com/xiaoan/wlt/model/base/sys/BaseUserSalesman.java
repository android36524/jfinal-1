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
public abstract class BaseUserSalesman<M extends BaseUserSalesman<M>> extends BaseModel<M> implements IBean {

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

	public void setVersion(java.lang.Long version) {
		set("version", version);
	}

	public java.lang.Long getVersion() {
		return get("version");
	}

	public void setSysUserId(java.lang.Long sysUserId) {
		set("sys_user_id", sysUserId);
	}

	public java.lang.Long getSysUserId() {
		return get("sys_user_id");
	}

	public void setCode(java.lang.String code) {
		set("code", code);
	}

	public java.lang.String getCode() {
		return get("code");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}

	public java.lang.String getName() {
		return get("name");
	}

	public void setPassword(java.lang.String password) {
		set("password", password);
	}

	public java.lang.String getPassword() {
		return get("password");
	}

	public void setInStatus(java.lang.String inStatus) {
		set("inStatus", inStatus);
	}

	public java.lang.String getInStatus() {
		return get("inStatus");
	}

	public void setSex(java.lang.String sex) {
		set("sex", sex);
	}

	public java.lang.String getSex() {
		return get("sex");
	}

	public void setBirthday(java.lang.String birthday) {
		set("birthday", birthday);
	}

	public java.lang.String getBirthday() {
		return get("birthday");
	}

	public void setMobile(java.lang.String mobile) {
		set("mobile", mobile);
	}

	public java.lang.String getMobile() {
		return get("mobile");
	}

	public void setMarriage(java.lang.String marriage) {
		set("marriage", marriage);
	}

	public java.lang.String getMarriage() {
		return get("marriage");
	}

	public void setQq(java.lang.String qq) {
		set("qq", qq);
	}

	public java.lang.String getQq() {
		return get("qq");
	}

	public void setHeadimgurl(java.lang.String headimgurl) {
		set("headimgurl", headimgurl);
	}

	public java.lang.String getHeadimgurl() {
		return get("headimgurl");
	}

	public void setBirthdayTime(java.util.Date birthdayTime) {
		set("birthdayTime", birthdayTime);
	}

	public java.util.Date getBirthdayTime() {
		return get("birthdayTime");
	}

	public void setDelState(java.lang.String delState) {
		set("delState", delState);
	}

	public java.lang.String getDelState() {
		return get("delState");
	}

	public void setMarryState(java.lang.String marryState) {
		set("marryState", marryState);
	}

	public java.lang.String getMarryState() {
		return get("marryState");
	}

	public void setSexType(java.lang.String sexType) {
		set("sexType", sexType);
	}

	public java.lang.String getSexType() {
		return get("sexType");
	}

	public void setDelFlag(java.lang.String delFlag) {
		set("delFlag", delFlag);
	}

	public java.lang.String getDelFlag() {
		return get("delFlag");
	}

	public static final String TABLE = " sys_user_salesman ";
	public WhereAndParas getWhereAndParas(BaseUserSalesman userSalesman){
		StringBuffer sql = new StringBuffer();
		List<Object> list = new ArrayList<Object>();
		WhereAndParas wap = new WhereAndParas();
		if(userSalesman.getId() != null && userSalesman.getId() > 0){
			sql.append(" and o.id = ?");
			list.add(userSalesman.getId());
		}
		if(userSalesman.getVersion() != null && userSalesman.getVersion() > 0){
			sql.append(" and o.version = ?");
			list.add(userSalesman.getVersion());
		}
		if(userSalesman.getSysUserId() != null && userSalesman.getSysUserId() > 0){
			sql.append(" and o.sys_user_id = ?");
			list.add(userSalesman.getSysUserId());
		}
		if(StringUtils.isNotEmpty(userSalesman.getCode())){
			sql.append(" and o.code like ?");
			list.add("%"+userSalesman.getCode()+"%");
		}
		if(StringUtils.isNotEmpty(userSalesman.getName())){
			sql.append(" and o.name like ?");
			list.add("%"+userSalesman.getName()+"%");
		}
		if(StringUtils.isNotEmpty(userSalesman.getPassword())){
			sql.append(" and o.password like ?");
			list.add("%"+userSalesman.getPassword()+"%");
		}
		if(StringUtils.isNotEmpty(userSalesman.getInStatus())){
			sql.append(" and o.inStatus like ?");
			list.add("%"+userSalesman.getInStatus()+"%");
		}
		if(StringUtils.isNotEmpty(userSalesman.getSex())){
			sql.append(" and o.sex like ?");
			list.add("%"+userSalesman.getSex()+"%");
		}
		if(StringUtils.isNotEmpty(userSalesman.getBirthday())){
			sql.append(" and o.birthday like ?");
			list.add("%"+userSalesman.getBirthday()+"%");
		}
		if(StringUtils.isNotEmpty(userSalesman.getMobile())){
			sql.append(" and o.mobile like ?");
			list.add("%"+userSalesman.getMobile()+"%");
		}
		if(StringUtils.isNotEmpty(userSalesman.getMarriage())){
			sql.append(" and o.marriage like ?");
			list.add("%"+userSalesman.getMarriage()+"%");
		}
		if(StringUtils.isNotEmpty(userSalesman.getQq())){
			sql.append(" and o.qq like ?");
			list.add("%"+userSalesman.getQq()+"%");
		}
		if(StringUtils.isNotEmpty(userSalesman.getHeadimgurl())){
			sql.append(" and o.headimgurl like ?");
			list.add("%"+userSalesman.getHeadimgurl()+"%");
		}
		if(StringUtils.isNotEmpty(userSalesman.getDelState())){
			sql.append(" and o.delState like ?");
			list.add("%"+userSalesman.getDelState()+"%");
		}
		if(StringUtils.isNotEmpty(userSalesman.getMarryState())){
			sql.append(" and o.marryState like ?");
			list.add("%"+userSalesman.getMarryState()+"%");
		}
		if(StringUtils.isNotEmpty(userSalesman.getSexType())){
			sql.append(" and o.sexType like ?");
			list.add("%"+userSalesman.getSexType()+"%");
		}
		if(StringUtils.isNotEmpty(userSalesman.getDelFlag())){
			sql.append(" and o.delFlag like ?");
			list.add("%"+userSalesman.getDelFlag()+"%");
		}
		wap.setWhere(sql.toString());
		wap.setParas(list.toArray());
		return wap;
	}
}
