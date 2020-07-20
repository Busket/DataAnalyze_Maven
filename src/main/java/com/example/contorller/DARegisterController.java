package com.example.contorller;

import com.example.entity.DAUser;
import com.example.service.DAUserService;
import com.example.service.MailService;
import com.example.util.ConstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class DARegisterController {
    @Autowired
    private DAUserService daUserService;//用户的service层
    @Autowired
    MailService mailService;//用于发送确认邮件
    //用户注册
    @RequestMapping("/register")
    public Object register(DAUser daUser){
        System.out.println("注册");
        int i= daUserService.insertSelective(daUser);
        //这个应该是用来检测插入是否成功的
        Map map =new HashMap<>();
        if(i>0)
        {
            map.put("code", ConstantUtils.successCode);
            map.put("message", ConstantUtils.insertSuccessMsg);
            //注册成功后，发送确认邮件

            //mailService.sendSimpleMailMessage(daUser.getEmail(),"欢迎您使用我们的系统！","请单击以下链接进行确认。");
            //发送带有邮箱以及激活码的链接，点击后进行确认操作
//            mailService.sendMimeMail(daUser.getEmail(),"欢迎您使用我们的系统！","请单击以下链接进行确认。\n" +
//                   "<a href=\"http://localhost:8080/emailconfirm?email="+daUser.getEmail()+"&activecode="+daUser.getActivecode()+"\">激活请点击:这里</a>");
            mailService.sendMimeMail(daUser.getEmail(),"欢迎您使用我们的系统！","请复制访问以下链接进行确认。\nhttp://localhost:8080/emailconfirm?email="+daUser.getEmail()+"&activecode="+daUser.getActivecode());
        }
        else
        {
            map.put("code", ConstantUtils.failCode);
            map.put("message",ConstantUtils.insertFailMsg);
        }
        //System.out.println(i);
        return map;
        //return "login";
    }

    @RequestMapping("/emailconfirm")
    public Object emailconfirm(String email,String activecode){
        System.out.println(email+"邮箱确认");
        int i= daUserService.checkActiveCodebyEmail(email,activecode);//验证激活码是否正确
        //可能需要考虑到重复验证的问题，即已经验证了的，依旧去单击连接
       switch (i){
           case 1:{
               System.out.println("验证成功！");
               if(daUserService.changeActiveCode(email)==1){//确认邮箱成功后，将激活码改为Active
                   daUserService.changeActiveTime(email);//只有在激活码修改成功后才能对其注册时间进行修改
                   return "confirmsuccess";//确认成功界面
               }
           }
           case 0:{
               System.out.println("验证失败！");
               return "confirmfail";//确认失败界面
           }
           case -1:{
               System.out.println("重复验证");
               return "confirmexpire";//确认重复界面（提醒无需重复点击）
           }
           default:return "error";//错误界面（一般不会用到）
       }
    }
}
