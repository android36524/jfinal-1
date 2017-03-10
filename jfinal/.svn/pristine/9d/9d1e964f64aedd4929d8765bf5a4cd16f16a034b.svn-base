package com.xiaoan.wlt.shiro;

import com.xiaoan.wlt.utils.ShiroUtils;

public class UserContext {

	public static Integer getShopsInfo(){
		try {
			return Integer.valueOf(ShiroUtils.getPrincipalProperty("shopsInfo").toString());
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String getUserName(){
		return ShiroUtils.getPrincipalProperty("userName").toString();
	}
	
	public static String getUserCode(){
		return ShiroUtils.getPrincipalProperty("userCode").toString();
	}
	
	public static String getUserType(){
		return ShiroUtils.getPrincipalProperty("userType").toString();
	}

	
}
