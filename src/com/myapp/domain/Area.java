package com.myapp.domain;

/**
 * Created by JohnC on 2016-08-04.
 */
public class Area
{
    private int area_id;
    private String name;
    private String code;
    private String parentCode;
    
    public int getArea_id()
    {
        return area_id;
    }
    
    public void setArea_id(int area_id)
    {
        this.area_id = area_id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getCode()
    {
        return code;
    }
    
    public void setCode(String code)
    {
        this.code = code;
    }
    
    public String getParentCode()
    {
        return parentCode;
    }
    
    public void setParentCode(String parentCode)
    {
        this.parentCode = parentCode;
    }
}
