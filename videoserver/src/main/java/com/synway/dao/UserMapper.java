package com.synway.dao;

import com.synway.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    /**
     * 根据主键id查找
     */
    @Select("select * from user where id = #{id}")
    User findByid(int userId);

    /**
     * 根据openid找用户
     */
    @Select("select * from user where openid = #{openid}")
    User findByopenid(@Param("openid")String openid);

    /**
     * 保存用户
     */
    @Insert("insert into user(openid,name,head_img,phone,sign,sex,city,create_time)" +
            "values"+
            "(#{openid},#{name},#{headImg},#{phone},#{sign},#{sex},#{city},#{createTime})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int save(User user);

}
