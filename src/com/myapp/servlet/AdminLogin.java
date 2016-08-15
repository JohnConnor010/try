package com.myapp.servlet;

import com.myapp.mapper.AdminMapper;
import com.myapp.mapper.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/admin_login")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/admin_login.html").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        try
        {
            AdminMapper adminMapper = session.getMapper(AdminMapper.class);
            String pwd = adminMapper.getPasswordByName(username);
            if(password.equals(pwd))
            {
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("username",username);
                out.print(1);
            }
            else
            {
                out.print(0);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            out.print(-1);
        }
        finally
        {
            session.close();
        }
    }

}
