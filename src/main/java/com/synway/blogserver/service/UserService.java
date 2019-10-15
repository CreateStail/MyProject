package com.synway.blogserver.service;

import com.synway.blogserver.dao.UserDao;
import com.synway.blogserver.dao.mapper.UserMapper;
import com.synway.blogserver.domain.User;
import com.synway.blogserver.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

/*    @Autowired
    private UserDao userDao;*/

    @Autowired
    private UserMapper userMapper;

    public User checkUser(String username,String password){
        User user = userMapper.findByUsernameAndPassword(username, CommonUtils.MD5(password));
        return user;
    }


}
