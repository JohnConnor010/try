package com.myapp.mapper;

import com.myapp.domain.Province;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by JohnC on 2016-08-04.
 */
public interface ProvinceMapper
{
    @Results({
        @Result(property = "province_id",column = "province_id"),
            @Result(property = "name",column = "name"),
            @Result(property = "code",column = "code")
    })
    @Select("SELECT * FROM leiju_province")
    List<Province> selectAllProvinces();
}
