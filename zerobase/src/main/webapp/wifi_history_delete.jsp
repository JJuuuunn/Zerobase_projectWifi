<%@page import="wifi_project.wifi_parsing"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String datetime = request.getParameter("datetime");

wifi_parsing wifiParsing = new wifi_parsing();
wifiParsing.withdraw(datetime);

response.sendRedirect("wifi_history.jsp");
%>
</body>
</html>