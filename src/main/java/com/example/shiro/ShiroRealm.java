package com.example.shiro;


import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author HB
 * @version 1.0
 * @date 2020/5/30 11:07
 */
public class ShiroRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }

//    @Autowired
//    private TUserBiz tUserBizImpl;
//
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        System.out.println("授权开始了！");
//        return null;
//    }
//
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        System.out.println("认证开始了！");
//        //开始校验用户名和密码
//        //取出令牌信息
//        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//        //登录验证分两个步骤：1、查询用户是否存在；
//
//        //登录页面传来的用户名
//        String name = token.getUsername();
//        TUser tUser = tUserBizImpl.selectUserByName(name);
//        if(tUser == null){
//            return null;
//        }
//        //2、查询密码是否正确。
//        String password = tUser.getPassWord();
//        //Object principal, Object credentials,  ByteSource byteSource, String realmName
//        String salt = tUser.getSalt();
//        ByteSource byteSource = ByteSource.Util.bytes(salt);
//        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(tUser,password, byteSource,getName());
//        return simpleAuthenticationInfo;
//    }
}
