<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <title>测试</title>
</head>

<body>
<script type="text/javascript">
    function edit(id) {
        var list = $("."+id);
        var list2 = $("."+id+"input");
        $.each( list, function(index, args){
            args.style.display="none";
        });
        $.each( list2, function(index, args){
            args.style.display="block";
        });
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
            <td><span class="${user.id}" >${user.userName}</span><input class="${user.id}input" style="display: none;" type="text" name="userName" value="${user.userName}" /></td>
            <td><span class="${user.id}" >${user.age}</span><input class="${user.id}input" style="display: none" type="text" name="age"  value="${user.age}" /></td>
            <td><span class="${user.id}" >${user.address}</span><input class="${user.id}input" style="display: none" type="text" name="address" value="${user.address}" /></td>
            <input style="display: none" type="text" name="id" value="${user.id}"></input>
            <td>
                <span><button type="button" class="${user.id}" name="编辑" value="编辑" onclick="edit('${user.id}')">编辑</button></span>
                <span><button style="display: none" type="submit" class="${user.id}input" >确定</button></span>
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