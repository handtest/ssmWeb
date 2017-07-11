<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

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

<form action="<%=request.getContextPath()%>/login" method="post">
    <input name="userName" type="text" />
    <input name="checkCode" type="text" id="checkCode" title="验证码区分大小写"
                                             size="8" ,maxlength="4" />
    <img src="VerificationCode" id="CreateCheckCode" align="middle">
    <a href="" onclick="myReload()"> 看不清,换一个</a>
    <input type="submit" value="提交"/><c:if test="${not empty msg}"><span style="color: red"><c:out value="${msg}"/></span></c:if>
</form>
</body>
</html>
