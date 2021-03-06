package com.xiaoan.wlt.controller.admin.system;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Record;
import com.xiaoan.wlt.controller.BaseController;
import com.xiaoan.wlt.model.sys.UserSalesman;

public class RankingController extends BaseController{

	private static String RANK = "/admin/system/ranking/rank.jsp";
	private static JSONObject BEFORE_SALES_RANK = new JSONObject();
	private static JSONObject BEFORE_NEW_CLIENT_RANK = new JSONObject();
	private static JSONObject BEFORE_DIALING_RANK = new JSONObject();
	
	// 名次上升下降情况
	private static JSONObject BEFORE_SALES_RANK_CHANGE = new JSONObject();
	private static JSONObject BEFORE_NEW_CLIENT_RANK_CHANGE = new JSONObject();
	private static JSONObject BEFORE_DIALING_RANK_CHANGE = new JSONObject();
	
	// 上个月前3名
	private static JSONObject LAST_MONTH_SALES3 = new JSONObject();
	private static JSONObject LAST_MONTH_NEW_CLIENT3 = new JSONObject();
	
	public void rank(){
		render(RANK);
	}
	
	public void ranking() {
		// 当前月
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH) + 1;
		String months = DateFormatUtils.format(calendar.getTime(), "yyyyMM");
		calendar.set(Calendar.DATE, 1);
		String startTime = DateFormatUtils.format(calendar.getTime(), "yyyy-MM-dd") + " 00:00:00";
		
		JSONArray result = new JSONArray();
		JSONArray json_arr = new JSONArray();
		List<Record> list = UserSalesman.dao.getRankData("SALES", months, startTime, null, month);
		int rankNum = 0;// 名次
		int flag = 1;// 上升标记 ：-1下降，1上升
		for(Record u : list) {
			rankNum ++;
			json_arr = new JSONArray();
			json_arr.add(rankNum);// 名次
			json_arr.add(u.get("name"));// 姓名
			json_arr.add(u.get("per"));// 完成进度百分比
			// 名次变化
			if(BEFORE_SALES_RANK.get(u.get("name")) != null) {
				// 排名判断
				flag = checkRank(u.get("name"), rankNum);
				// 移除现在数据
				BEFORE_SALES_RANK.remove(u.get("name"));
				BEFORE_SALES_RANK_CHANGE.remove(u.get("name"));
			} else {
				flag = 1;
			}
			// 添加新数据
			BEFORE_SALES_RANK.put((String) u.get("name"), rankNum);
			BEFORE_SALES_RANK_CHANGE.put((String) u.get("name"), flag);
			json_arr.add(flag);
			result.add(json_arr);
		}
		this.setAttr("sales",result.toString());
		// 新客户
		rankNum = 0;
		result = new JSONArray();
		list = UserSalesman.dao.getRankData("NEW_CLIENT", months, startTime, null, month);
		for(Record u : list) {
			rankNum ++;
			json_arr = new JSONArray();
			json_arr.add(rankNum);// 名次
			json_arr.add(u.get("name"));// 姓名
			json_arr.add(u.get("per"));// 完成进度百分比
			// 名次变化
			if(BEFORE_NEW_CLIENT_RANK.get(u.get("name")) != null) {
				// 排名判断
				flag = checkRank(u.get("name"), rankNum);
				// 移除现在数据
				BEFORE_NEW_CLIENT_RANK.remove(u.get("name"));
				BEFORE_NEW_CLIENT_RANK_CHANGE.remove(u.get("name"));
			} else {
				flag = 1;
			}
			// 添加新数据
			BEFORE_NEW_CLIENT_RANK.put((String) u.get("name"), rankNum);
			BEFORE_NEW_CLIENT_RANK_CHANGE.put((String) u.get("name"), flag);
			json_arr.add(flag);
			result.add(json_arr);
		}
		this.setAttr("newcl",result.toString());
		// 电话目标
		rankNum = 0;
		result = new JSONArray();
		list = UserSalesman.dao.getRankData("DIALING", months, startTime, null, month);
		for(Record u : list) {
			rankNum ++;
			json_arr = new JSONArray();
			json_arr.add(rankNum);// 名次
			json_arr.add(u.get("name"));// 姓名
			json_arr.add(u.get("per"));// 完成进度百分比
			// 名次变化
			if(BEFORE_DIALING_RANK.get(u.get("name")) != null) {
				// 排名判断
				flag = checkRank(u.get("name"), rankNum);
				// 移除现在数据
				BEFORE_DIALING_RANK.remove(u.get("name"));
				BEFORE_DIALING_RANK_CHANGE.remove(u.get("name"));
			} else {
				flag = 1;
			}
			// 添加新数据
			BEFORE_DIALING_RANK.put((String) u.get("name"), rankNum);
			BEFORE_DIALING_RANK_CHANGE.put((String) u.get("name"), flag);
			json_arr.add(flag);
			result.add(json_arr);
		}
		this.setAttr("dialing",result.toString());
		
