package com.example.entity;

import lombok.Data;

import java.sql.Time;

@Data
public class DAUser {
    private int id;//用户id
    private String name;//用户名
    private String phone;//手机号
    private String email;//邮箱
    private String password;//密码
    private Time email_verified_at;//邮箱验证时间
    private String remember_token;//token
    private Time creat_at;//账号注册时间
    private Time update_at;//账号更新时间
    //头像...
}
