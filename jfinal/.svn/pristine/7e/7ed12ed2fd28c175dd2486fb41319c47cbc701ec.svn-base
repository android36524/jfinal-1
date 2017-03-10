package com.xiaoan.wlt.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Page;

/**
 * BaseController
 */
public abstract class BaseController extends Controller {

	public void setPageAttr(Page page) {
		this.setAttr("page", page);
	}

	public void setAttrMsg(String msg) {
		this.setAttr("msg", msg);
	}

	public void setAttrErrMsg(String msg) {
		this.setAttr("errMsg", msg);
	}

	public void setErrMsg(String msg) {
		this.setAttr("errMsg", msg);
	}

	public void setSysErr() {
		setErrMsg("系统异常！");
	}
}
