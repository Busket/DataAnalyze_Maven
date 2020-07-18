package com.example.contorller;

import com.example.entity.DAUser;
import com.example.service.DAUserService;
import com.example.util.ConstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class DARegisterController {
    @Autowired
    private DAUserService daUserService;
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
