package com.example.contorller;

import com.example.entity.DAUser;
import com.example.service.DAUserService;

import jdk.nashorn.internal.runtime.Context;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@Controller
public class DALoginController {
    @Autowired
    private DAUserService daUserService;



    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

//    @RequestMapping("/login")
//    public String login(String email, String password, Model model){
//        System.out.println("邮箱："+ email);
//        System.out.println("密码："+ password);
//        //获取shiro的主体
//        Subject subject = SecurityUtils.getSubject();
//        //构建用户登录令牌
//        UsernamePasswordToken token = new UsernamePasswordToken(email, password);
//        System.out.println(token);
//        try{
//            subject.login(token);
//        }catch (UnknownAccountException e){
//            model.addAttribute("msg","用户名错误");
//            return "login";
//        }catch (IncorrectCredentialsException e){
//            model.addAttribute("msg","密码错误");
//            return  "login";
//        }
//        //放入用户名
//        model.addAttribute("loginEmail",email);
//
////        //hzk 菜单管理
////        //放入所有的菜单，根据当前登录的用户
////
////        List<LayuiTableTree> menus = tMenuBiz.selectAllMenuByName(name);
////        model.addAttribute("menus",menus);
//        return "index";
//    }


    @RequestMapping(value = "/login")
    public ResponseEntity<Void> login(String email, String password, HttpServletRequest request, HttpServletResponse response){
        Subject subject = SecurityUtils.getSubject();
        System.out.println(email);
        System.out.println(password);
        try {
            //将用户请求参数封装后，直接提交给Shiro处理
            UsernamePasswordToken token = new UsernamePasswordToken(email, password);
            subject.login(token);
            //Shiro认证通过后会将user信息放到subject内，生成token并返回
            DAUser daUser = (DAUser) subject.getPrincipal();
            String newToken = daUserService.generateJwtToken(daUser);
            response.setHeader("remember_token", newToken);
            //对token进行持久化
            DAUser record=new DAUser();
            record.setEmail(email);
            record.setRemember_token(newToken);
            daUserService.updateToken(record);

            System.out.println("验证成功，返回token");
            return ResponseEntity.ok().build();
        } catch (AuthenticationException e) {
            // 如果校验失败，shiro会抛出异常，返回客户端失败
            System.out.println("User "+ email+" login fail, Reason:" +e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping(value = "/logout")
    public ResponseEntity<Void> logout() {
        Subject subject = SecurityUtils.getSubject();
        if(subject.getPrincipals() != null) {
            DAUser daUser = (DAUser)subject.getPrincipals().getPrimaryPrincipal();
            daUserService.deleteLoginInfo(daUser.getEmail());//根据email删除token
            System.out.println("用户登出成功！");
        }
        SecurityUtils.getSubject().logout();
        return ResponseEntity.ok().build();
    }



}
