package com.xiaoan.wlt.common.api;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.weixin.sdk.api.AccessTokenApi;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.utils.HttpUtils;
import com.jfinal.weixin.sdk.utils.JsonUtils;

/**
 * 客服的接口
 * @author liangjiahong
 * @date 2016年10月31日
 */
public class KFapi {

	private static String GET_KF_LIST  = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=";

	/**
	 * 获取全部客服
	 * @return
	 */
	public static ApiResult getKfList(){
		String url = GET_KF_LIST + AccessTokenApi.getAccessTokenStr();
        String jsonResult = HttpUtils.get(url);
		return new ApiResult(jsonResult);
	}
	
	private static String GET_ONLINE_KF_LIST = "https://api.weixin.qq.com/cgi-bin/customservice/getonlinekflist?access_token=";
	/**
	 * 获取在线客服
	 * @return
	 */
	public static ApiResult getOnlineKfList(){
		String url = GET_ONLINE_KF_LIST + AccessTokenApi.getAccessTokenStr();
        String jsonResult = HttpUtils.get(url);
		return new ApiResult(jsonResult);
	}
	
	private static String KF_ACCOUNT = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=";
	/**
	 * 添加客服帐号
	 * kf_account	完整客服帐号，格式为：帐号前缀@公众号微信号，帐号前缀最多10个字符，必须是英文、数字字符或者下划线，后缀为公众号微信号，长度不超过30个字符
	 * @return
	 */
	public static ApiResult addKf(String kfAccount,String nickname){
		String url = KF_ACCOUNT + AccessTokenApi.getAccessTokenStr();
		Map<String, String> mapData = new HashMap<String, String>();
		mapData.put("kf_account", kfAccount);
		mapData.put("nickname", nickname);
		String jsonResult = HttpUtils.post(url, JsonUtils.toJson(mapData));
		return new ApiResult(jsonResult);
	}
	
	private static String INVITE_WORKER = "https://api.weixin.qq.com/customservice/kfaccount/inviteworker?access_token=";
	/**
	 * 邀请绑定客服帐号
	 */
	public static ApiResult inviteWorker(String kfAccount,String inviteWx){
		String url = INVITE_WORKER + AccessTokenApi.getAccessTokenStr();
		Map<String, String> mapData = new HashMap<String, String>();
		mapData.put("kf_account", kfAccount);
		mapData.put("invite_wx", inviteWx);
		String jsonResult = HttpUtils.post(url, JsonUtils.toJson(mapData));
		return new ApiResult(jsonResult);
	}
	
	private static String UPDATE_KF_INFO = "https://api.weixin.qq.com/customservice/kfaccount/update?access_token=";
	/**
	 * 设置客服信息
	 */
	public static ApiResult updateKfInfo(String kfAccount,String nickname){
		String url = UPDATE_KF_INFO + AccessTokenApi.getAccessTokenStr();
		Map<String, String> mapData = new HashMap<String, String>();
		mapData.put("kf_account", kfAccount);
		mapData.put("nickname", nickname);
		String jsonResult = HttpUtils.post(url, JsonUtils.toJson(mapData));
		return new ApiResult(jsonResult);
	}
	
	private static String DEL_KF = "https://api.weixin.qq.com/customservice/kfaccount/del?access_token=";
	/**
	 * 删除客服
	 * @param kfAccount
	 * @return
	 */
	public static ApiResult delKf(String kfAccount){
		String url = DEL_KF + AccessTokenApi.getAccessTokenStr() + "&kf_account="+kfAccount;
		String jsonResult = HttpUtils.get(url);
		return new ApiResult(jsonResult);
	}
	
	/**
	 * 获取客服聊天记录接口
	 * access_token	是	调用接口凭证
		starttime	是	查询开始时间，UNIX时间戳
		endtime	是	查询结束时间，UNIX时间戳，每次查询不能跨日查询
		pagesize	是	每页大小，每页最多拉取50条
		pageindex	是	查询第几页，从1开始
	 * @param kfAccount
	 * @return
	 */
	private static String GET_RECORD ="https://api.weixin.qq.com/customservice/msgrecord/getrecord?access_token=";
	public static ApiResult getRecord(Long starttime,Long endtime,int pagesize,int pageindex){
		String url = GET_RECORD + AccessTokenApi.getAccessTokenStr();
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("starttime", starttime);
		mapData.put("endtime", endtime);
		mapData.put("pagesize", pagesize);
		mapData.put("pageindex", pageindex);
		String jsonResult = HttpUtils.post(url, JsonUtils.toJson(mapData));
		return new ApiResult(jsonResult);
	}
	
