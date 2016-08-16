package com.myapp.mapper;

import com.myapp.domain.CorporateInfo;
import com.myapp.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by JohnC on 2016-08-04.
 */
public interface CorporateInfoMapper
{
    
    @Insert("INSERT INTO corporate_info (code) VALUES (#{code})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int insert(CorporateInfo corporateInfo);
    
    @Select("SELECT MAX(id) FROM corporate_info")
    Object getMaxIdFromCorporateInfo();
    
    @Select("SELECT * FROM corporate_info where id=#{id}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "code",column = "code"),
            @Result(property = "name",column = "name"),
            @Result(property = "industry",column = "industry"),
            @Result(property = "province",column = "province"),
            @Result(property = "city",column = "city"),
            @Result(property = "area",column = "area"),
            @Result(property = "address",column = "address"),
            @Result(property = "level",column = "level"),
            @Result(property = "phone",column = "phone"),
            @Result(property = "fax",column = "fax"),
            @Result(property = "_400Phone",column = "400Phone"),
            @Result(property = "email",column = "email"),
            @Result(property = "legal_name",column = "legal_name"),
            @Result(property = "legal_mobile",column = "legal_mobile"),
            @Result(property = "legal_phone",column = "legal_phone"),
            @Result(property = "legal_qq",column = "legal_qq"),
            @Result(property = "official_website",column = "official_website"),
            @Result(property = "is_complete",column = "is_complete"),
            @Result(property = "is_provided",column = "is_provided"),
            @Result(property = "image_url",column = "image_url")
            
    })
    CorporateInfo getCorporateInfoById(int id);
    
    @Select("SELECT a.* from user_info as a where a.corporate_id=${id}")
    List<UserInfo> getUserInfosByCorporateId(int id);
    
    @SelectProvider(type = MySelectProvider.class,method = "getPaginationSQL")
    @Results({
        @Result(property = "id",column = "id"),
            @Result(property = "code",column = "code"),
            @Result(property = "name",column = "name"),
            @Result(property = "industry",column = "industry"),
            @Result(property = "province",column = "province"),
            @Result(property = "city",column = "city"),
            @Result(property = "area",column = "area"),
            @Result(property = "address",column = "address"),
            @Result(property = "level",column = "level"),
            @Result(property = "phone",column = "phone"),
            @Result(property = "fax",column = "fax"),
            @Result(property = "_400Phone",column = "400Phone"),
            @Result(property = "email",column = "email"),
            @Result(property = "legal_name",column = "legal_name"),
            @Result(property = "legal_mobile",column = "legal_mobile"),
            @Result(property = "legal_phone",column = "legal_phone"),
            @Result(property = "legal_qq",column = "legal_qq"),
            @Result(property = "official_website",column = "official_website"),
            @Result(property = "is_complete",column = "is_complete"),
            @Result(property = "is_provided",column = "provided"),
    })
    List<CorporateInfo> getCorporateInfoByLimit(@Param("offset") int offiset,@Param("limit") int limit,@Param("is_complete") int is_complete,@Param("is_provided") int is_provided);
    
    @Update("UPDATE corporate_info SET name = #{name}, industry = #{industry}, province = #{province}, city = #{city}, area = #{area}, address = #{address}, level = #{level}, phone = #{phone}, fax = #{fax}, `400Phone` = #{_400Phone}, email = #{email}, legal_name = #{legal_name}, legal_mobile = #{legal_mobile}, legal_phone = #{legal_phone}, legal_qq = #{legal_qq}, official_website = #{official_website}, is_complete = #{is_complete}, is_provided = #{is_provided} WHERE id = #{id};")
    int update(CorporateInfo info);
    
    @Update("UPDATE corporate_info set image_url=#{image_url} WHERE id=#{id}")
    int updateImgUrl(@Param("image_url") String image_url,@Param("id") int id);
}
