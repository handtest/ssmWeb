<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>测试</title>
</head>

<body>
<script type="text/javascript">
    function edit(id) {
        document.getElementById(id+"name").style.display="none";
        document.getElementById(id+"age").style.display="none";
        document.getElementById(id+"ps").style.display="none";
        document.getElementById(id+"edit").style.display="none";
        document.getElementById(id+"delete").style.display="none";
        document.getElementById(id+"inName").style.display="block";
        document.getElementById(id+"inAge").style.display="block";
        document.getElementById(id+"inPs").style.display="block";
        document.getElementById(id+"editAdd").style.display="block";

    }
</script>


<table class="table table-hover">
    <caption>悬停表格布局</caption>
    <thead>
    <tr>
        <th>姓名</th>
        <th>年龄</th>
        <th>城市</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${userList}" var="user" varStatus="status" >
        <form action="<%=request.getContextPath()%>/edit" method="post" id="${user.id}">
        <tr>
            <td><span id="${user.id}name">${user.userName}</span><input id="${user.id}inName" style="display: none;" type="text" name="userName" value="${user.userName}" /></td>
            <td><span id="${user.id}age">${user.age}</span><input id="${user.id}inAge" style="display: none" type="text" name="age"  value="${user.age}" /></td>
            <td><span id="${user.id}ps">${user.address}</span><input id="${user.id}inPs" style="display: none" type="text" name="address" value="${user.address}" /></td>
            <input style="display: none" type="text" name="id" value="${user.id}"></input>
            <td>
                <span><button type="button" id="${user.id}edit" class="edit" name="编辑" value="编辑" onclick="edit('${user.id}')">编辑</button></span>
                <span><button style="display: none" type="submit" id="${user.id}editAdd" class="editAdd" >确定</button></span>
                <span id="${user.id}delete"><a href="<%=request.getContextPath()%>/remove?id=${user.id}"><button type="button" class="delete" name="删除" value="删除">删除</button></a></span>
            </td>
        </tr>
        </form>
    </c:forEach>
    <form action="<%=request.getContextPath()%>/save" id="addUserId" method="post" name="addUserFrom">
        <tr>
            <td><input type="text" name="userName"   /></td>
            <td><input type="text" name="age"   /></td>
            <td><input type="text" name="address"  /></td>
            <td><button type="submit" >添加</button></td>
        </tr>
    </form>
    </tbody>
</table>
</body>
</html>