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

//    int deleteByPrimaryKey(Integer userId);
//
//    int insert(DAUser record);
//
//    int insertSelective(TUser record);//保存添加的用户
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
