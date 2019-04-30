<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>

<div style="margin:0px auto; width:500px">

    <form action="updateUser" method="post">

        password: <input type="text" name="password" value="${c.password}"> <br>
        <input name="id" type="hidden" value="${c.id}">
        <button type="submit">提交</button>

    </form>
</div>