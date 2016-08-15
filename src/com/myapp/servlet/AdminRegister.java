package com.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.myapp.domain.Admin;
import com.myapp.mapper.AdminMapper;
import com.myapp.mapper.MyBatisUtil;

/**
 * Servlet implementation class AdminRegister
 */
@WebServlet("/auditor_register")
public class AdminRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        if(httpSession.getAttribute("username") == null)
        {
            request.getRequestDispatcher("/admin_login.html").forward(request,response);
        }
	    String h2 = null;
        String h4 = null;
        String id = request.getParameter("id");
        String action = "add";
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        if(id == null)
        {
            h2 = "审核员注册";
            h4 = "创建新审核员";
            Admin admin = new Admin();
            request.setAttribute("admin",admin);
        }
        else
        {
            h2 = "编辑审核员";
            h4 = "编辑当前审核员";
            action = "edit";
            try
            {
                AdminMapper adminMapper = session.getMapper(AdminMapper.class);
                Admin admin = adminMapper.findById(Integer.valueOf(id));
                request.setAttribute("admin",admin);
            }
            finally
            {
                session.close();
            }
    
        }
        request.setAttribute("h2",h2);
        request.setAttribute("h4",h4);
        request.setAttribute("action",action);
		request.getRequestDispatcher("/admin_register.html").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirm_password = request.getParameter("confirm-password");
        String realname = request.getParameter("realname");
        String description = request.getParameter("description");
        String action = request.getParameter("hide_action");
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try
		{
			AdminMapper mapper = session.getMapper(AdminMapper.class);
            if(action.equals("add"))
            {
                Admin admin = new Admin();
                admin.setUsername(username);
                admin.setPassword(password);
                admin.setCategory_id(2);
                admin.setRealname(realname);
                admin.setDescription(description);
                mapper.insert(admin);
            }
            else if(action.equals("edit"))
            {
                String editId = request.getParameter("hide_editId");
                Admin admin = new Admin();
                admin.setId(Integer.valueOf(editId));
                admin.setUsername(username);
                admin.setPassword(password);
                admin.setCategory_id(2);
                admin.setRealname(realname);
                admin.setDescription(description);
                mapper.update(admin);
            }
			session.commit();
			out.print(1);
		}
		catch (Exception e) 
		{
			out.print(0);
		}
		finally
		{
			session.close();
		}
	}

}
