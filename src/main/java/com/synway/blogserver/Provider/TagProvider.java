package com.synway.blogserver.Provider;

import com.synway.blogserver.domain.Tag;
import com.synway.blogserver.domain.Type;
import org.apache.ibatis.jdbc.SQL;

public class TagProvider {

    public String updateTag(Tag tag){
        return new SQL(){{
            UPDATE("t_tag");
            if(tag.getName() != null){
                SET("name = #{name}");
            }
            WHERE("id = #{id}");
        }}.toString();
    }


}
