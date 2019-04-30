package com.xyjhw.xyj.mapper;
import com.xyjhw.xyj.pojo.Member;
import java.util.List;

import org.apache.ibatis.annotations.*;

@Mapper
public interface MemberMapper {
    @Select("select * from member ")
    List<Member> findAll();

    @Insert(" insert into member ( action_id,telephone,name,idcard,beizhu ) values ( #{action_id},#{telephone},#{name},#{idcard},#{beizhu} ) ")
    public int save(Member member);

    @Delete(" delete from member where id= #{id} ")
    public void delete(long id);

    @Update("update member set action_id=#{action_id},telephone=#{telephone},name=#{name},idcard=#{idcard},beizhu=#{beizhu} where id=#{id} ")
    public int update(Member member);

    @Select("select * from member where id= #{id} ")
    public Member get(long id);
}
