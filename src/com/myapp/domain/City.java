package com.myapp.domain;

/**
 * Created by JohnC on 2016-08-04.
 */
public class City
{
    private int city_id;
    private String name;
    private String code;
    private String parentCode;
    
    public int getCity_id()
    {
        return city_id;
    }
    
    public void setCity_id(int city_id)
    {
        this.city_id = city_id;
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
