package com.synway.utils;


import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

public class HttpUtils  {

    private static final Gson gson = new Gson();
    /**
     * get方法
     */
     public static Map<String,Object> doGet(String url,int timeout){
         CloseableHttpClient httpClient = HttpClients.createDefault();
         Map<String,Object> map = new HashMap<>();
         RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(timeout)
                 .setConnectTimeout(timeout)
                 .setSocketTimeout(timeout)
                 .setRedirectsEnabled(true)
                 .build();
         HttpGet httpGet = new HttpGet(url);
         httpGet.setConfig(config);

         try{
             HttpResponse httpResponse = httpClient.execute(httpGet);
             if(httpResponse.getStatusLine().getStatusCode() == 200){
                 String jsonResult = EntityUtils.toString(httpResponse.getEntity());
                 map = gson.fromJson(jsonResult,map.getClass());
             }
         }catch (Exception e){
                e.printStackTrace();
         }finally {
            try {
                httpClient.close();
            }catch (Exception e){
                e.printStackTrace();
            }
         }
         return map;
     }

    /**
     * 封装post
     * @param url
     * @param data
     * @return
     */
     public static String doPost(String url,String data,int timeout){
         CloseableHttpClient httpClient = HttpClients.createDefault();
         HttpPost post = new HttpPost(url);
         RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(timeout)
                 .setConnectTimeout(timeout)
                 .setSocketTimeout(timeout)
                 .setRedirectsEnabled(true)
                 .build();
         post.addHeader("Content-Type","text/html;chartset=UTF-8");
         post.setConfig(config);
         if(data != null && data instanceof String){//使用字符串传参
             StringEntity stringEntity = new StringEntity(data.toString(),"UTF-8");
             post.setEntity(stringEntity);
         }
         try{
             HttpResponse response = httpClient.execute(post);
             HttpEntity entity = response.getEntity();
             if(response.getStatusLine().getStatusCode() == 200){
                 String result = EntityUtils.toString(entity);
                 return  result;
             }
         }catch (Exception e){
             e.printStackTrace();
         }finally {
             try{
                 httpClient.close();
             }catch (Exception e){
                 e.printStackTrace();
             }
         }
        return  null;
     }



}
