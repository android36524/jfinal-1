package com.xiaoan.wlt.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;
import com.jfinal.log.Log;

public class Para2ActionHandler extends Handler {
	private final List<String> controllerKeys;
	private static final Log log = Log.getLog(Para2ActionHandler.class);

	public Para2ActionHandler(List<String> controllerKeys) {
		this.controllerKeys = controllerKeys;
	}

	@Override
	public void handle(String target, HttpServletRequest request,
			HttpServletResponse response, boolean[] isHandled) {
		String copy = target;
		for (String key : controllerKeys) {
			if (target.indexOf(key) >= 0) {
				String actionName = request.getParameter("action");
				if (actionName != null && !"".equals(actionName.trim())) {
					target += ("/" + actionName);
				}
				if (log.isDebugEnabled()) {
					log.debug(copy + " converted to " + target);
				}
			}
		}
		next.handle(target, request, response, isHandled);
	}

}
