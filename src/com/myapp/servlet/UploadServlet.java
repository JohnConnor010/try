package com.myapp.servlet;

import com.myapp.mapper.CorporateInfoMapper;
import com.myapp.mapper.MyBatisUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.session.SqlSession;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JohnC on 2016-08-06.
 */
@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=utf-8");
        final String path = request.getServletContext().getRealPath("") + "\\upload";
        final Part filePart = request.getPart("fileInput");
        String _name = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        final String ext = FilenameUtils.getExtension(getFileName(filePart));
        final String fileName = _name + "." + ext;
        String companyId = request.getParameter("id");
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try
        {
            outputStream = new FileOutputStream(new File(path + File.separator + fileName));
            inputStream = filePart.getInputStream();
            int read = 0;
            byte[] bytes = new byte[1024];
            while((read = inputStream.read(bytes)) != -1)
            {
                outputStream.write(bytes,0,read);
            }
            Map<String,Object> map = new HashMap<String,Object>();
            String filePath = "/upload/" + fileName;
            map.put("status",1);
            map.put("path",filePath);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writer().writeValueAsString(map);
            SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
            try
            {
                CorporateInfoMapper mapper = session.getMapper(CorporateInfoMapper.class);
                int status = mapper.updateImgUrl(filePath,Integer.valueOf(companyId));
                session.commit();
                out.print(json);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            finally
            {
                session.close();
            }
    
        }
        catch (FileNotFoundException e)
        {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String,Object> map = new HashMap<>();
            map.put("status",0);
            map.put("path","");
            String json = objectMapper.writer().writeValueAsString(map);
            out.print(json);
        }
        finally
        {
            if(outputStream != null)
            {
                outputStream.close();
            }
            if(inputStream != null)
            {
                inputStream.close();
            }
            if(out != null)
            {
                out.close();
            }
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        
    }
    private String getFileName(final Part part)
    {
        final String partHeader = part.getHeader("content-disposition");
        for(String content : part.getHeader("content-disposition").split(";"))
        {
            if(content.trim().startsWith("filename"))
            {
                return content.substring(content.lastIndexOf("=") + 1).trim().replace("\"","");
            }
        }
        return null;
    }
}
