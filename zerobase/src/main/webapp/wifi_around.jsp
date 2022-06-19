<%@page import="wifi_project.wifi_parsing"%>
<%@page import="wifi_project.wifi_getset"%>
<%@page import="java.util.List"%>

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
	margin-top : 20px;
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

<script>

	<%
	String myLAT = "37.258137";
	String myLNT = "127.033444";
	%>

	function input_text() {
		document.getElementById("LAT").value=<%=myLAT%>;
		document.getElementById("LNT").value=<%=myLNT%>;
	}
</script>
</head>
<body>
	<%
	String LAT1 = request.getParameter("LAT");
	String LNT1 = request.getParameter("LNT");
	wifi_parsing wifiParsing = new wifi_parsing();
	List<wifi_getset> wifi_getsetList = wifiParsing.list(LAT1,LNT1);
	%>
	
	<h1>와이파이 정보 구하기</h1>

	<div>
		<a href = "wifi_list.jsp">홈</a> |
		<a href = "wifi_history.jsp">위치 히스토리 목록</a> |
		<a href = "wifi_load.jsp">Open API 와이파이 정보 가져오기</a>
	</div>
	
	<form action="wifi_around.jsp?">
		LAT: 
		<input type="text" id="LAT" name="LAT">
		,LNT: 
		<input type="text" id="LNT" name="LNT">
		<button onclick="input_text()">내 위치 가져오기</button>
		<input type="submit" value="근처 WIFI 정보 보기">
	</form>
	
	<table>
		<thead>
			
			<tr>
				<th>거리(Km)</th>
				<th>관리번호</th>
				<th>자치구</th>
				<th>와이파이명</th>
				<th>도로명주소</th>
				<th>상세주소</th>
				<th>설치위치(층)</th>
				<th>설치유형</th>
				<th>설치기관</th>
				<th>서비스구분</th>
				<th>망종류</th>
				<th>설치년도</th>
				<th>실내외구분</th>
				<th>WIFI접속환경</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>작업일자</th>
			</tr>
			
		</thead>
		<tbody>
			<tr >
				<%
				for (wifi_getset wifigs : wifi_getsetList) {
				%>
			
				<tr>
					<th><%=wifigs.getDistance()%></th>
					<th><%=wifigs.getX_SWIFI_MGR_NO()%></th>
					<th><%=wifigs.getX_SWIFI_WRDOFC()%></th>
					<th><%=wifigs.getX_SWIFI_MAIN_NM()%></th>
					<th><%=wifigs.getX_SWIFI_ADRES1()%></th>
					<th><%=wifigs.getX_SWIFI_ADRES2()%></th>
					<th><%=wifigs.getX_SWIFI_INSTL_FLOOR()%></th>
					<th><%=wifigs.getX_SWIFI_INSTL_TY()%></th>
					<th><%=wifigs.getX_SWIFI_INSTL_MBY()%></th>
					<th><%=wifigs.getX_SWIFI_SVC_SE()%></th>
					<th><%=wifigs.getX_SWIFI_CMCWR()%></th>
					<th><%=wifigs.getX_SWIFI_CNSTC_YEAR()%></th>
					<th><%=wifigs.getX_SWIFI_INOUT_DOOR()%></th>
					<th><%=wifigs.getX_SWIFI_REMARS3()%></th>
					<th><%=wifigs.getLAT()%></th>
					<th><%=wifigs.getLNT()%></th>
					<th><%=wifigs.getWORK_DTTM()%></th>
				</tr>
				<%
				}
				%>
			</tr>
		</tbody>
	</table>

</body>
</html>