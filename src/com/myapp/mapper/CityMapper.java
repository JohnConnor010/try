package com.myapp.mapper;

import com.myapp.domain.City;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by JohnC on 2016-08-04.
 */
public interface CityMapper
{
    @Results({
        @Result(property = "city_id",column = "city_id"),
            @Result(property = "name",column = "name"),
            @Result(property = "code",column = "code"),
            @Result(property = "parentCode",column = "ParentCode")
    })
    @Select("SELECT * FROM leiju_city WHERE ParentCode=#{parentCode}")
    List<City> getCitiesByParentCode(String parentCode);
}
