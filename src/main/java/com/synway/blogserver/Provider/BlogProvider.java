package com.synway.blogserver.Provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class BlogProvider {

//    public String updateBlog(Blog blog){
//        return new SQL(){{
//            UPDATE("t_blog");
//            if(blog.isAppreciation())
//        }}.toString();
//    }

    public String queryBlog(Map<String,Object> params){
        String sql = "select id,appreciation,commentabled,content,create_time,description," +
                "first_picture,flag,published,recommend,share_statement,title," +
                "date_format(update_time,'%Y-%m-%d %H:%i:%s') as update_time," +
                "views,type_id,user_id from t_blog where 1=1 ";
               if(!"".equals(params.get("title")) && params.get("title") != null){
                   sql += "and title like '%"+ params.get("title") +"%'";
               }
               if(!"".equals(params.get("type_id")) && params.get("type_id") != null){
                   sql += "and type_id = '"+ params.get("type_id") +"'";
               }
//               if(! (boolean)params.get("recommend")){
//                   sql += "and recommend is not null";
//               }
         return sql;
    }

}
