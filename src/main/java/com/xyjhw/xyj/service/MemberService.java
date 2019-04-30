package com.xyjhw.xyj.service;
import com.xyjhw.xyj.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.xyjhw.xyj.pojo.Member;
import java.util.List;
@RestController
public class MemberService {
    @Autowired
    MemberMapper memberMapper;
    @RequestMapping(value = "/listMemberService",method = RequestMethod.GET)
    public List<Member> getInfo(){
        StringBuffer sb=new StringBuffer();
        List<Member> as = memberMapper.findAll();
        return as;
    }
    @RequestMapping(value="/addMemberService",method = RequestMethod.POST)
    public boolean addMember(Member c) throws Exception {
        try{
            memberMapper.save(c);
        }
        catch(Exception e){
            return false;
        }
        return true;
    }
    @RequestMapping(value="/deleteMemberService",method = RequestMethod.POST)
    public boolean deleteMember(Member c) throws Exception {
        try{
            memberMapper.delete(c.getId());
        }
        catch (Exception e){
            return  false;
        }
        return true;
    }
    @RequestMapping(value="/updateMemberService",method = RequestMethod.POST)
    public boolean updateMember(Member c) throws Exception {
        try{
            memberMapper.update(c);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
    @RequestMapping(value="/getMemberService",method = RequestMethod.POST)
    public Member listMember(long id) throws Exception {
        Member c= memberMapper.get(id);
        return c;
    }
}
