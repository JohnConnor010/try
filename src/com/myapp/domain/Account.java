package com.myapp.domain;

/**
 * Created by JohnC on 2016-08-06.
 */
public class Account
{
    private int id;
    private String account_name;
    private String account_password;
    private String account_startdate;
    private String account_enddate;
    private int corporate_id;
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public String getAccount_name()
    {
        return account_name;
    }
    
    public void setAccount_name(String account_name)
    {
        this.account_name = account_name;
    }
    
    public String getAccount_password()
    {
        return account_password;
    }
    
    public void setAccount_password(String account_password)
    {
        this.account_password = account_password;
    }
    
    public String getAccount_startdate()
    {
        return account_startdate;
    }
    
    public void setAccount_startdate(String account_startdate)
    {
        this.account_startdate = account_startdate;
    }
    
    public String getAccount_enddate()
    {
        return account_enddate;
    }
    
    public void setAccount_enddate(String account_enddate)
    {
        this.account_enddate = account_enddate;
    }
    
    public int getCorporate_id()
    {
        return corporate_id;
    }
    
    public void setCorporate_id(int corporate_id)
    {
        this.corporate_id = corporate_id;
    }
}
