<!--
作成者：たけだりほ
作成日：2022/6/27
 -->
<%@page contentType="text/html; charset=UTF-8"%>

<%
String error = (String)request.getAttribute("error");
String cmd = (String)request.getAttribute("cmd");
%>
<html>
<head>
<title>エラー</title>
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/style.css">
	<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/style2.css">
</head>
<body>


	<div class="error_wrapper">
		<h1>ユニフォーム受注システム</h1>
		<h3>エラー画面</h3>
	</div>



	<div class="error" align="center">
		<p class="error_p"><%=error %></p>
		<br>
		<%
			if (cmd.equals("top")) {
		%>
		<a href="<%=request.getContextPath()%>/view/top.jsp">[トップへ戻る]</a>

		<%
			} else if (cmd.equals("logout")) {
		%>
		<a href="<%=request.getContextPath()%>/logout">[ログイン画面へ]</a>
		<%
			} else if (cmd.equals("amenu")) {
		%>
		<a href="<%=request.getContextPath()%>/view/adminMenu.jsp">[メニュー画面へ]</a>

		<%
			} else {
		%>
		<%-- null時も一覧表示に戻る --%>
		<a href="<%=request.getContextPath()%>/productList">[一覧表示へ戻る]</a>
		<%
			}
		%>

	</div>

	<%@ include file="/common/footer.jsp"%>
</body>
</html>