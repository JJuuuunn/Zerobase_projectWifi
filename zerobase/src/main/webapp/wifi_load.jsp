<%@page import="wifi_project.wifi_getset"%>
<%@page import="wifi_project.wifi_json"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	home {
		width = 100%;
	}
</style>
</head>
<body>
	<%
	wifi_json json = new wifi_json();
	for (int i = 1; i < 15000; i += 1000) {
		for (wifi_getset wifigs: json.wifi_read(i)) {
			json.register(wifigs);
		}
	}
	%>
	<center>
		<h1>WIFI정보를 정상적으로 저장하였습니다.</h1>
		<a href = "wifi_list.jsp" id = "home">홈으로 가기</a>
	</center>
</body>
</html>