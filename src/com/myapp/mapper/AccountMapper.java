package com.myapp.mapper;

import com.myapp.domain.Account;
import org.apache.ibatis.annotations.*;

/**
 * Created by JohnC on 2016-08-06.
 */
public interface AccountMapper
{
    @Insert("INSERT INTO accounts (account_name,account_password,account_startdate,account_enddate,corporate_id) VALUES (#{account_name},#{account_password},#{account_startdate},#{account_enddate},#{corporate_id})")
    int insert(Account account);
    
    @Select("SELECT * FROM accounts WHERE account_startdate=#{startdate} AND account_enddate=#{enddate} ORDER BY id DESC LIMIT 1")
    @Results({
        @Result(property = "id",column = "id"),
            @Result(property = "account_name",column = "account_name"),
            @Result(property = "account_password",column = "account_password"),
            @Result(property = "account_startdate",column = "account_startdate"),
            @Result(property = "account_enddate",column = "account_enddate"),
            @Result(property = "corporate_id",column = "corporate_id")
    })
    Account findByStartDateAndEndDate(@Param("startdate") String startdate, @Param("enddate") String enddate);
    
    @Select("SELECT * FROM accounts WHERE corporate_id=#{id} ORDER BY id DESC LIMIT 1")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "account_name",column = "account_name"),
            @Result(property = "account_password",column = "account_password"),
            @Result(property = "account_startdate",column = "account_startdate"),
            @Result(property = "account_enddate",column = "account_enddate"),
            @Result(property = "corporate_id",column = "corporate_id")
    })
    Account findByCorporateIdLimitDESC(int id);
    
    @Select("SELECT account_password FROM accounts WHERE account_name=#{name} ORDER BY id DESC LIMIT 1")
    String findPassword(String name);
}
