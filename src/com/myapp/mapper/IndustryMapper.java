package com.myapp.mapper;

import com.myapp.domain.Industry;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by JohnC on 2016-08-10.
 */
public interface IndustryMapper
{
    @Select("SELECT * from leiju_hangye WHERE pid=#{pid}")
    @Results({
        @Result(property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "pid",column = "pic")
    })
    List<Industry> findByPid(@Param("pid") int pid);
    
    @Select("SELECT name FROM leiju_hangye WHERE id=${id}")
    String getNameById(@Param("id") int id);
}
