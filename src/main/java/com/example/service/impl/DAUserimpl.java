package com.example.service.impl;

import com.example.entity.DAUser;
import com.example.repostitory.DAUserMapper;
import com.example.service.DAUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DAUserimpl implements DAUserService {
    @Autowired
    private DAUserMapper daUserMapper;

    //根据邮箱查找用户
    @Override
    public DAUser selectUserByEmail(String email) {
        return daUserMapper.selectUserByEmail(email);
    }
    //查询所有用户
    @Override
    public PageInfo<DAUser> selectAllUser(int page, int limit) {
        return null;
    }
    //保存添加的用户
    @Override
    public int insertSelective(DAUser record) {
        return 0;
    }
    //删除用户信息
    @Override
    public int delUserByID(List<String> ids) {
        return 0;
    }

    //修改用户的信息（可以不修改密码）
    @Override
    public int updateByPrimaryKeySelective(DAUser record) {
        return 0;
    }


}
