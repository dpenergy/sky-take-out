package com.wudd.mapper;

import dto.AdmiSearchInfo;
import entity.Admi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdmiMapper {
    @Select("select * from admi where username = #{username}")
    Admi queryByUsername(String username);

    // 注意Mybatis不运行方法重载，会导致错误
    //@Select("select * from admi where name like concat('%',#{searchName},'%')")
    //List<Admi> queryAdmiList(String searchName);


    @Select("select count(*) from admi")
    Integer admiTotalCounts();

    @Select("select * from admi where name like concat('%',#{searchName},'%') limit #{start},#{pageSize}")
    List<Admi> queryAdmiList(AdmiSearchInfo admiSearchInfo);
}
