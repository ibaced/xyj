package com.xyjhw.xyj.web;

import java.util.List;

import com.xyjhw.xyj.pojo.User;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xyjhw.xyj.mapper.UserMapper;

@Controller
public class UserController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/listUser")
    public String listUser(Model m, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "20") int size) throws Exception {
        PageHelper.startPage(start, size, "id desc");
        List<User> cs = userMapper.findAll();
        PageInfo<User> page = new PageInfo<>(cs);
        m.addAttribute("page", page);
        return "listUser";
    }

    @RequestMapping("/addUser")
    public String addUser(User c) throws Exception {
        userMapper.save(c);
        return "redirect:listUser";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(User c) throws Exception {
        userMapper.delete(c.getId());
        return "redirect:listUser";
    }

    @RequestMapping("/updateUser")
    public String updateUser(User c) throws Exception {
        userMapper.update(c);
        return "redirect:listUser";
    }

    @RequestMapping("/editUser")
    public String listUser(String id, Model m) throws Exception {
        User c = userMapper.get(id);
        m.addAttribute("c", c);
        return "editUser";
    }
}