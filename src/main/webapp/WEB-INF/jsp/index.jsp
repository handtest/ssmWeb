<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <style>
        .col-sm-10 {width: 40%;}
    </style>
</head>
<body>
<%
    HttpSession session1 = request.getSession();
    String code = (String)session1.getAttribute("randCheckCode");
%>
<script type="text/javascript">
    function myReload() {
        document.getElementById("CreateCheckCode").src = document
                .getElementById("CreateCheckCode").src
            + "?nocache=" + new Date().getTime();
    }
</script>

<div style="width: 500px; margin-left: 400px; margin-top: 100px;">
    <form class="form-horizontal" role="form" action="<%=request.getContextPath()%>/login" method="post">
        <div class="form-group">
            <label for="firstname" class="col-sm-2 control-label">名字</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="firstname" name="name"
                       placeholder="请输入名字">
            </div>
        </div>
        <div class="form-group">
            <label for="lastname" class="col-sm-2 control-label">验证码</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="lastname" name="checkCode"
                       placeholder="请输入验证码" maxlength="4">
            </div><a href="#" onclick="myReload()"><img src="VerificationCode" id="CreateCheckCode" align="middle"></a>
        </div>
        <c:if test="${not empty msg}"><span style="color: red"><c:out value="${msg}"/></span></c:if>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <div class="checkbox">
                    <label>
                        <input type="checkbox"> 请记住我
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">登录</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>