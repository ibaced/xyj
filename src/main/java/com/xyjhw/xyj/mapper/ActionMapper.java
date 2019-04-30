package com.xyjhw.xyj.mapper;
import com.xyjhw.xyj.pojo.Action;
import java.util.List;

import org.apache.ibatis.annotations.*;

@Mapper
public interface ActionMapper {

    @Select("select * from action ")
    List<Action> findAll();

    @Insert(" insert into action ( name,price,date ) values ( #{name},#{price},#{date} ) ")
    public int save(Action action);

    @Delete(" delete from action where id= #{id} ")
    public void delete(long id);

    @Update("update action set name=#{name},price=#{price},date=#{date} where id=#{id} ")
    public int update(Action action);

    @Select("select * from action where id= #{id} ")
    public Action get(long id);
}