package com.xyjhw.xyj.web;

import java.util.List;

import com.xyjhw.xyj.pojo.Action;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xyjhw.xyj.mapper.ActionMapper;
//import com.xyjhw.xyj.dao.ActionDAO;

@Controller
public class ActionController {
    @Autowired ActionMapper actionMapper;
   // @Autowired ActionDAO actionDAO;

    @RequestMapping("/listAction")
    public String listAction(Model m, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "20") int size) throws Exception {
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
    @RequestMapping("/updateAction")
    public String updateAction(Action c) throws Exception {
        actionMapper.update(c);
        return "redirect:listAction";
    }
    @RequestMapping("/editAction")
    public String listAction(long id,Model m) throws Exception {
        Action c= actionMapper.get(id);
        m.addAttribute("c", c);
        return "editAction";
    }
/*
@GetMapping("/actions")
    public String listAction(Model m,@RequestParam(value = "start", defaultValue = "0") int start,@RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        start = start<0?0:start;
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page<Action> page =actionDAO.findAll(pageable);
        m.addAttribute("page", page);
        return "listAction";
    }

    @PostMapping("/actions")
    public String addAction(Action c) throws Exception {
        actionDAO.save(c);
        return "redirect:/actions";
    }
    @DeleteMapping("/actions/{id}")
    public String deleteAction(Action c) throws Exception {
        actionDAO.delete(c);
        return "redirect:/actions";
    }
    @PutMapping("/actions/{id}")
    public String updateAction(Action c) throws Exception {
        actionDAO.save(c);
        return "redirect:/actions";
    }
    @GetMapping("/actions/{id}")
    public String getCategory(@PathVariable("id") int id,Model m) throws Exception {
        Action c= actionDAO.getOne(id);
        m.addAttribute("c", c);
        return "editAction";
    }
*/
}