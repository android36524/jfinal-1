package com.xiaoan.wlt.model.base.t;

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
public abstract class BaseAnasGoal<M extends BaseAnasGoal<M>> extends BaseModel<M> implements IBean {

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

	public void setConcrete(java.lang.String concrete) {
		set("concrete", concrete);
	}

	public java.lang.String getConcrete() {
		return get("concrete");
	}

	public void setEndTime(java.util.Date endTime) {
		set("endTime", endTime);
	}

	public java.util.Date getEndTime() {
		return get("endTime");
	}

	public void setFinish(java.lang.Double finish) {
		set("finish", finish);
	}

	public java.lang.Double getFinish() {
		return get("finish");
	}

	public void setGoalCycle(java.lang.String goalCycle) {
		set("goalCycle", goalCycle);
	}

	public java.lang.String getGoalCycle() {
		return get("goalCycle");
	}

	public void setGoalState(java.lang.String goalState) {
		set("goalState", goalState);
	}

	public java.lang.String getGoalState() {
		return get("goalState");
	}

	public void setGoalType(java.lang.String goalType) {
		set("goalType", goalType);
	}

	public java.lang.String getGoalType() {
		return get("goalType");
	}

	public void setNum(java.lang.Double num) {
		set("num", num);
	}

	public java.lang.Double getNum() {
		return get("num");
	}

	public void setStartTime(java.util.Date startTime) {
		set("startTime", startTime);
	}

	public java.util.Date getStartTime() {
		return get("startTime");
	}

	public void setCreateUser(java.lang.Long createUser) {
		set("create_user", createUser);
	}

	public java.lang.Long getCreateUser() {
		return get("create_user");
	}

	public void setWeek(java.lang.Integer week) {
		set("week", week);
	}

	public java.lang.Integer getWeek() {
		return get("week");
	}

	public static final String TABLE = " t_anas_goal ";
	public WhereAndParas getWhereAndParas(BaseAnasGoal anasGoal){
		StringBuffer sql = new StringBuffer();
		List<Object> list = new ArrayList<Object>();
		WhereAndParas wap = new WhereAndParas();
		if(anasGoal.getId() != null && anasGoal.getId() > 0){
			sql.append(" and o.id = ?");
			list.add(anasGoal.getId());
		}
		if(anasGoal.getVersion() != null && anasGoal.getVersion() > 0){
			sql.append(" and o.version = ?");
			list.add(anasGoal.getVersion());
		}
		if(StringUtils.isNotEmpty(anasGoal.getConcrete())){
			sql.append(" and o.concrete like ?");
			list.add("%"+anasGoal.getConcrete()+"%");
		}
		if(anasGoal.getFinish() != null && anasGoal.getFinish() > 0){
			sql.append(" and o.finish = ?");
			list.add(anasGoal.getFinish());
		}
		if(StringUtils.isNotEmpty(anasGoal.getGoalCycle())){
			sql.append(" and o.goalCycle like ?");
			list.add("%"+anasGoal.getGoalCycle()+"%");
		}
		if(StringUtils.isNotEmpty(anasGoal.getGoalState())){
			sql.append(" and o.goalState like ?");
			list.add("%"+anasGoal.getGoalState()+"%");
		}
		if(StringUtils.isNotEmpty(anasGoal.getGoalType())){
			sql.append(" and o.goalType like ?");
			list.add("%"+anasGoal.getGoalType()+"%");
		}
		if(anasGoal.getNum() != null && anasGoal.getNum() > 0){
			sql.append(" and o.num = ?");
			list.add(anasGoal.getNum());
		}
		if(anasGoal.getCreateUser() != null && anasGoal.getCreateUser() > 0){
			sql.append(" and o.create_user = ?");
			list.add(anasGoal.getCreateUser());
		}
		if(anasGoal.getWeek() != null && anasGoal.getWeek() > 0){
			sql.append(" and o.week = ?");
			list.add(anasGoal.getWeek());
		}
		wap.setWhere(sql.toString());
		wap.setParas(list.toArray());
		return wap;
	}
}
