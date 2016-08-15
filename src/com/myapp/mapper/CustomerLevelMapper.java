package com.myapp.mapper;

import com.myapp.domain.CustomerLevel;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by JohnC on 2016-08-06.
 */
public interface CustomerLevelMapper
{
    @Select("SELECT * FROM customer_level")
    @Results({
        @Result(property = "id",column = "id"),
            @Result(property = "level",column = "level"),
            @Result(property = "name",column = "name")
    })
    List<CustomerLevel> getAll();
}
