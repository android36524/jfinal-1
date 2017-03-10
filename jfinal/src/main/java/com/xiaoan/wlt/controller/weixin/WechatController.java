package com.xiaoan.wlt.controller.weixin;

import com.jfinal.kit.PropKit;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.kit.SignatureCheckKit;
import com.xiaoan.wlt.controller.BaseController;

public class WechatController extends BaseController {
	/**
	 * 接入微信公共平台，签名验证
	 */
	public void index() {
		ApiConfig apiConfig = new ApiConfig(PropKit.get("token"),PropKit.get("appId"),PropKit.get("appSecret"));
		ApiConfigKit.setThreadLocalApiConfig(apiConfig);
		String signature = getPara("signature");
		String timestamp = getPara("timestamp");
		String nonce = getPara("nonce");
		String echostr = getPara("echostr");
		if (SignatureCheckKit.me.checkSignature(signature, timestamp, nonce)) {
			renderText(echostr);
		}
	}
}
