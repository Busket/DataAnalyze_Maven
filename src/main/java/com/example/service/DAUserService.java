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

    //保存添加的用户
    int insertSelective(DAUser record);

    //删除用户信息
    int delUserByID( @Param("ids") List<String> ids);

    //修改用户的信息（可以不修改密码）
    int updateByPrimaryKeySelective(DAUser record);
}