		// 上个月销售前三、新客户前三
		String host = PropKit.get("host");
		calendar.add(Calendar.DATE, -1);
		int last_month = calendar.get(Calendar.MONTH) + 1;
		String last_months = DateFormatUtils.format(calendar.getTime(), "yyyyMM");
		String last_endTime = DateFormatUtils.format(calendar.getTime(), "yyyy-MM-dd") + " 00:00:00";
		calendar.set(Calendar.DATE, 1);
		String last_startTime = DateFormatUtils.format(calendar.getTime(), "yyyy-MM-dd") + " 00:00:00";
		if(LAST_MONTH_SALES3.get(last_months) == null) {
			JSONArray json_arr3 = new JSONArray();
			list = UserSalesman.dao.getRankData("SALES", last_months, last_startTime, last_endTime, last_month);
			for(int i = 0; i < 3; i++) {
				json_arr = new JSONArray();
				json_arr.add(i+1);
				json_arr.add(list.get(i).get("name"));
				json_arr.add(list.get(i).get("per"));
				json_arr.add(host + list.get(i).get("imageUrl"));
				json_arr3.add(json_arr);
			}
			LAST_MONTH_SALES3.put(last_months, json_arr3);
			
			json_arr3 = new JSONArray();
			list = UserSalesman.dao.getRankData("NEW_CLIENT", last_months, last_startTime, last_endTime, last_month);
			for(int i = 0; i < 3; i++) {
				json_arr = new JSONArray();
				json_arr.add(i+1);
				json_arr.add(list.get(i).get("name"));
				json_arr.add(list.get(i).get("per"));
				json_arr.add(host + list.get(i).get("imageUrl"));
				json_arr3.add(json_arr);
			}
			LAST_MONTH_NEW_CLIENT3.put(last_months, json_arr3);
		}
		result = new JSONArray();
		result.add(LAST_MONTH_SALES3.get(last_months));
		result.add(LAST_MONTH_NEW_CLIENT3.get(last_months));
		
		this.setAttr("last", result.toString());
		
		// 新订单通知
		List<Record> list_record = UserSalesman.dao.getNewOrders();
		result = new JSONArray();
		for(Record u : list_record) {
			json_arr = new JSONArray();
			json_arr.add(u.get("shopsName"));
			json_arr.add(u.get("prodNum"));
			json_arr.add(u.get("orderTime").toString().substring(0, 19));
			result.add(json_arr);
		}
		this.setAttr("order", result.toString());
		
		// 新客户通知
		list_record = UserSalesman.dao.getNewClient();
		result = new JSONArray();
		for(Record u : list_record) {
			json_arr = new JSONArray();
			json_arr.add(u.get("userName"));
			json_arr.add(u.get("createTime").toString().substring(0, 19));
			json_arr.add(u.get("shopsName"));
			result.add(json_arr);
		}
		this.setAttr("newClient", result.toString());
		renderJson();
	}
	
	public int checkRank(Object key, int after) {
		int flag = 1;
		if(BEFORE_DIALING_RANK.get(key) != null) {
			if((int)BEFORE_DIALING_RANK.get(key) > after) {
				flag = 1;
			} else if((int)BEFORE_DIALING_RANK.get(key) < after) {
				flag = -1;
			} else if((int)BEFORE_DIALING_RANK.get(key) == after) {
				flag = (int) BEFORE_DIALING_RANK_CHANGE.get(key);
			}
		}
		return flag;
	}
}
