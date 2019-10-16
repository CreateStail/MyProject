package com.synway.blogserver.dao;

import com.synway.blogserver.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username,String password);

}
