package com.xiaoan.wlt.controller.admin.weixin;

import java.util.Calendar;

import com.jfinal.kit.PropKit;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.xiaoan.wlt.common.api.KFapi;

/**
 * 微信客服
 * @author liangjiahong
 * @date 2016年10月31日
 */
public class KFController extends WeixinBaseController {

	private static String LIST = "/admin/weixin/kf/list.jsp";
	
	private static String GET_RECORD = "/admin/weixin/kf/getRecord.jsp";
	
	public void list(){
		Cache c = Redis.use(PropKit.get("weixin"));
		ApiResult ar = KFapi.getKfList();
		c.set("KFkey", ar.getJson());
		System.out.println(ar.toString().trim());
		setAttr("json",ar.toString().trim());
		render(LIST);
	}
	
	public void getRecord(){
		Cache c = Redis.use(PropKit.get("weixin"));
		long endtime = System.currentTimeMillis()/1000;
		 Calendar cal = Calendar.getInstance();  
	        cal.set(Calendar.HOUR_OF_DAY, 0);  
	        cal.set(Calendar.SECOND, 0);  
	        cal.set(Calendar.MINUTE, 0);  
	        cal.set(Calendar.MILLISECOND, 0);  
		long starttime = cal.getTimeInMillis()/1000;
		ApiResult ar = KFapi.getRecord(starttime, endtime, 50, 1);
		c.set("KF_GET_RECORD", ar.getJson());
		System.out.println(ar.toString().trim());
		setAttr("json",ar.toString().trim());
		render(GET_RECORD);
	}
	
}
