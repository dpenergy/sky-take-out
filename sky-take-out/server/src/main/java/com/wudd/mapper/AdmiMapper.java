package com.wudd.mapper;

import entity.Admi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdmiMapper {
    @Select("select * from admi where username = #{username}")
    Admi queryByUsername(String username);
}
