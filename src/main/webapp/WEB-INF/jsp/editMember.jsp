<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>

<div style="margin:0px auto; width:500px">

    <form action="updateMember" method="post">

        telephone: <input type="text" name="telephone" value="${c.telephone}"> <br>
        name:<input type="text" name="name" value="${c.name}"> <br>
        idcard:<input type="text" name="idcard" value="${c.idcard}"> <br>
        beizhu:<input type="text" name="beizhu" value="${c.beizhu}"> <br>
        <input name="id" type="hidden" value="${c.id}">
        <button type="submit">提交</button>

    </form>
</div>