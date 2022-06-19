<%@page import="wifi_project.wifi_getset"%>
<%@page import="java.util.List"%>
<%@page import="wifi_project.wifi_parsing"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
div {
	height: 40px;
}

table {
	width: 100%;
}

thead {
	background-color: #009900;
	color: #feffff;
}

th, td {
	height: 40px;
	border: solid 1px #000;
}
</style>

</head>
<body>
	<%
	wifi_parsing wifiParsing = new wifi_parsing();
	List<wifi_getset> wifiGetsetList = wifiParsing.record_list();
	%>
	<h1>와이파이 정보 구하기</h1>

	<div>
		<a href="wifi_list.jsp">홈</a> |
		<a href="wifi_history.jsp">위치 히스토리 목록</a> |
		<a href="wifi_load.jsp">Open API 와이파이 정보 가져오기</a>
	</div>

	<table>
		<thead>
			<tr>
			<tr>
				<th>ID</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>조회일자</th>
				<th>비고</th>
			</tr>
			</tr>
		</thead>
		<tbody>
			<%
			for (wifi_getset wifigs : wifiGetsetList) {
			%>
			
			<tr>
			<th><%=wifigs.getRow()%></th>
				<th><%=wifigs.getLAT()%></th>
				<th><%=wifigs.getLNT()%></th>
				<th><%=wifigs.getWORK_DTTM()%></th>
				<th>
					<button onclick="location.href='wifi_history_delete.jsp?datetime=<%=wifigs.getWORK_DTTM()%>'">삭제</button>
				</th>
			
			</tr>
			<%
			}
			%>
		</tbody>
	</table>

</body>
</html>