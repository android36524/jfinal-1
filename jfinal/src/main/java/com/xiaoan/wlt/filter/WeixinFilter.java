package com.xiaoan.wlt.filter;

import static com.xiaoan.wlt.common.Constants.WECHAT_USER_OPENID_SESSION_NAME;
import static com.xiaoan.wlt.common.Constants.WECHAT_USER_SESSION_NAME;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.kit.PropKit;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.SnsAccessToken;
import com.jfinal.weixin.sdk.api.SnsAccessTokenApi;
import com.jfinal.weixin.sdk.api.SnsApi;
import com.xiaoan.wlt.common.Constants;
import com.xiaoan.wlt.model.t.WechatUsers;

public class WeixinFilter implements Filter {

	private Set<String> prefixIignores = new HashSet<String>();

	@Override
	public void destroy() {
	}

	// 授权地址
	private static String AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

	// 拉取用户信息地址
	// private static String USERINFO_URL =
	// "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
		/*
		 * if (session.getAttribute("userCode") != null &&
		 * session.getAttribute("openId") != null) { // session 微信用户存在不执行授权
		 * chain.doFilter(request, response); return; }
		 */
		// 忽略的链接
		if (canIgnore(request)) {
			chain.doFilter(request, response);
			return;
		}
		//**模拟微信用户//
		String openid = "ouzELv0OE8ssqBPQOXlSIxgA4T3Q";
		WechatUsers wechatUsers;
		wechatUsers = WechatUsers.dao.use("o2o").findFirst("select * from t_wechat_users where openid=? ", openid);
		session.setAttribute(Constants.WECHAT_USER_SESSION_NAME,
				wechatUsers);
		session.setAttribute(
				Constants.WECHAT_USER_OPENID_SESSION_NAME, openid);
		chain.doFilter(request, response);
		/////*/
		
