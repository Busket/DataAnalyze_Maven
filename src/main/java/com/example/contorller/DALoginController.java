package com.example.contorller;

import com.example.service.DAUserService;
import com.example.util.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.Map;


@Controller
public class DALoginController {
    @Autowired
    private DAUserService daUserService;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(String email, String password, Model model){
        System.out.println("邮箱："+ email);
        System.out.println("密码："+ password);
        //获取shiro的主体
        Subject subject = SecurityUtils.getSubject();
        //构建用户登录令牌
        UsernamePasswordToken token = new UsernamePasswordToken(email, password);
        try{
            subject.login(token);
        }catch (UnknownAccountException e){
            model.addAttribute("msg","用户名错误");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return  "login";
        }
        //放入用户名
        model.addAttribute("loginEmail",email);

//        //hzk 菜单管理
//        //放入所有的菜单，根据当前登录的用户
//
//        List<LayuiTableTree> menus = tMenuBiz.selectAllMenuByName(name);
//        model.addAttribute("menus",menus);
        return "index";
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }



}
