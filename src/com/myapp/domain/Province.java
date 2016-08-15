package com.myapp.domain;

/**
 * Created by JohnC on 2016-08-04.
 */
public class Province
{
    private int province_id;
    private String name;
    private String code;
    
    public int getProvince_id()
    {
        return province_id;
    }
    
    public void setProvince_id(int province_id)
    {
        this.province_id = province_id;
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
}
