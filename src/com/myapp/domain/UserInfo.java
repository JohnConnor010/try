package com.myapp.domain;

import java.sql.Timestamp;

public class UserInfo
{
	private int id;
	private String name;
	private String mobile;
	private String telephone;
	private String source;
	private String summary;
	private short is_new;
	private Timestamp add_time;
    private int category_id;
    private String ip_address;
    private String ip_region;
    
    public int getCategory_id()
    {
        return category_id;
    }
    
    public void setCategory_id(int category_id)
    {
        this.category_id = category_id;
    }
    
    private int corporate_id;
    private String email;
    private String qq;
    public int getId()
	
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getMobile()
	{
		return mobile;
	}
	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}
	public String getTelephone()
	{
		return telephone;
	}
	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}
	public String getSource()
	{
		return source;
	}
	public void setSource(String source)
	{
		this.source = source;
	}
	public String getSummary()
	{
		return summary;
	}
	public void setSummary(String summary)
	{
		this.summary = summary;
	}
	public short getIs_new()
	{
		return is_new;
	}
	public void setIs_new(short is_new)
	{
		this.is_new = is_new;
	}
	public Timestamp getAdd_time()
	{
		return add_time;
	}
	public void setAdd_time(Timestamp add_time)
	{
		this.add_time = add_time;
	}
	
	public int getCorporate_id()
	{
		return corporate_id;
	}
	
	public void setCorporate_id(int corporate_id)
	{
		this.corporate_id = corporate_id;
	}
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getQq()
    {
        return qq;
    }
    
    public void setQq(String qq)
    {
        this.qq = qq;
    }
    
    public String getIp_address()
    {
        return ip_address;
    }
    
    public void setIp_address(String ip_address)
    {
        this.ip_address = ip_address;
    }
    
    public String getIp_region()
    {
        return ip_region;
    }
    
    public void setIp_region(String ip_region)
    {
        this.ip_region = ip_region;
    }
}
