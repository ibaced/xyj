package com.xyjhw.xyj.service;

import com.xyjhw.xyj.mapper.ActionMapper;
import com.xyjhw.xyj.mapper.MemberMapper;
import com.xyjhw.xyj.pojo.Action;
import com.xyjhw.xyj.pojo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
//import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.tomcat.util.security.MD5Encoder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.xyjhw.xyj.mapper.ActionMapper;
import com.xyjhw.xyj.mapper.MemberMapper;
import com.xyjhw.xyj.pojo.Action;
import com.xyjhw.xyj.pojo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import javax.servlet.http.HttpServletResponse;
@RestController
@RequestMapping("/enroll")
public class EnrollService {
    @Autowired
    ActionMapper actionMapper;
    @Autowired
    MemberMapper memberMapper;
    @RequestMapping(value = "/check",produces="application/json")
    public void postInfo(Member m, HttpServletResponse response) throws NoSuchAlgorithmException, JSONException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json;charset=utf-8");
        JSONObject json = new JSONObject();

        try {
            Action a = actionMapper.get(m.getAction_id());

            Calendar cal = Calendar.getInstance();
            long datelong = cal.get(Calendar.DATE) + (cal.get(Calendar.MONTH) + 1) * 100 + cal.get(Calendar.YEAR) * 10000;
            Date date=new Date();
            String rdm=String.valueOf(date.getTime()%10000);
            if (datelong > a.getDate()) {
                json.put("success", false);
                json.put("msg", "超过报名时间");
                json.put("url", "");
                // return json.toString();
            } else {
                StringBuffer sb = new StringBuffer();
                sb.append("12345678");//goodsname
                sb.append("2");//istype
                sb.append("http://zjhxiyuzhongxin.cn:8080/xyj/enroll/paysapi_notify");//
                sb.append("123456");//orderid
                sb.append(rdm);
                sb.append("1234567");//orderuid
                sb.append("0.01");
                sb.append("http://zjhxiyuzhongxin.cn:8080/xyj/enroll/success");
                sb.append("05a14d0c9df09d1616dd2c1352cc5481");
                sb.append("3140582701d0be669f91d5e1");
                String st = sb.toString();
                String re_md5 = new String();
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(st.getBytes());
                byte b[] = md.digest();
                int i;
                StringBuffer buf = new StringBuffer("");
                for (int offset = 0; offset < b.length; offset++) {
                    i = b[offset];
                    if (i < 0)
                        i += 256;
                    if (i < 16)
                        buf.append("0");
                    buf.append(Integer.toHexString(i));
                }

                re_md5 = buf.toString();
                String key = re_md5;
               /* JSONObject data=new JSONObject();
                data.put("uid","3140582701d0be669f91d5e1");
                data.put("price",Float.parseFloat(String.valueOf(a.getPrice())));
                data.put("istype",2);
                data.put("notify_url","http://zjhxiyuzhongxin.cn:8080/xyj/enroll/paysapi_notify");
                data.put("return_url","http://zjhxiyuzhongxin.cn/%23/success");
                data.put("orderid","123456");
                data.put("orderuid","1234567");
                data.put("goodsname","12345678");
                data.put("key",key);
                json.put("data", data);
                */

                String url = "https://pay.sxhhjc.cn/?"
                        + "uid=" + "3140582701d0be669f91d5e1" + "&"
                        + "price=" + "0.01" + "&"
                        + "istype=" + "2" + "&"
                        + "notify_url=" + "http://zjhxiyuzhongxin.cn:8080/xyj/enroll/paysapi_notify" + "&"
                        + "return_url=" + "http://zjhxiyuzhongxin.cn:8080/xyj/enroll/success" + "&"
                        + "orderid=" + "123456" +rdm+ "&"
                        + "orderuid=" + "1234567" + "&"
                        + "goodsname=" + "12345678" + "&"
                        + "key=" + key;
                json.put("success", true);
                json.put("msg", "");
                json.put("url",url);
            }
        }catch (Exception e){
            json.put("success", false);
            json.put("msg", "没有该活动");
            json.put("url", "");
        }
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
    @RequestMapping(value = "/paysapi_notify")
    public void paysapi_notify(HttpServletResponse response){

    }


    /*@RequestMapping(value = "/check",method = RequestMethod.GET)
    public JSONObject getInfo(Member m, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setContentType("application/json");
        String st=new String("[{\"success\":true,\"msg\":\"\",\"url\":\"https://pay.sxhhjc.cn/?\"}]");
        JSONArray json = new JSONArray();
        json.put(st);
        JSONObject resultJson = json.optJSONObject(0);
        return resultJson;
    }*/
}
