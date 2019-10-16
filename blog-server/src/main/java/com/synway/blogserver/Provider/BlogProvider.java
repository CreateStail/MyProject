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

    public String queryBlog(Map<String,String> params){
        return new SQL(){{

        }}.toString();
    }

}
