package com.synway.blogserver.dao;

import com.synway.blogserver.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BlogDao extends JpaRepository<Blog,Long>,JpaSpecificationExecutor<Blog> {
}
