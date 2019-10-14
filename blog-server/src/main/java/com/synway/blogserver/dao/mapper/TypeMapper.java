package com.synway.blogserver.dao.mapper;

import com.synway.blogserver.Provider.TypeProvider;
import com.synway.blogserver.domain.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TypeMapper {

    @Insert("insert into t_type(name) values (#{name})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int save_type(Type type);

    @Delete("delete from t_type where id=#{id}")
    int delete(int id);

    @Select("select id,name from t_type")
    List<Type> findAll();

    @Select("select id,name from t_type where id = #{id}")
    Type findById(long id);

    @UpdateProvider(type = TypeProvider.class,method = "updateType")
    int update(Type type);

    @Select("select count(1) from t_type where name = #{name}")
    int findTypeByName(String name);
}
