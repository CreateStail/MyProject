package com.synway.yth.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public class DaoSupport implements Dao {

    @Resource(name = "oracleSqlSessionTemplate")
    SqlSessionTemplate sqlSessionTemplate;

    @Override
    public Object save(String mapper, Object object) {
        return sqlSessionTemplate.insert(mapper,object);
    }

    @Override
    public Object delete(String mapper, Object object) {
        return sqlSessionTemplate.delete(mapper,object);
    }

    @Override
    public Object update(String mapper, Object object) {
        return sqlSessionTemplate.update(mapper,object);
    }

    @Override
    public Object findForObject(String mapper, Object object) {
        return sqlSessionTemplate.selectOne(mapper,object);
    }

    @Override
    public List<Map<String, Object>> findForList(String mapper, Object object) {
        return sqlSessionTemplate.selectList(mapper,object);
    }
}
