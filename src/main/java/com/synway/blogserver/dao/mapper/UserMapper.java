package com.synway.blogserver.dao.mapper;

import com.synway.blogserver.domain.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select id,avatar,create_time,email,nickname,password,type,update_time,username from t_user")
    User findByUsernameAndPassword(String username,String password);

}
