package com.synway.blogserver.dao;

import com.synway.blogserver.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TypeDao extends JpaRepository<Type,Long> {

    @Query(value = "select count(t.name) from t_type t where t.name = ?1",nativeQuery = true)
    Integer findTypeByName(String typeName);

}
