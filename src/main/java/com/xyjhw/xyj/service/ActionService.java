package com.xyjhw.xyj.service;
import com.xyjhw.xyj.mapper.ActionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.xyjhw.xyj.pojo.Action;
import java.util.List;
@RestController
public class ActionService {
    @Autowired
    ActionMapper actionMapper;
    @RequestMapping(value = "/listActionService",method = RequestMethod.GET)
    public List<Action> getInfo(){
        StringBuffer sb=new StringBuffer();
        List<Action> as = actionMapper.findAll();
        return as;
    }
    @RequestMapping(value="/addActionService",method = RequestMethod.POST)
    public boolean addAction(Action c) throws Exception {
        try{
            actionMapper.save(c);
        }
        catch(Exception e){
            return false;
        }
        return true;
    }
    @RequestMapping(value="/deleteActionService",method = RequestMethod.POST)
    public boolean deleteAction(Action c) throws Exception {
        try{
            actionMapper.delete(c.getId());
        }
        catch (Exception e){
            return  false;
        }
        return true;
    }
    @RequestMapping(value="/updateActionService",method = RequestMethod.POST)
    public boolean updateAction(Action c) throws Exception {
        try{
            actionMapper.update(c);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
    @RequestMapping(value="/getActionService",method = RequestMethod.POST)
    public Action listAction(long id) throws Exception {
            Action c= actionMapper.get(id);
        return c;
    }
}
