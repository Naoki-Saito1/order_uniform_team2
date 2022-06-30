<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.*,bean.Admin"%>

<%

%>

<html>
<head>
<meta charset="UTF-8">
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/b733a092ed.js"
	crossorigin="anonymous"></script>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
<title>管理者メニュー</title>
</head>
<body>
	<!-- ヘッダー部分 -->
	<%@ include file="/common/header.jsp"%>
<div class="alert alert-success" role="alert">
  <h2 align="center">管理者メニュー</h2>
</div>
	<div class="admin_button" align="center">
		<a href="<%=request.getContextPath()%>/showOrderedList">受注一覧画面</a><br>
	</div>

	<div class="admin_button" align="center">
		<a href="<%=request.getContextPath()%>/logout">ログアウト<i
			class="fa fa-sign-out "></i>
		</a>
	</div>

	<%@ include file="/common/footer.jsp"%>


</body>
</html>