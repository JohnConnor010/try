package com.myapp.mapper;

import com.myapp.domain.Log;
import org.apache.ibatis.annotations.Insert;

/**
 * Created by JohnC on 2016-08-06.
 */
public interface LogMapper
{
    @Insert("INSERT INTO log (author,msg,log_time) VALUES (#{author},#{msg},#{log_time})")
    int insert(Log log);
}
