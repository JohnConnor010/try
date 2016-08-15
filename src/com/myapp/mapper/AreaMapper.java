package com.myapp.mapper;

import com.myapp.domain.Area;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by JohnC on 2016-08-04.
 */
public interface AreaMapper
{
    @Results({
        @Result(property = "area_id",column = "area_id"),
            @Result(property = "name",column = "name"),
            @Result(property = "code",column = "code"),
            @Result(property = "parentCode",column = "ParentCode")
    })
    @Select("SELECT * FROM leiju_area WHERE ParentCode=#{parentCode}")
    List<Area> getAreasByParentCode(String parentCode);
}
