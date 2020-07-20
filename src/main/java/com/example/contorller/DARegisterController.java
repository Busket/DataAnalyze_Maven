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

            mailService.sendSimpleMailMessage(daUser.getEmail(),"欢迎您使用我们的系统！","请单击以下链接进行确认。");

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
}
