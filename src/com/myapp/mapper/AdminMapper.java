package com.myapp.mapper;

import org.apache.ibatis.annotations.*;

import com.myapp.domain.Admin;

import java.util.List;

public interface AdminMapper
{
	@Results({
		@Result(property = "id",column = "id"),
		@Result(property = "username",column = "username"),
		@Result(property = "password",column = "password"),
            @Result(property = "category_id",column = "category_id"),
            @Result(property = "realname",column = "realname"),
            @Result(property = "description",column = "description")
	})
	@Insert("INSERT INTO admin (username,password,category_id,realname,description) VALUES (#{username},#{password},#{category_id},#{realname},#{description})")
	int insert(Admin admin);
    
	@Select("SELECT password FROM admin WHERE username=#{name}")
    String getPasswordByName(@Param("name") String name);
    
    @Select("SELECT * FROM admin WHERE id=#{id}")
    Admin findById(int id);
    
    @Select("SELECT * FROM admin WHERE category_id=#{category_id}")
    List<Admin> findAllByCategoryId(int category_id);
    
    @Update("UPDATE admin SET username=#{username},password=#{password},realname=#{realname},description=#{description} WHERE id=${id}")
    int update(Admin admin);
    
    @Delete("DELETE FROM admin WHERE id=#{id}")
    int delete(int id);
	
}
