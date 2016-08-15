package com.myapp.servlet;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

/**
 * Created by JohnC on 2016-08-10.
 */
public class Taobao_IP
{
    public static String getRegion(String ip)
    {
        try(CloseableHttpClient client = HttpClients.createDefault())
        {
            String url = "http://ip.taobao.com/service/getIpInfo.php?ip=" + ip;
            HttpGet httpGet = new HttpGet(url);
            try(CloseableHttpResponse response = client.execute(httpGet))
            {
                if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
                {
                    HttpEntity entity = response.getEntity();
                    if (entity != null)
                    {
                        String jsonString = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                        if (jsonString != null)
                        {
                            JSONObject jsonObject = new JSONObject(jsonString);
                            if (jsonObject != null)
                            {
                                if(jsonObject.getInt("code") == 0)
                                {
                                    JSONObject data = jsonObject.getJSONObject("data");
                                    if (data != null)
                                    {
                                        if(data.getString("country").equals("未分配或者内网IP"))
                                        {
                                            return "未分配或者内网IP";
                                        }
                                        else
                                        {
                                            String result = data.getString("country") + "," + data.getString("region")
                                                    + "," + data.getString("city");
                                            return result;
                                        }
        
                                    }
                                }
                                
                            }
                            
                        }
                        
                    }
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
}