		/**真实用户
		if (session.getAttribute(WECHAT_USER_SESSION_NAME) != null
				&& session.getAttribute(WECHAT_USER_OPENID_SESSION_NAME) != null) {
			// session 微信用户存在不执行授权
			chain.doFilter(request, response);
			return;
		} else {
			String code = request.getParameter("code");
			// 用户同意授权
			if (StringUtils.isNotBlank(code)) {
				SnsAccessToken snsAccessToken = SnsAccessTokenApi
						.getSnsAccessToken(PropKit.get("appId"),
								PropKit.get("appSecret"), code);
				String openid = snsAccessToken.getOpenid();
				ApiResult userInfo = SnsApi.getUserInfo(
						snsAccessToken.getAccessToken(),
						snsAccessToken.getOpenid());
				if (userInfo != null && userInfo.get("errcode") == null) {
					System.out.println("user's openid: " + openid);
					// 查询数据库中是否存在该用户
					WechatUsers wechatUser = WechatUsers.dao
							.use("o2o")
							.findFirst(
									"select * from t_wechat_users where openid=?",
									openid);
					// 不存在，添加
					if (wechatUser == null) {
						wechatUser = new WechatUsers();
						wechatUser.setGroupId(0);
					}
					wechatUser.setOpenid(openid);
					wechatUser.setNickname(userInfo.getStr("nickname"));
					if ("1".equals(String.valueOf(userInfo.get("sex")))) {
						wechatUser.setSexType("MALE");
					} else if ("2".equals(String.valueOf(userInfo.get("sex")))) {
						wechatUser.setSexType("FEMALE");
					} else {
						wechatUser.setSexType("UNKNOWN");
					}
					wechatUser.setProvince(userInfo.getStr("province"));
					wechatUser.setCity(userInfo.getStr("city"));

					wechatUser.setHeadimgurl(userInfo.getStr("headimgurl"));
					wechatUser.setUnionid(userInfo.getStr("unionid"));
					wechatUser.use("o2o");
					wechatUser.saveOrUpdate();
					session.setAttribute(Constants.WECHAT_USER_SESSION_NAME,
							wechatUser);
					session.setAttribute(
							Constants.WECHAT_USER_OPENID_SESSION_NAME, openid);
					String beforeAuthorizeURL = this
							.getBeforeAuthorizeURL(request);
					response.sendRedirect(beforeAuthorizeURL.toString());
				} else if (userInfo != null
						&& userInfo.getStr("errcode") != null) {
					// 授权错误，再次请求
					authorize(request, response);
					return;
				} else {
					throw new ServletException("无法获取用户信息");
				}

			} else {
				// 未授权
				authorize(request, response);
				return;
			}
		}
		//*/
	}

	private void authorize(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException,
			IOException {
		String authorizeURL = AUTHORIZE_URL
				.replace("APPID", PropKit.get("appId"))
				.replace("REDIRECT_URI", getBeforeAuthorizeURLEncoder(request))
				.replace("SCOPE", "snsapi_userinfo").replace("state", "123");
		System.out.println("用户授权 URL：" + authorizeURL.toString());
		response.sendRedirect(authorizeURL.toString());
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		String cp = config.getServletContext().getContextPath();
		String ignoresParam = config.getInitParameter("ignores");
		String[] ignoreArray = ignoresParam.split(",");
		for (String s : ignoreArray) {
			prefixIignores.add(cp + s);
		}
		PropKit.use("config.txt");
		ApiConfig apiConfig = new ApiConfig(PropKit.get("token"),
				PropKit.get("appId"), PropKit.get("appSecret"));
		ApiConfigKit.setThreadLocalApiConfig(apiConfig);
	}

	private boolean canIgnore(HttpServletRequest request) {
		String url = request.getRequestURI();
		for (String ignore : prefixIignores) {
			if (url.matches(ignore)) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unused")
	private String getBeforeAuthorizeURLRemoval(HttpServletRequest request)
			throws UnsupportedEncodingException {
		StringBuffer beforeAuthorizeURL = new StringBuffer(
				request.getRequestURL()); // 授权前的URL地址
		Map<String, String[]> map = request.getParameterMap();
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		int i = 0;
		while (it.hasNext()) {
			String key = it.next().toString();
			String value = ((String[]) map.get(key))[0];
			if (!key.equals("code") && !key.equals("state")
					&& !key.equals("ct")) {
				if (i == 0)
					beforeAuthorizeURL.append("?");
				else
					beforeAuthorizeURL.append("&");
				beforeAuthorizeURL.append(key).append("=").append(value);
			}
		}
		if (i == 0) {
			beforeAuthorizeURL.append("?ct=").append(new Date().getTime());
		}
		return URLEncoder.encode(beforeAuthorizeURL.toString(), "UTF-8");
	}

	private String getBeforeAuthorizeURL(HttpServletRequest request)
			throws UnsupportedEncodingException {
		StringBuffer beforeAuthorizeURL = new StringBuffer(
				request.getRequestURL()); // 授权前的URL地址
		String queryString = request.getQueryString();
		if (StringUtils.isNotEmpty(queryString)) {
			beforeAuthorizeURL.append("?").append(queryString);
		} else {
			beforeAuthorizeURL.append("?ct=").append(new Date().getTime());
		}
		return beforeAuthorizeURL.toString();
	}

	private String getBeforeAuthorizeURLEncoder(HttpServletRequest request)
			throws UnsupportedEncodingException {
		StringBuffer beforeAuthorizeURL = new StringBuffer(
				request.getRequestURL()); // 授权前的URL地址
		String queryString = request.getQueryString();
		if (org.apache.commons.lang3.StringUtils.isNotEmpty(queryString)) {
			beforeAuthorizeURL.append("?").append(queryString);
		} else {
			beforeAuthorizeURL.append("?ct=").append(new Date().getTime());
		}
		return URLEncoder.encode(beforeAuthorizeURL.toString(), "UTF-8");
	}
}
