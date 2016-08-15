package com.myapp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

import com.myapp.domain.UserInfo;



public interface UserInfoMapper
{
	@Results({
		@Result(property = "id",column = "id"),
		@Result(property = "name",column = "name"),
		@Result(property = "mobile",column = "mobile"),
		@Result(property = "telephone",column = "telephone"),
		@Result(property = "source",column = "source"),
		@Result(property = "summary",column = "source"),
		@Result(property = "is_new",column = "is_new"),
		@Result(property = "add_time",column = "add_time"),
            @Result(property = "email",column = "email"),
            @Result(property = "qq",column = "qq"),
            @Result(property = "ip_address",column = "ip_address"),
            @Result(property = "ip_region",column = "ip_region")
	})
	@Insert("INSERT INTO `leiju_try`.`user_info`(`name`,`mobile`,`telephone`,`source`,`summary`,`is_new`,`add_time`,`corporate_id`,`email`,`qq`,`ip_address`,`ip_region`)"
			+ "VALUES(#{name},#{mobile},#{telephone},#{source},#{summary},#{is_new},#{add_time},#{corporate_id},#{email},#{qq},#{ip_address},#{ip_region});")
	@Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
	int insert(UserInfo userInfo);
    
	@Select("SELECT COUNT(id) from user_info where mobile=#{mobile}")
	int getCountByMobile(String mobile);
    
	@Select("select password from user_info where name=#{name}")
	String getPasswordByName(String name);
    
	@Select("SELECT * FROM user_info order by id desc limit #{offset},#{limit}")
	List<UserInfo> getUserInfosByLimit(@Param("offset") int offset, @Param("limit") int limit);
    
	@Select("SELECT COUNT(id) FROM user_info")
	int countAll();
    
    @Select("SELECT * FROM user_info where corporate_id=#{id} ORDER BY id DESC")
    List<UserInfo> getUserInfoByCorporateId(int id);
    
    @Update("UPDATE `user_info` SET `name`=#{name}, `mobile`=#{mobile}, `telephone`=#{telephone}, `summary`=#{summary}," +
            " `corporate_id`=#{corporate_id}, `email`=#{email}, `qq`=#{qq}, WHERE (`id`=${id});")
    int updateUserInfo(UserInfo userInfo);
    
    @Select("SELECT * FROM user_info WHERE id=#{id}")
    UserInfo findById(int id);
    
    @Delete("DELETE FROM user_info where id=#{id}")
    int deleteById(int id);
    
    @Select("SELECT * FROM user_info WHERE is_new=1 AND corporate_id=#{corporate_id} limit 0,1")
    UserInfo findIsNew(int corporate_id);
    
    @Select("SELECT name,add_time,ip_address,ip_region FROM user_info WHERE is_new=1 AND corporate_id=#{corporate_id} limit 0,1")
    Map<String,Object> findNameIsNew(int corporate_id);
    
    @Select("SELECT corporate_id FROM user_info WHERE name=#{name}")
    Integer findCorporateIdByName(String name);
    
}
