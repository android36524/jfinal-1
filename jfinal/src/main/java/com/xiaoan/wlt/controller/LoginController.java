package com.xiaoan.wlt.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jfinal.plugin.redis.Redis;

public class LoginController extends BaseController {
	private Log log = Log.getLog(LoginController.class);
	private static String LOGIN_URL = "/admin/login.jsp";

	public void index() {
		
		render(LOGIN_URL);
	}

	public void doLogin() {
		try {
			String username = this.getPara("userName");
			String password = this.getPara("password");
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username,password);
			subject.login(token);
			
			this.redirect("/admin/index");
		} catch (LockedAccountException e) {
			log.error(e.getMessage());
			index();
		} catch (org.apache.shiro.authc.AuthenticationException e) {
			log.error("用户名或者密码错误"+e.getMessage());
			setAttr("msg", "用户名或者密码错误");
			index();
		} catch (Exception e) {
			setAttr("msg", "系统异常");
			log.error(e.getMessage());
			index();
		}
	}
	
	@RequiresAuthentication
	public void doLogout(){
		Subject subject = SecurityUtils.getSubject();
		Redis.use("session").del((Object)subject.getPrincipals());
		subject.logout();
		render(LOGIN_URL);
	}
	
}