	/**
	 * 创建会话
	 * openid	是	客户openid
		kf_account	是	完整客服账号，格式为：账号前缀@公众号微信号
		text	否	附加信息，文本会展示在客服人员的多客服客户端
		返回码	说明
		0	成功(no error)/会话已存在(session exsited)
		61458	客户正在被其他客服接待(customer accepted by xxx@xxxx)
		61459	客服不在线(kf offline)
	 */
	private static String CREATE ="https://api.weixin.qq.com/customservice/kfsession/create?access_token=";
	public static ApiResult create(String openid,String kf_account,String text){
		String url = CREATE + AccessTokenApi.getAccessTokenStr();
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("openid", openid);
		mapData.put("kf_account", kf_account);
		mapData.put("text", text);
		String jsonResult = HttpUtils.post(url, JsonUtils.toJson(mapData));
		return new ApiResult(jsonResult);
	}
	
	/**
	 * 关闭会话
	 * openid	是	客户openid
		kf_account	是	完整客服账号，格式为：账号前缀@公众号微信号
		text	否	附加信息，文本会展示在客服人员的多客服客户端
		返回码	说明
		0	成功(no error)/会话已存在(session exsited)
		61458	客户正在被其他客服接待(customer accepted by xxx@xxxx)

	 */
	private static String CLOSE =" https://api.weixin.qq.com/customservice/kfsession/close?access_token=";
	public static ApiResult close(String openid,String kf_account,String text){
		String url = CLOSE + AccessTokenApi.getAccessTokenStr();
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("openid", openid);
		mapData.put("kf_account", kf_account);
		mapData.put("text", text);
		String jsonResult = HttpUtils.post(url, JsonUtils.toJson(mapData));
		return new ApiResult(jsonResult);
	}
	
	/**
	 * 获取客户的会话状态
		参数	描述
		kf_account	正在接待的客服，为空表示没有人在接待
		createtime	会话接入的时间
	 */
	private static String GET_SESSION ="https://api.weixin.qq.com/customservice/kfsession/getsession?access_token=";
	public static ApiResult getSession(String openid){
		String url = GET_SESSION + AccessTokenApi.getAccessTokenStr() + "&openid="+openid;
		String jsonResult = HttpUtils.get(url);
		return new ApiResult(jsonResult);
	}
	
	/**
	 * 获取客服的会话列表
		参数	描述
		sessionlist	会话列表
		sessionlist.openid	客户openid
		sessionlist.createtime	会话创建时间，UNIX时间戳
	 */
	private static String GET_SESSION_LIST ="https://api.weixin.qq.com/customservice/kfsession/getsessionlist?access_token=";
	public static ApiResult getSessionList(String kf_account){
		String url = GET_SESSION_LIST + AccessTokenApi.getAccessTokenStr() + "&kf_account="+kf_account;
		String jsonResult = HttpUtils.get(url);
		return new ApiResult(jsonResult);
	}
	
	/**
	 * 获取未接入会话列表
		参数	描述
		count	未接入会话数量
		waitcaselist	未接入会话列表，最多返回100条数据
		waitcaselist.openid	客户openid
		waitcaselist.kf_account	指定接待的客服，为空表示未指定客服
		waitcaselist.createtime	用户来访时间，UNIX时间戳
	 */
	private static String GET_WAITCASE =" https://api.weixin.qq.com/customservice/kfsession/getwaitcase?access_token=";
	public static ApiResult getWaitcase(){
		String url = GET_WAITCASE + AccessTokenApi.getAccessTokenStr();
		String jsonResult = HttpUtils.get(url);
		return new ApiResult(jsonResult);
	}
	
}
