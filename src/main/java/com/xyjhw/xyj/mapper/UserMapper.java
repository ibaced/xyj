package com.xyjhw.xyj.mapper;
import com.xyjhw.xyj.pojo.User;
import java.util.List;

import org.apache.ibatis.annotations.*;
@Mapper
public interface UserMapper {
    @Select("select * from user ")
    List<User> findAll();

    @Insert(" insert into user ( id,password ) values ( #{id},#{password} ) ")
    public int save(User user);

    @Delete(" delete from user where id= #{id} ")
    public void delete(String id);

    @Update("update user set password=#{password} where id=#{id} ")
    public int update(User user);

    @Select("select * from user where id= #{id} ")
    public User get(String id);
}
