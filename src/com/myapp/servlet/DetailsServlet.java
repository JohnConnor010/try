package com.myapp.servlet;

import com.myapp.domain.*;
import com.myapp.mapper.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JohnC on 2016-08-04.
 */
@WebServlet("/details")
public class DetailsServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String author = "admin";
        if(request.getSession() != null)
        {
            HttpSession httpSession = request.getSession();
            if(httpSession.getAttribute("username") != null)
            {
                author = httpSession.getAttribute("username").toString();
            }
            
        }
        
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        PrintWriter out = response.getWriter();
        String companyId = request.getParameter("hide_companyId");
        String company_name = request.getParameter("company_name");
        String level1 = request.getParameter("industry_level1");
        String level2 = request.getParameter("industry_level2");
        String level3 = request.getParameter("industry_level3");
        String level4 = request.getParameter("industry_level4");
        String company_industry = String.format("%s,%s,%s,%s",level1,level2,level3,level4);
        System.out.println(company_industry);
        String company_province = request.getParameter("company_province");
        String company_city = request.getParameter("company_city");
        String company_area = request.getParameter("company_area");
        String company_address = request.getParameter("company_address");
        String company_level = request.getParameter("company_level");
        String company_telephone = request.getParameter("company_telephone");
        String company_fax = request.getParameter("company_fax");
        String company_400Phone = request.getParameter("company_400Phone");
        String company_email = request.getParameter("company_email");
        String legalman_name = request.getParameter("legalman_name");
        String legalman_mobile = request.getParameter("legalman_mobile");
        String legalman_phone = request.getParameter("legalman_phone");
        String legalman_qq = request.getParameter("legalman_qq");
        String company_homepage = request.getParameter("company_homepage");
        CorporateInfo info = new CorporateInfo();
        info.setName(company_name);
        info.setEmail(company_email);
        info.setPhone(company_telephone);
        info.set_400Phone(company_400Phone);
        info.setAddress(company_address);
        info.setArea(company_area);
        info.setCity(company_city);
        info.setFax(company_fax);
        info.setIndustry(company_industry);
        info.setLegal_mobile(legalman_mobile);
        info.setLegal_name(legalman_name);
        info.setLegal_phone(legalman_phone);
        info.setLegal_qq(legalman_qq);
        info.setLevel(company_level);
        info.setOfficial_website(company_homepage);
        info.setProvince(company_province);
        info.setId(Integer.valueOf(companyId));
        info.setIs_complete(2);
        String account_name = request.getParameter("account_name");
        String account_password = request.getParameter("account_password");
        int provided = 1;
        if(!account_name.equals("") && !account_password.equals(""))
        {
            provided = 2;
            String starttime = request.getParameter("account_starttime");
            String endtime = request.getParameter("account_endtime");
            System.out.println(starttime + " " + endtime);
            AccountMapper accountMapper = session.getMapper(AccountMapper.class);
            Account account = accountMapper.findByStartDateAndEndDate(starttime,endtime);
            
            if(account == null)
            {
                account = new Account();
                account.setAccount_name(account_name);
                account.setAccount_password(account_password);
                account.setAccount_startdate(starttime);
                account.setAccount_enddate(endtime);
                account.setCorporate_id(Integer.valueOf(companyId));
                accountMapper.insert(account);
                LogMapper logMapper = session.getMapper(LogMapper.class);
                String msg = author + "给企业名为" + company_name + "的用户分配了账户名为" + account_name + "的账户和密码";
                logMapper.insert(new Log(author,msg,Timestamp.from(Instant.now())));
            }
        }
        info.setIs_provided(provided);
        try
        {
            CorporateInfoMapper mapper = session.getMapper(CorporateInfoMapper.class);
            int status = mapper.update(info);
            LogMapper logMapper = session.getMapper(LogMapper.class);
            String msg = author + "完善了关于" + company_name + "的企业资料";
            logMapper.insert(new Log(author,msg, Timestamp.from(Instant.now())));
            session.commit();
            out.print(status);
        }
        finally
        {
            session.close();
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession httpSession = request.getSession();
        if(httpSession.getAttribute("username") == null)
        {
            request.getRequestDispatcher("/admin_login.html").forward(request,response);
        }
        String id = request.getParameter("id");
        if(id == null)
        {
            id = "4";
        }
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        try
        {
            IndustryMapper industryMapper = session.getMapper(IndustryMapper.class);
            List<Industry> industries = industryMapper.findByPid(0);
            industries.add(0,new Industry()
            {
                {
                    setId(-1);
                    setName("选择行业");
                }
            });
            request.setAttribute("industries",industries);
            
            ProvinceMapper provinceMapper = session.getMapper(ProvinceMapper.class);
            List<Province> provinces = provinceMapper.selectAllProvinces();
            provinces.add(0,new Province(){
                {
                    setProvince_id(0);
                    setName("选择省份");
                    setCode("0");
                }
            });
            request.setAttribute("provinces",provinces);
    
            CustomerLevelMapper customerLevelMapper = session.getMapper(CustomerLevelMapper.class);
            List<CustomerLevel> levels = customerLevelMapper.getAll();
            request.setAttribute("levels",levels);
            
            CorporateInfoMapper corporateInfoMapper = session.getMapper(CorporateInfoMapper.class);
            CorporateInfo corporate = corporateInfoMapper.getCorporateInfoById(Integer.valueOf(id));
            request.setAttribute("corporate",corporate);
            
            if(corporate.getIndustry().split(",")[1] != "-1")
            {
                String pid = corporate.getIndustry().split(",")[0];
                List<Industry> level2_industries = industryMapper.findByPid(Integer.valueOf(pid));
                level2_industries.add(0,new Industry(){
                    {
                        setId(-1);
                        setName("选择行业");
                    }
                });
                request.setAttribute("level2_industries",level2_industries);
            }
            
            if(corporate.getIndustry().split(",")[2] != "-1")
            {
                String pid = corporate.getIndustry().split(",")[1];
                List<Industry> level3_industries = industryMapper.findByPid(Integer.valueOf(pid));
                
                request.setAttribute("level3_industries",level3_industries);
            }
            
            if(corporate.getIndustry().split(",")[3] != "-1")
            {
                String pid = corporate.getIndustry().split(",")[2];
                List<Industry> level4_industries = industryMapper.findByPid(Integer.valueOf(pid));
                request.setAttribute("level4_industries",level4_industries);
            }
            CityMapper cityMapper = session.getMapper(CityMapper.class);
            List<City> cities = cityMapper.getCitiesByParentCode(corporate.getProvince());
            cities.add(0,new City(){
                {
                    setCode("0");
                    setName("选择市");
                }
            });
            request.setAttribute("cities",cities);
            
            AreaMapper areaMapper = session.getMapper(AreaMapper.class);
            List<Area> areas = areaMapper.getAreasByParentCode(corporate.getCity());
            areas.add(0,new Area(){
                {
                    setCode("0");
                    setName("选择区");
                }
            });
            request.setAttribute("areas",areas);
            
            UserInfoMapper userInfoMapper = session.getMapper(UserInfoMapper.class);
            List<UserInfo> users = userInfoMapper.getUserInfoByCorporateId(corporate.getId());
            request.setAttribute("users",users);
            
            UserInfo user = userInfoMapper.findIsNew(Integer.valueOf(id));
            request.setAttribute("user",user);
            
            AccountMapper accountMapper = session.getMapper(AccountMapper.class);
            Account account = accountMapper.findByCorporateIdLimitDESC(corporate.getId());
            if(account == null)
            {
                account = new Account();
                account.setAccount_enddate("");
                account.setCorporate_id(corporate.getId());
                account.setAccount_startdate("");
                account.setAccount_name("");
                account.setAccount_password("");
            }
            request.setAttribute("account",account);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }
        request.getRequestDispatcher("/details.html").forward(request,response);
    }
}
