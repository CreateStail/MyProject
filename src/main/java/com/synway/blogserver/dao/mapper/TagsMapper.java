package com.synway.blogserver.dao.mapper;

import com.synway.blogserver.Provider.TagProvider;
import com.synway.blogserver.domain.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TagsMapper {

    @Insert("insert into t_tag(name) values (#{name})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int save_tag(Tag tag);

    @Delete("delete from t_tag where id=#{id}")
    int delete(int id);

    @Select("select id,name from t_tag")
    List<Tag> findAll();

    @Select("select id,name from t_tag where id = #{id}")
    Tag findById(long id);

    @UpdateProvider(type = TagProvider.class,method = "updateTag")
    int update(Tag tag);

    @Select("select count(1) from t_tag where name = #{name}")
    int findtagByName(String name);
}
