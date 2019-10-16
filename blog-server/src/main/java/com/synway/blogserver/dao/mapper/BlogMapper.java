package com.synway.blogserver.dao.mapper;

import com.synway.blogserver.domain.Blog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BlogMapper {
    /**
     * "(#{id},#{appreciation},#{commentabled},#{content},#{create_time}," +
     *             "#{description},#{first_picture},#{flag},#{published},#{recommend}," +
     *             "#{share_statement},#{title},#{update_time},#{views},#{type_id},#{user_id}"
     * @return
     */
    @Select("select (id,appreciation,commentabled,content,create_time," +
            "description,first_picture,flag,published,recommend,share_statement," +
            "title,update_time,views,type_id,user_id) from t_blog")
    List<Blog> findAll();


    @Select("select (id,appreciation,commentabled,content,create_time," +
            "description,first_picture,flag,published,recommend,share_statement," +
            "title,update_time,views,type_id,user_id) from t_blog where id = #{id}")
    Blog getBlog(long id);


    @Insert("insert into t_blog(appreciation,commentabled,content,create_time," +
            "description,first_picture,flag,published,recommend,share_statement," +
            "title,update_time,views,type_id,user_id) " +
            "values" +
            "(#{appreciation},#{commentabled},#{content},#{create_time}, "+
            " #{description},#{first_picture},#{flag},#{published},#{recommend}," +
            " #{share_statement},#{title},#{update_time},#{views},#{type_id},#{user_id}")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int saveBlog(Blog blog);

    @Delete("delete from t_blog where id = #{id}")
    int deleteBlog(int id);


//    int updateBlog(Blog blog);

}
