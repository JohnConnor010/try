package com.myapp.domain;

import java.sql.Timestamp;

/**
 * Created by JohnC on 2016-08-06.
 */
public class Log
{
    private int id;
    private String author;
    private String msg;
    private Timestamp log_time;
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public String getAuthor()
    {
        return author;
    }
    
    public void setAuthor(String author)
    {
        this.author = author;
    }
    
    public String getMsg()
    {
        return msg;
    }
    
    public void setMsg(String msg)
    {
        this.msg = msg;
    }
    
    public Timestamp getLog_time()
    {
        return log_time;
    }
    
    public void setLog_time(Timestamp log_time)
    {
        this.log_time = log_time;
    }
    
    public Log(String author, String msg, Timestamp log_time)
    {
        this.author = author;
        this.msg = msg;
        this.log_time = log_time;
    }
}
