package com.myapp.domain;

public class Admin
{
	private int id;
	private String username;
	private String password;
    private int category_id;
    private String realname;
    private String description;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
    
    public int getCategory_id()
    {
        return category_id;
    }
    
    public void setCategory_id(int category_id)
    {
        this.category_id = category_id;
    }
    
    public String getRealname()
    {
        return realname;
    }
    
    public void setRealname(String realname)
    {
        this.realname = realname;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
}
