package com.xiaoan.wlt.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.xiaoan.wlt.model.sys.Log;
import com.xiaoan.wlt.utils.Common;
import com.xiaoan.wlt.utils.ShiroUtils;

/**
 * 日志记录
 * @author liangjiahong
 * @date 2016年10月28日
 */
public class LogInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		Controller controller = (Controller)inv.getController();  
        HttpServletRequest request = controller.getRequest();  
        
        String ctxpath = request.getContextPath();
		String url = request.getRequestURI().substring(ctxpath.length());
		if(url.indexOf("ranking") > -1) {
			// 排行版过滤 login、shiro
		} else {
			Log log = new Log();
			String browser = request.getHeader("user-agent");
			log.setBrowser(browser);
			log.setLink(url);
			log.setIpAddress(Common.getRealIpAddr(request));
			log.setPara(controller.getPara());
			//用户信息
			try {
				if(ShiroUtils.isUser()){
					log.setUserCde((String)ShiroUtils.getPrincipalProperty("userCode"));
					log.setUserName((String)ShiroUtils.getPrincipalProperty("userName"));
				}
			} catch (Exception e) {
				
			}
			log.setIsPass("Y");
			log.setCreateTime(new Date());
			log.setUpdateTime(new Date());
			log.setVersion(0);
	        log.save();
		}
        
        // 执行正常逻辑
        inv.invoke();
	}
}
