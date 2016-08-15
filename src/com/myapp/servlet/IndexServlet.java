package com.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myapp.mapper.AccountMapper;
import org.apache.ibatis.session.SqlSession;

import com.myapp.mapper.MyBatisUtil;
import com.myapp.mapper.UserInfoMapper;


/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/login.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try
		{
            AccountMapper mapper = session.getMapper(AccountMapper.class);
            String pwd = mapper.findPassword(name);
            if(pwd == null)
            {
                out.print(-1);
            }
            else
            {
                if(!pwd.equals(password))
                {
                    out.print(0);
                }
                else
                {
                    out.print(1);
                }
            }
		}
		finally
		{
			session.close();
		}
	}

}
