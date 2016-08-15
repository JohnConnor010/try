package com.myapp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.myapp.domain.Category;
import com.myapp.mapper.CategoryMapper;
import com.myapp.mapper.MyBatisUtil;
import com.myapp.mapper.UserInfoMapper;

/**
 * Servlet implementation class TryListServlet
 */
@WebServlet("/trylist")
public class TryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        HttpSession httpSession = request.getSession();
        if(httpSession.getAttribute("username") == null)
        {
            request.getRequestDispatcher("/admin_login.html").forward(request,response);
        }
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try
		{
			CategoryMapper mapper = session.getMapper(CategoryMapper.class);
			List<Category> categories = mapper.getAllCategory();
			categories.add(0,new Category(){
				{
					setId(0);
					setName("选择分类");
				}
			});
			request.setAttribute("options", categories);
			
			UserInfoMapper userMapper = session.getMapper(UserInfoMapper.class);
			int rowCount = userMapper.countAll();
			int pageSize = 10;
			int totalPages = (int)Math.ceil((double)rowCount / pageSize);
			
			request.setAttribute("totalPages", totalPages);
		}
		finally
		{
			session.close();
		}
		request.getRequestDispatcher("/trylist.html").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
