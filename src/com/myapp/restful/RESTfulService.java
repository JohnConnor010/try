package com.myapp.restful;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.myapp.domain.*;
import com.myapp.mapper.*;
import org.apache.ibatis.session.SqlSession;

@Path("service")
public class RESTfulService
{
	@POST
	@Path("check/mobile")
	@Produces(MediaType.TEXT_PLAIN)
	public int checkMobile(String mobile)
	{
		int result = 0;
		if(mobile != null)
		{
			SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
			UserInfoMapper mapper = session.getMapper(UserInfoMapper.class);
			result = mapper.getCountByMobile(mobile);
		}
		return result;
	}
	@GET
	@Path("userinfo/get/{page}/{is_complete}/{is_provided}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CorporateInfo> getUserInfoByPage(@PathParam("page") int page,@PathParam("is_complete") int is_complete,@PathParam("is_provided") int is_provided)
	{
		List<CorporateInfo> results = new ArrayList<>();
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        try
        {   CorporateInfoMapper corpMapper = session.getMapper(CorporateInfoMapper.class);
            int limit = 10;
            int offset = (page - 1) * limit;
            results = corpMapper.getCorporateInfoByLimit(offset,limit,is_complete,is_provided);
            UserInfoMapper userInfoMapper = session.getMapper(UserInfoMapper.class);
            IndustryMapper industryMapper = session.getMapper(IndustryMapper.class);
            for(CorporateInfo info : results)
            {
                Map<String,Object> map = userInfoMapper.findNameIsNew(info.getId());
                String str = industryMapper.getNameById(Integer.valueOf(info.getIndustry().split(",")[0]));
                if (str == null)
                {
                    str = "";
                }
                map.put("industry_name",str);
                info.setUserInfoData(map);
            }
            
        }
        finally
        {
            session.close();
        }
        return results;
	}
	@GET
    @Path("city/get/{province_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<City> getCitiesByParentCode(@PathParam("province_id") String province_id)
    {
        List<City> cities = new ArrayList<>();
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        try
        {
            CityMapper cityMapper = session.getMapper(CityMapper.class);
            cities = cityMapper.getCitiesByParentCode(province_id);
            
        }
        finally
        {
            session.close();
        }
            
        return cities;
    }
    @GET
    @Path("area/get/{city_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Area> getAreasByCityId(@PathParam("city_id") String city_id)
    {
        List<Area> areas = new ArrayList<>();
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        try
        {
            AreaMapper areaMapper = session.getMapper(AreaMapper.class);
            areas = areaMapper.getAreasByParentCode(city_id);
            return areas;
        }
        finally
        {
            session.close();
        }
    }
    @POST
    @Path("userinfo/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public int updateUserInfo(UserInfo userInfo)
    {
        int result = 0;
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        try
        {
            UserInfoMapper mapper = session.getMapper(UserInfoMapper.class);
            result = mapper.updateUserInfo(userInfo);
            session.commit();
        }
        finally
        {
            session.close();
        }
        return result;
    }
    @GET
    @Path("userinfo/bycorporate/{corporate_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserInfo> getUsersByCorporate(@PathParam("corporate_id") int corporate_id)
    {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        try
        {
            UserInfoMapper mapper = session.getMapper(UserInfoMapper.class);
            return mapper.getUserInfoByCorporateId(corporate_id);
        }
        finally
        {
            session.close();
        }
    }
    @POST
    @Path("userinfo/new")
    public int newUserInfo(UserInfo userInfo)
    {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        int result = 0;
        try
        {
            UserInfoMapper mapper = session.getMapper(UserInfoMapper.class);
            userInfo.setAdd_time(Timestamp.from(Instant.now()));
            userInfo.setCategory_id(1);
            result = mapper.insert(userInfo);
            session.commit();
        }
        finally
        {
            session.close();
        }
        return result;
    }
    @GET
    @Path("userinfo/findById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserInfo findById(@PathParam("id") int id)
    {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        try
        {
            UserInfoMapper mapper = session.getMapper(UserInfoMapper.class);
            return mapper.findById(id);
        }
        finally
        {
            session.close();
        }
    }
    @GET
    @Path("userinfo/deleteById/{id}")
    public int deleteById(@PathParam("id") int id)
    {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        int result = 0;
        try
        {
            UserInfoMapper mapper = session.getMapper(UserInfoMapper.class);
            result = mapper.deleteById(id);
            session.commit();
        }
        finally
        {
            session.close();
        }
        return result;
    }
    
    @GET
    @Path("auditor/delete/{id}")
    public int deleteAdminById(@PathParam("id") int id)
    {
        int result = 0;
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        try
        {
            AdminMapper mapper = session.getMapper(AdminMapper.class);
            result = mapper.delete(id);
            session.commit();
        }
        finally
        {
            session.close();
        }
        return result;
    }
    
    @GET
    @Path("industry/get/{pid}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Industry> getIndustryByPid(@PathParam("pid") int pid)
    {
        List<Industry> results = new ArrayList<Industry>();
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        try
        {
            IndustryMapper mapper = session.getMapper(IndustryMapper.class);
            results = mapper.findByPid(pid);
        }
        finally
        {
            session.close();
        }
        return results;
    }
    
	
}
