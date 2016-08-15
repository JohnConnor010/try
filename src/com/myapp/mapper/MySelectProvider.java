package com.myapp.mapper;

import org.apache.ibatis.jdbc.SQL;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JohnC on 2016-08-05.
 */
public class MySelectProvider
{
    public String getPaginationSQL(Map<String,Object> parameters)
    {
        Integer offset = Integer.valueOf(parameters.get("offset").toString());
        Integer limit = Integer.valueOf(parameters.get("limit").toString());
        Integer is_complete = Integer.valueOf(parameters.get("is_complete").toString());
        Integer is_provided = Integer.valueOf(parameters.get("is_provided").toString());
        String conditions = "";
        if(is_complete != 0)
        {
            conditions = "is_complete = " + is_complete;
        }
        String sql = new SQL(){
            {
                SELECT("*");
                FROM("corporate_info");
                if(is_complete != 0 && is_provided==0)
                {
                    WHERE("is_complete=" + is_complete);
                }
                else if(is_provided != 0 && is_complete==0)
                {
                    WHERE("is_provided=" + is_provided);
                }
                else if(is_complete != 0 && is_provided != 0)
                {
                    WHERE("is_complete=" + is_complete + " AND is_provided=" + is_provided);
                }
                
            }
        }.toString() + " ORDER BY id DESC LIMIT " + offset + "," + limit;
        return sql;
    }
}
