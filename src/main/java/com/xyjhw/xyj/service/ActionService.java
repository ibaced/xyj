package com.xyjhw.xyj.service;
import com.xyjhw.xyj.mapper.ActionMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
}
