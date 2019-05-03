package com.xyjhw.xyj.service;

import com.xyjhw.xyj.mapper.ActionMapper;
import com.xyjhw.xyj.mapper.MemberMapper;
import com.xyjhw.xyj.pojo.Action;
import com.xyjhw.xyj.pojo.Member;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;

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
        Action a = actionMapper.get(m.getAction_id());
        try {
            Calendar cal = Calendar.getInstance();
            long datelong = cal.get(Calendar.DATE) + (cal.get(Calendar.MONTH) + 1) * 100 + cal.get(Calendar.YEAR) * 10000;
            Date date=new Date();
            String rdm=String.valueOf(date.getTime()%10000);
            if (datelong > a.getDate()) {
                json.put("success", false);
                json.put("msg", "超过报名时间");
                json.put("url", "");
            } else {
                StringBuffer sb = new StringBuffer();
                String gdsnm=String.valueOf(a.getId());
                String odruid=m.getIdcard();
                float price=Float.parseFloat(String.valueOf(a.getPrice()))/100;
                NumberFormat formatter = new DecimalFormat("0.00");
                String formmatedFloatValue = formatter.format(price);
                String odrid=new String(gdsnm+odruid+m.getTelephone()+date.getTime()%100);
                sb.append(gdsnm);//goodsname
                sb.append("2");//istype
                sb.append("http://zjhxiyuzhongxin.cn:8080/xyj/enroll/paysapi_notify");//
                sb.append(odrid);//orderid
                sb.append(rdm);
                sb.append(odruid);//orderuid
                sb.append(formmatedFloatValue);
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
                        + "price=" + formmatedFloatValue + "&"
                        + "istype=" + "2" + "&"
                        + "notify_url=" + "http://zjhxiyuzhongxin.cn:8080/xyj/enroll/paysapi_notify" + "&"
                        + "return_url=" + "http://zjhxiyuzhongxin.cn:8080/xyj/enroll/success" + "&"
                        + "orderid=" + odrid +rdm+ "&"
                        + "orderuid=" + odruid + "&"
                        + "goodsname=" + gdsnm + "&"
                        + "key=" + key;
                json.put("success", true);
                json.put("msg", "");
                json.put("url",url);
                m.setBeizhu(odrid+rdm);
                memberMapper.save(m);
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
    public void paysapi_notify(HttpServletResponse response, HttpServletRequest request){
        String bz=request.getParameter("orderid");
        memberMapper.updatebeizhu(bz);
    }
}
