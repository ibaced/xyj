package com.xyjhw.xyj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.xyjhw.xyj.pojo.Action;

@Mapper
public interface ActionMapper {

    @Select("select * from action ")
    List<Action> findAll();

    @Insert(" insert into action ( id ) values (#{id}) ")
    public int save(Action action);

    @Delete(" delete from action where id= #{id} ")
    public void delete(int id);

    @Select("select * from action where id= #{id} ")
    public Action get(int id);

    @Update("update action set name=#{name} where id=#{id} ")
    public int updatename(Action action);

    @Update("update action set price=#{price} where id=#{id} ")
    public int updateprice(Action action);

    @Update("update action set statue=#{statue} where id=#{id} ")
    public int updatestatue(Action action);
}