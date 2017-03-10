package com.xiaoan.wlt.base;

import java.util.ArrayList;
import java.util.List;

import cn.dreampie.web.handler.SkipHandler;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.plugin.shiro.ShiroInterceptor;
import com.jfinal.ext.plugin.shiro.ShiroPlugin3;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.tx.TxByMethodRegex;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.xiaoan.wlt.controller.IndexController;
import com.xiaoan.wlt.controller.LoginController;
import com.xiaoan.wlt.controller.admin.system.FunctionController;
import com.xiaoan.wlt.controller.admin.system.LogController;
import com.xiaoan.wlt.controller.admin.system.RankingController;
import com.xiaoan.wlt.controller.admin.system.RoleController;
import com.xiaoan.wlt.controller.admin.system.UserController;
import com.xiaoan.wlt.controller.admin.weixin.KFController;
import com.xiaoan.wlt.controller.erp.jxc.CategoryController;
import com.xiaoan.wlt.controller.erp.jxc.CustomerController;
import com.xiaoan.wlt.controller.erp.jxc.DepotController;
import com.xiaoan.wlt.controller.erp.jxc.IntoController;
import com.xiaoan.wlt.controller.erp.jxc.OutController;
import com.xiaoan.wlt.controller.erp.jxc.ProductController;
import com.xiaoan.wlt.controller.erp.jxc.ShopsInfoController;
import com.xiaoan.wlt.controller.erp.jxc.StockController;
import com.xiaoan.wlt.controller.erp.jxc.SupplierController;
import com.xiaoan.wlt.controller.upload.UploadController;
import com.xiaoan.wlt.controller.websocket.WebSocketController;
import com.xiaoan.wlt.controller.weixin.WechatController;
import com.xiaoan.wlt.controller.weixin.WeixinController;
import com.xiaoan.wlt.controller.zyk.ResourceTemplateController;
import com.xiaoan.wlt.controller.zyk.ShopsResourceController;
import com.xiaoan.wlt.handler.Para2ActionHandler;
import com.xiaoan.wlt.interceptor.LogInterceptor;
import com.xiaoan.wlt.model._MappingKit;

public class Start extends JFinalConfig {
	/**
	 * 供Shiro插件使用。
	 */
	private Routes routes;

	public void configConstant(Constants me) {
		PropKit.use("config.txt");
		me.setDevMode(true);
		me.setViewType(ViewType.JSP);
		// RequiresGuest，RequiresAuthentication，RequiresUser验证异常，返回HTTP401状态码
		me.setErrorView(401, "/admin/login.jsp");
		// RequiresRoles，RequiresPermissions授权异常,返回HTTP403状态码
		me.setErrorView(403, "/admin/403.html");
		me.setError404View("/404.html");
		me.setError500View("/500.html");
		// 设置上传基础路径
		me.setBaseUploadPath(PathKit.getWebRootPath() + "/upload_tmp");
	}

	public void configRoute(Routes me) {
		this.routes = me;
		me.add("/", IndexController.class);
		me.add("/admin", IndexController.class);
		me.add("/admin/login", LoginController.class);
		me.add("/admin/system/user", UserController.class);
		me.add("/admin/system/function", FunctionController.class);
		me.add("/admin/system/role", RoleController.class);
		me.add("/admin/system/log", LogController.class);
		me.add("/admin/system/ranking", RankingController.class);
		me.add("/ranking", RankingController.class);

		me.add("/admin/weixin/kf", KFController.class);

		me.add("/erp/jxc/shopsInfo", ShopsInfoController.class);
		me.add("/erp/jxc/customer", CustomerController.class);
		me.add("/erp/jxc/product", ProductController.class);
		me.add("/erp/jxc/supplier", SupplierController.class);
		me.add("/erp/jxc/depot", DepotController.class);
		me.add("/erp/jxc/into", IntoController.class);
		me.add("/erp/jxc/out", OutController.class);
		me.add("/erp/jxc/stock", StockController.class);
		me.add("/erp/jxc/category", CategoryController.class);
		
		
		me.add("/upload", UploadController.class);

		me.add("/webSocket", WebSocketController.class);
		// me.add("/msg", WeixinMsgController.class);
		// me.add("/api", WeixinApiController.class, "/api");

		me.add("/weixin", WeixinController.class);
		// 测试用，接入微信平台
		me.add("/wechat", WechatController.class);

		me.add("/ueditor", com.xiaoan.wlt.controller.UeditorController.class);

		me.add("/zyk/category", com.xiaoan.wlt.controller.zyk.CategoryController.class);
		me.add("/zyk/resourceTemplate", ResourceTemplateController.class);
		me.add("/zyk/shopsResource", ShopsResourceController.class);
	}

	public static C3p0Plugin createC3p0Plugin() {
		return new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"),
				PropKit.get("password").trim());
	}

	public static C3p0Plugin o2o() {
		return new C3p0Plugin(PropKit.get("o2ojdbcUrl"),
				PropKit.get("o2ouser"), PropKit.get("o2opassword").trim());
	}

	public void configPlugin(Plugins me) {
		C3p0Plugin C3p0Plugin = createC3p0Plugin();
		me.add(C3p0Plugin);

		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(C3p0Plugin);
		arp.setShowSql(true);
		me.add(arp);
		// 所有配置在 MappingKit 中搞定
		_MappingKit.mapping(arp);

		// o2o数据库连接
		C3p0Plugin o2o = o2o();
		me.add(o2o);
		// 配置ActiveRecord插件
		ActiveRecordPlugin o2oarp = new ActiveRecordPlugin("o2o", o2o);
		o2oarp.setShowSql(true);
		me.add(o2oarp);
		
		//加载redis插件
		RedisPlugin weinix=new RedisPlugin(PropKit.get("weixin"),PropKit.get("redisUrl"),PropKit.getInt("redisPort"),PropKit.get("redisPassword"));
		me.add(weinix);
		RedisPlugin redisCache = new RedisPlugin(PropKit.get("redisSession"),
				PropKit.get("redisUrl"), PropKit.getInt("redisPort"),
				PropKit.get("redisPassword"));
		me.add(redisCache);
		
		//加载Shiro插件
		//me.add(new ShiroPlugin(routes));
		// 2017-03-08 重写了 ShiroPlugin
		ShiroPlugin3 shiroPlugin = new ShiroPlugin3(this.routes);
		shiroPlugin.setLoginUrl("/admin/login");
		shiroPlugin.setSuccessUrl("/admin/index");
		shiroPlugin.setUnauthorizedUrl("/admin/403.jsp");
		me.add(shiroPlugin);
	}

	public void configInterceptor(Interceptors me) {
		me.add(new LogInterceptor());
		me.add(new ShiroInterceptor());
		me.add(new TxByMethodRegex(
				"(.*save*.*|.*update*.*|.*delete*.*|.*Update*.*)"));
	}

	public void configHandler(Handlers me) {
		me.add(new SkipHandler("/ws/**"));// 跳过该目录
		me.add(new ContextPathHandler());
		// 处理ueditor的请求
		List<String> controllerKeys = new ArrayList<String>();
		controllerKeys.add("/ueditor");
		me.add(new Para2ActionHandler(controllerKeys));
	}

	@Override
	public void configEngine(Engine me) {
		// TODO Auto-generated method stub
		
	}
}
