package com.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.domain.CorporateInfo;
import com.myapp.mapper.CorporateInfoMapper;
import com.sun.deploy.net.HttpRequest;
import org.apache.ibatis.session.SqlSession;

import com.myapp.domain.UserInfo;
import com.myapp.mapper.MyBatisUtil;
import com.myapp.mapper.UserInfoMapper;


/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/register.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String telephone = request.getParameter("telephone_part1") + "-" + request.getParameter("telephone_part2");
		String[] sources = request.getParameterValues("source");
		List<String> list = new ArrayList<>(Arrays.asList(sources));
		String otherSource = request.getParameter("other_source");
		String summary = request.getParameter("summary");
        String ip_address = getIPAddress(request);
        String ip_region = Taobao_IP.getRegion(ip_address);
		if(otherSource != null)
		{
			list.add(otherSource);
		}
		String source = String.join(",", list);
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try
		{
			CorporateInfoMapper corInfoMapper = session.getMapper(CorporateInfoMapper.class);
			Object lastId = corInfoMapper.getMaxIdFromCorporateInfo();
			int _lastId = 1;
			if(lastId != null)
			{
				_lastId = Integer.valueOf(lastId.toString());
			}
			CorporateInfo corInfo = new CorporateInfo();
			DecimalFormat df = new DecimalFormat("0000");
			String code = df.format(_lastId);
			corInfo.setCode(code);
            corInfo.setIndustry("-1,-1,-1,-1");
			corInfoMapper.insert(corInfo);
			UserInfoMapper mapper = session.getMapper(UserInfoMapper.class);
			UserInfo userInfo = new UserInfo();
			userInfo.setName(name.toLowerCase());
			userInfo.setMobile(mobile);
			userInfo.setTelephone(telephone);
			userInfo.setSummary(summary);
			userInfo.setSource(source);
			userInfo.setIs_new(Short.valueOf("1"));
			Timestamp add_time = Timestamp.from(Instant.now());
			userInfo.setAdd_time(add_time);
			userInfo.setCorporate_id(corInfo.getId());
            userInfo.setIp_address(ip_address);
            userInfo.setIp_region(ip_region);
			mapper.insert(userInfo);
			session.commit();
			out.println("1");
            
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			out.println("0");
		}
		finally
		{
			session.close();
		}
		
		
	}
	public static String getIPAddress(HttpServletRequest request)
    {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
