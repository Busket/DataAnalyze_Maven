package com.example.service;

import com.example.entity.DAUser;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DAUserService {
    //根据邮箱查找用户
    DAUser selectUserByEmail(String email);

    //查询所有用户
    PageInfo<DAUser> selectAllUser(int page, int limit);

    //注册用户（添加用户）
    int insertSelective(DAUser record);

    //删除用户信息
    int delUserByID( @Param("ids") List<String> ids);

    //修改用户的信息（可以不修改密码）
    int updateByPrimaryKeySelective(DAUser record);
    //获得token
    String generateJwtToken(DAUser daUser);
    //数据库更新token
    int updateToken(DAUser record);
    //登出，删除token
    int deleteLoginInfo(String email);
    //邮箱确认，通过邮箱查找激活码，再进行比对
    int checkActiveCodebyEmail(String email, String activecode);
    //邮箱确认成功后，更改激活码
    int changeActiveCode(String emai);
    //邮箱确认后添加确认时间，即修改Email_verified_at
    void changeActiveTime(String email);
}
