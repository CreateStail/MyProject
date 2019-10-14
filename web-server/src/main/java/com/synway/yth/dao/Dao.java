package com.synway.yth.dao;

import java.util.List;
import java.util.Map;

public interface Dao {

     Object save(String mapper,Object object);
     Object delete(String mapper,Object object);
     Object update(String mapper,Object object);
     Object findForObject(String mapper,Object object);
     List<Map<String,Object>> findForList(String mapper, Object object);

}
