package cn.hjiabin.bos.realm;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hjiabin.bos.domain.system.Permission;
import cn.hjiabin.bos.domain.system.Role;
import cn.hjiabin.bos.domain.system.User;
import cn.hjiabin.bos.service.system.IPermissionService;
import cn.hjiabin.bos.service.system.IRoleService;
import cn.hjiabin.bos.service.system.IUserService;

@Service("bosRealm")
public class BosRealm extends AuthorizingRealm {

	@Autowired
	private IUserService userServiceImpl;
	
	@Autowired
	private IRoleService roleServiceImpl;
	
	@Autowired
	private IPermissionService permissionServiceImpl;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		System.out.println("授权管理");
		
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		
		List<Role> roles = roleServiceImpl.findByUser(user);
		for (Role role : roles) {
			authorizationInfo.addRole(role.getKeyword());
		}
		
		List<Permission> permissions = permissionServiceImpl.findByUser(user);
		for (Permission permission : permissions) {
			authorizationInfo.addStringPermission(permission.getKeyword());
		}
		
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("认证管理");
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		User user = userServiceImpl.findByUsername(usernamePasswordToken.getUsername());
		if(user == null){
			return null;
		}else {
			return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
		}
	}

}
