<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="head.jsp"/>
<body>
<div style="padding: 10px 10px 10px; float: left">
    <div class="input-group" style="display: block;">
        <input type="text" id="userName" name="userName" class="form-control" style="width: 70%;">
        <span style="width: auto; padding: 9px 12px;" class="input-group-addon"><input style="border: 0px;" type="button" onclick="queryLogByUser()" value="查询" /></span>
    </div>
</div>
<form action="<%=request.getContextPath()%>/showLog" method="post">
    <input style="margin: 10px 10px 10px; float: left; width: 10%;" class="form_datetime form-control" name="afterDate" type="text" value="2017-07-18" size="16">
    <input style="margin: 10px 10px 10px; float: left; width: 10%;" class="form_datetime form-control" name="beforeDate" type="text" value="2017-07-18" size="16">
    <input style="margin: 10px 10px 10px; border: 1px solid darkgrey; width: 65px; height: 32px; border-radius: 4px;color: #555;background-color: #eee" type="submit" value="查询"/>
</form>
<table class="table table-bordered">
    <caption>日志记录</caption>
    <thead>
    <tr>
        <th>操作ID</th>
        <th>操作用户</th>
        <th>操作方法</th>
        <th>传入参数</th>
        <th>操作描述</th>
        <th>操作IP</th>
        <th>操作日期</th>
    </tr>
    </thead>
    <tbody id="showListLog">
    </tbody>
</table>
<div style="position: absolute;top: 550px;right: 50px" id="test1" style="text-align: center;" pagination="pagination_new" pagenumber="1" totalpage="20"></div>
</body>
<script type="text/javascript">
    $(function ($) {
        $("#showListLog").MyProject(
            'showLogList',
            {
                'url':'<%=request.getContextPath()%>/listLog',
                'data':{}
            }
        );
    });
    function queryLogByUser (){
        var username = $("#userName").val();
        $("#showListLog").MyProject(
            'showLogList',
            {
                'url':'<%=request.getContextPath()%>/listLog',
                'data':{'userName':username}
            }
        );
    }
    function paginationClick(pagination_id){
        var username = $("#userName").val();
        $("#showListLog").MyProject(
            'showLogList',
            {
                'url':'<%=request.getContextPath()%>/listLog',
                'data':{'page':pagination_id,'userName':username}
            }
        );
    }
</script>
</html>
