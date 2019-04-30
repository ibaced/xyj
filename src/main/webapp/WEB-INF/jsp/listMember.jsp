<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fmt:formatDate value='${model.getTime}' type='date' pattern='yyyy-MM-dd'/>
<div align="center">

</div>

<div style="width:500px;margin:20px auto;text-align: center">
    <table align='center' border='1' cellspacing='0'>
        <tr>
            <td>id</td>
            <td>action_id</td>
            <td>telephone</td>
            <td>name</td>
            <td>idcard</td>
            <td>beizhu</td>
            <td>编辑</td>
            <td>删除</td>
        </tr>
        <c:forEach items="${page.list}" var="c" varStatus="st">
            <tr>
                <td>${c.id}</td>
                <td>${c.action_id}</td>
                <td>${c.telephone}</td>
                <td> ${c.name}</td>
                <td>${c.idcard}</td>
                <td> ${c.beizhu}</td>
                <td><a href="editMember?id=${c.id}">编辑</a></td>
                <td><a href="deleteMember?id=${c.id}">删除</a></td>
            </tr>
        </c:forEach>

    </table>
    <br>
    <div>
        <a href="?start=1">[首  页]</a>
        <a href="?start=${page.pageNum-1}">[上一页]</a>
        <a href="?start=${page.pageNum+1}">[下一页]</a>
        <a href="?start=${page.pages}">[末  页]</a>
    </div>
    <br>
    <form action="addMember" method="post">
        action_id: <input name="action_id"> <br>
        <button type="submit">生成新报名</button>
    </form>
</div>