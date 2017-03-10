package com.xiaoan.wlt.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.jfinal.log.Log;
import com.xiaoan.wlt.common.Response;
import com.xiaoan.wlt.model.erp.jxc.ShopsInfo;
import com.xiaoan.wlt.model.sys.Function;
import com.xiaoan.wlt.model.sys.Role;
import com.xiaoan.wlt.model.sys.RolePerm;
import com.xiaoan.wlt.model.sys.RoleUser;
import com.xiaoan.wlt.model.sys.User;
import com.xiaoan.wlt.utils.Common;

public class MyRealm extends AuthorizingRealm {
	private Log log = Log.getLog(MyRealm.class);

	/**
	 * 
	 * 认证回调函数,登录时调用.
	 * 
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
		// UsernamePasswordToken对象用来存放提交的登录信息
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		log.info("验证当前Subject时获取到token为：" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
		// 查出是否有此用户 
		User user = User.dao.findByUsername(token.getUsername());
		if (user != null) {
			// 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
			SimpleAuthenticationInfo info =  new SimpleAuthenticationInfo(user, user.getPwd(), getName());
			return info;
		}
		return null;
	}

	/**
	 * 
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 * 
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		log.info("##################执行Shiro权限认证##################");
		
		User user = (User) super.getAvailablePrincipal(principals);
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
//		if(user.getUserCode().equals("super")){
//			
//			Response re = Function.dao.findMenuList();
//			List<String> perm = new ArrayList<String>();
//			if(re.getData()!= null){
//				for(Function rp : (List<Function>)re.getData()){
//					perm.add(rp.getFuncCde());
//				}
//			}
//			
//			info.addRole("super");
//			info.addStringPermissions(perm);
//			log.info("已为用户["+user.getUserName()+"]赋予了["+"super"+"]角色和["+perm+"]权限");
//			return info;
//		}
//		
		List<RoleUser> list = RoleUser.dao.find("select * from sys_role_user where userId=?",user.getId());
		
		for(RoleUser ru : list){
			Role r = Role.dao.findById(ru.getRoleId());
			System.out.println(r.getRoleName());
			
			List<RolePerm> rpl = RolePerm.dao.find("select * from sys_role_perm where roleId=?",r.getId());
			
			List<String> perm = new ArrayList<String>();
			for(RolePerm rp : rpl){
				perm.add(rp.getFunctionId());
			}
			
			info.addRole(r.getRoleName());
			info.addStringPermissions(perm);
			log.info("已为用户["+user.getUserName()+"]赋予了["+r.getRoleName()+"]角色和["+perm+"]权限");
		}
		return info;
	}

	/**
	 * 
	 * 清除所有用户授权信息缓存.
	 * 
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}
}
