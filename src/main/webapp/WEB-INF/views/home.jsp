<%--
  Created by IntelliJ IDEA.
  User: zhouwenchao
  Date: 16/2/18
  Time: 上午10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/home.js"></script>
    <title>Home</title>
</head>
<body>
<span><%=basePath %></span>
</body>
</html>
