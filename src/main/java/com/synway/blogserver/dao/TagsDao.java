package com.synway.blogserver.dao;

import com.synway.blogserver.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface TagsDao extends JpaRepository<Tag,Long> {

    @Query(value = "select count(t.name) from t_tag t where t.name = ?1",nativeQuery = true)
    int findTagByName(String tagName);
}
