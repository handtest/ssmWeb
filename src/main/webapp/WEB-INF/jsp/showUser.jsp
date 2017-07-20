<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <title>测试</title>
    <style type="text/css">
        .addInput {
            width: 200px;
            padding-left: 10px;
            border: 1px solid #eee;
            margin-bottom: 10px;
        }
    </style>
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

    //删除警告框
    function firm(id) {
        //利用对话框返回的值 （true 或者 false）
        if (confirm("你确定删除吗？")) {
            $.ajax({
                type:"POST",
                url:"<%=request.getContextPath()%>/remove?id="+id,
                success:function () {
                    window.location.href=window.location.href;
                }
            })
        } else {

        }
    }

    //校验添加用户是否填入数据
    function checkData(){
        var age = $("#age").val();
        var userName = $("#userName").val();
        var address = $("#address").val();
        if(age != "" && userName != "" && address != ""){
            $("#addUser").attr("disabled",false);
        }else{
            $("#addUser").attr("disabled","disabled");
        }
    }

    //添加用户
    function addUser() {
        var age = $("#age").val();
        var userName = $("#userName").val();
        var address = $("#address").val();
        $.ajax({
            type:"POST",
            url:"<%=request.getContextPath()%>/save",
            data:"age="+age+"&userName="+userName+"&address="+address,
            success:function () {
                window.location.href=window.location.href;
            }
        })
    }
</script>

<!-- 按钮触发模态框 -->
<div style="text-align: center;">
<h2>用户列表</h2>
</div>
<button style="width: 80px;height: 36px;padding: 6px 5px;float: right;margin-right: 40px;margin-right: 325px" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
    添加用户
</button>

<table class="table table-hover" style="width: 50%;margin-top: 60px;margin-left: 320px;">
    <thead>
    <tr>
        <th>姓名</th>
        <th>年龄</th>
        <th>城市</th>
        <th style="width:120px;">操作</th>
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
                <span id="${user.id}delete"><a id="delete"><button onclick="firm('${user.id}')" type="button" class="delete" name="删除" value="删除">删除</button></a></span>
            </td>
        </tr>
        </form>
    </c:forEach>
    </tbody>
</table>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 400px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel" style="text-align: center;">
                    添加用户
                </h4>
            </div>
            <div class="modal-body" style="text-align: center;">
                <input class="addInput" type="text" onkeyup="checkData()" onblur="checkData()" id="userName" name="userName" placeholder="用户名" /><br/>
                <input class="addInput" type="text" onkeyup="checkData()" onblur="checkData()" id="age" name="age" placeholder="年龄"  /><br/>
                <input class="addInput" type="text" onkeyup="checkData()" onblur="checkData()" id="address" name="address" placeholder="地址" /><br/>
            </div>
            <div class="modal-footer" style="text-align: center;">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" onclick="addUser()" disabled="disabled" class="btn btn-primary" id="addUser">
                    提交更改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>