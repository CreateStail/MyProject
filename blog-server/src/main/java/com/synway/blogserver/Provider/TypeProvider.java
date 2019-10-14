package com.synway.blogserver.Provider;

import com.synway.blogserver.domain.Type;
import org.apache.ibatis.jdbc.SQL;

public class TypeProvider {

    public String updateType(Type type){
        return new SQL(){{
            UPDATE("t_type");
            if(type.getName() != null){
                SET("name = #{name}");
            }
            WHERE("id = #{id}");
        }}.toString();
    }


}
