package com.acs.admin.config;

import com.acs.admin.ds.dao.SysUserDao;
import com.acs.admin.ds.entity.SysUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

// https://ddnd.cn/2019/02/02/springmvc-mybatis-shiro/
@Component
public class ACSRealm extends AuthorizingRealm {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Collection<Permission> permissions = loadPermissions(principals);
        authorizationInfo.addObjectPermissions(permissions);
        return authorizationInfo;
    }

    private Collection<Permission> loadPermissions(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        List<String> ps = sysUserDao.findAllPermissions(username);
        if (CollectionUtils.isEmpty(ps)) {
            return null;
        }
        return ps.stream()
                .distinct()
                .map(WildcardPermission::new)
                .collect(Collectors.toList());
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        SysUser user = sysUserDao.findByUsername(username);
        if (user == null) {
            throw new UnknownAccountException();
        }

        String pwd = user.getPassword();
        String password = new String(((char[]) token.getCredentials()));
        if (!pwd.equals(password)) {
            throw new IncorrectCredentialsException();
        }
        return new SimpleAuthenticationInfo(username, pwd, getName());
    }
}
