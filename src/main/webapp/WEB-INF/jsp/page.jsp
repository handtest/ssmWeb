<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/19
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/pagination.js"></script>
    <title>Title</title>
</head>
<body>

<div id="test1" style="text-align: center;" pagination="pagination_new" pagenumber="11" totalpage="13"></div>
<hr >
<div id="test2" style="text-align: center;" pagination="pagination_new" pagenumber="14" totalpage="15" ></div>
<hr >
<div id="test3" style="text-align: center;" pagination="pagination_new" pagenumber="1" totalpage="1" onlyOnePageIsShow="false"></div>
<hr >
<div id="test4" style="text-align: center;" pagination="pagination_new" pagenumber="1" totalpage="2" ></div>
<hr >
<div id="test5" style="text-align: center;" pagination="pagination_new" pagenumber="10" totalpage="15" paginationMaxLength="5" onlyOnePageIsShow="false"></div>
<hr/>
<button id="btn1">手动刷新指定分页条</button>
<button id="btn2">手动刷新全部分页条</button>
<script>
    function paginationClick(pagination_id){
        var pagenumber = $('#'+pagination_id+'').attr('pagenumber');
        var totalpage = $('#'+pagination_id+'').attr('totalpage');
        alert('zmy通用分页测试：当前id：'+pagination_id+' , pagenumber:'+pagenumber+' , totalpage:'+totalpage);
    }
    $(function(){
        $('#btn1').click(function(){
            $('#test3').attr('pagenumber','2');
            $('#test3').attr('totalpage','4');
            initPagination($('#test3'));
        });
        $('#btn2').bind('click',function(){
            $('#test3').attr('pagenumber','2');
            $('#test3').attr('totalpage','4');

            $('#test1').attr('pagenumber','7');
            $('#test1').attr('totalpage','10');
            $('#test1').attr('paginationMaxLength','6');
            paginationInit();
        });
    });
</script>
</body>
</html>
