package com.example.shiro;


import com.example.entity.DAUser;
import com.example.service.DAUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @version 1.0
 * @date 2020/5/30 11:07
 */
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private DAUserService daUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权开始了！");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证开始了！");
        //开始校验用户名和密码
        //取出令牌信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //登录验证分两个步骤：1、查询用户是否存在；

        //登录页面传来的用户名
        String email = token.getUsername();
        DAUser daUser = daUserService.selectUserByEmail(email);
        if(daUser == null){
            return null;
        }
        //2、查询密码是否正确。
        String password = daUser.getPassword();
        //Object principal, Object credentials,  ByteSource byteSource, String realmName
        String salt = daUser.getSalt();
        ByteSource byteSource = ByteSource.Util.bytes(salt);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(daUser,password, byteSource,getName());
        return simpleAuthenticationInfo;
    }
}
