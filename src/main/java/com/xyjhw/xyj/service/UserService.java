package com.xyjhw.xyj.service;
import com.xyjhw.xyj.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.xyjhw.xyj.pojo.User;
import java.util.List;
@RestController
public class UserService {
    @Autowired
    UserMapper userMapper;
    @RequestMapping(value = "/listUserService",method = RequestMethod.GET)
    public List<User> getInfo(){
        StringBuffer sb=new StringBuffer();
        List<User> as = userMapper.findAll();
        return as;
    }
    @RequestMapping(value="/addUserService",method = RequestMethod.POST)
    public boolean addUser(User c) throws Exception {
        try{
            userMapper.save(c);
        }
        catch(Exception e){
            return false;
        }
        return true;
    }
    @RequestMapping(value="/deleteUserService",method = RequestMethod.POST)
    public boolean deleteUser(User c) throws Exception {
        try{
            userMapper.delete(c.getId());
        }
        catch (Exception e){
            return  false;
        }
        return true;
    }
    @RequestMapping(value="/updateUserService",method = RequestMethod.POST)
    public boolean updateUser(User c) throws Exception {
        try{
            userMapper.update(c);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
    @RequestMapping(value="/getUserService",method = RequestMethod.POST)
    public User listUser(String id) throws Exception {
        User c= userMapper.get(id);
        return c;
    }
}
