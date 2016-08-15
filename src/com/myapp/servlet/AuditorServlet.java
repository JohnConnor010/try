package com.myapp.servlet;

import com.myapp.domain.Admin;
import com.myapp.mapper.AdminMapper;
import com.myapp.mapper.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by JohnC on 2016-08-08.
 */
@WebServlet("/auditors")
public class AuditorServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession httpSession = request.getSession();
        if(httpSession.getAttribute("username") == null)
        {
            request.getRequestDispatcher("/admin_login.html").forward(request,response);
        }
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        try
        {
            AdminMapper mapper = session.getMapper(AdminMapper.class);
            List<Admin> auditors = mapper.findAllByCategoryId(2);
            request.setAttribute("auditors",auditors);
        }
        finally
        {
            session.close();
        }
        request.getRequestDispatcher("/auditor.html").forward(request,response);
    }
}
