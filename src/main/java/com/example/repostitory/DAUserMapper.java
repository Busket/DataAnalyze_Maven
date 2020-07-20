package com.example.repostitory;

import com.example.entity.DAUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DAUserMapper {

    //根据邮箱查找用户
    DAUser selectUserByEmail(String email);

    //保存添加的用户
    int insertSelective(DAUser record);
    //保存（更新）token
    int updateToken(DAUser record);
    //登出
    int deletLoginInfo(String email);
    //邮件确认，通过邮箱查找激活码
    String checkActiveCodebyEmail(String email);
    //邮箱确认成功后，更改激活码
    void changeActiveCode(String email);
    //邮箱确认后，更改邮箱确认时间
    void changeActiveTime(DAUser record);

//    int deleteByPrimaryKey(Integer userId);
//
//    int insert(DAUser record);
//

//
//    TUser selectByPrimaryKey(Integer userId);
//
//    int updateByPrimaryKeySelective(TUser record);//修改用户的信息（可以不修改密码）
//
//    int updateByPrimaryKey(TUser record);
//
//    List<TUser> selectAllUser();//查询所有用户
//
//    int delUserByID( @Param("ids") List<String> ids); //删除选中的用户
//
//    TUser selectUserByName(String name);

}
