package com.xyjhw.xyj.web;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xyjhw.xyj.mapper.ActionMapper;
import com.xyjhw.xyj.pojo.Action;
import com.xyjhw.xyj.dao.ActionDAO;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ActionController {
    @Autowired ActionMapper actionMapper;


    @RequestMapping("/listAction")
    public String listAction(Model m, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        PageHelper.startPage(start,size,"id desc");
        List<Action> cs=actionMapper.findAll();
        PageInfo<Action> page = new PageInfo<>(cs);
        m.addAttribute("page", page);
        return "listAction";
    }



    @RequestMapping("/addAction")
    public String addAction(Action c) throws Exception {
        actionMapper.save(c);
        return "redirect:listAction";
    }
    @RequestMapping("/deleteAction")
    public String deleteAction(Action c) throws Exception {
        actionMapper.delete(c.getId());
        return "redirect:listAction";
    }
    @RequestMapping("/updatenameAction")
    public String updateAction(Action c) throws Exception {
        actionMapper.updatename(c);
        return "redirect:listAction";
    }
    @RequestMapping("/editAction")
    public String listAction(int id,Model m) throws Exception {
        Action c= actionMapper.get(id);
        m.addAttribute("c", c);
        return "editAction";
    }

}