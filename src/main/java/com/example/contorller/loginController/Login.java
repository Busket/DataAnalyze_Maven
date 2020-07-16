package com.example.contorller.loginController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Login {
//    @Autowired
//    private TMenuBiz tMenuBiz;
//
//    @RequestMapping("/toLogin")
//    public String toLogin(){
//        return "login";
//    }
//
//    @RequestMapping("/login")
//    public String login(String name, String password, Model model){
//        System.out.println("姓名："+ name);
//        System.out.println("密码："+ password);
//        //获取shiro的主体
//        Subject subject = SecurityUtils.getSubject();
//        //构建用户登录令牌
//        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
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
//        model.addAttribute("loginName",name);
//
//        //hzk 菜单管理
//        //放入所有的菜单，根据当前登录的用户
//
//        List<LayuiTableTree> menus = tMenuBiz.selectAllMenuByName(name);
//        model.addAttribute("menus",menus);
//        return "index";
//    }
//
//    @RequestMapping("/logout")
//    public String logout(){
//        Subject subject = SecurityUtils.getSubject();
//        subject.logout();
//        return "login";
//    }
}
