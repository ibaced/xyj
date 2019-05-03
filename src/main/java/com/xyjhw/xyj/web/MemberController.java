package com.xyjhw.xyj.web;

import java.util.List;
import com.xyjhw.xyj.pojo.Member;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xyjhw.xyj.mapper.MemberMapper;

@Controller
public class MemberController {
    @Autowired
    MemberMapper memberMapper;

    @RequestMapping("/enroll/success")
    public String enroll() throws Exception {
        return "success";
    }

    @RequestMapping("/listMember")
    public String listMember(Model m, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "20") int size) throws Exception {
        PageHelper.startPage(start, size, "id desc");
        List<Member> cs = memberMapper.findAll();
        PageInfo<Member> page = new PageInfo<>(cs);
        m.addAttribute("page", page);
        return "listMember";
    }

    @RequestMapping("/addMember")
    public String addMember(Member c) throws Exception {
        memberMapper.save(c);
        return "redirect:listMember";
    }

    @RequestMapping("/deleteMember")
    public String deleteMember(Member c) throws Exception {
        memberMapper.delete(c.getId());
        return "redirect:listMember";
    }

    @RequestMapping("/updateMember")
    public String updateMember(Member c) throws Exception {
        memberMapper.update(c);
        return "redirect:listMember";
    }

    @RequestMapping("/editMember")
    public String listMember(long id, Model m) throws Exception {
        Member c = memberMapper.get(id);
        m.addAttribute("c", c);
        return "editMember";
    }
}