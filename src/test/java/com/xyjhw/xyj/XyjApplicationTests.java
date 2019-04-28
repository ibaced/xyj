package com.xyjhw.xyj;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.xyjhw.xyj.XyjApplication;
import com.xyjhw.xyj.dao.ActionDAO;
import com.xyjhw.xyj.pojo.Action;
import com.xyjhw.xyj.mapper.ActionMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = XyjApplication.class)
public class XyjApplicationTests {

    @Autowired ActionDAO dao;

    @Test
    public void test() {
        List<Action> cs=  dao.findAll();
        for (Action c : cs) {
            System.out.println("c.getName():"+ c.getName());
        }

    }
}
