package com.synway.yth.service;

import com.synway.yth.dao.Dao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class UserService {

    @Resource
    private Dao dao;

    @SuppressWarnings("unchecked")
    public Map<String,Object> findUserById(String userId){
        return (Map<String, Object>) dao.findForObject("userMapper.getUserById",userId);
    }

    public Map<String,Object> findUserByName(String userName){
        return (Map<String, Object>) dao.findForObject("userMapper.getUserByName",userName);
    }

}
