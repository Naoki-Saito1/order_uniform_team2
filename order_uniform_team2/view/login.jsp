<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Admin"%>

<%
	// たけだ追記　6/27
	String error = (String) request.getAttribute("error");
if(error == null){
	error = "";
}
%>

<html>
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

<link rel="stylesheet" href="<%=request.getContextPath()%>/js/login.js">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">

<title>ログイン</title>
</head>
<body>

	<!-- ヘッダー部分 -->
	<%@ include file="/common/header.jsp"%>
	<div class="alert alert-success" role="alert">
		<h2 align="center">管理者ログイン</h2>
	</div>
	<%if(!error.equals("")){ %>
	<%= error %>
	<%} %>
	<div class="login_wrapper">
		<form action="<%=request.getContextPath()%>/login" method="post"
			id="loginForm">
			<div align="center">
				<!-- cssはproductForm共通です。 -->

				<div class="product_input">

					<label for="adminid"></label> <input type="text" name="adminid"
						placeholder="管理者ID"> <i class="fa fa-child  fa-lg fa-fw"></i>
					<br> <span class="is-error-adminid"></span>
				</div>


				<div class="product_input">

					<label for="password"></label> <input type="password"
						name="password" placeholder="パスワード">
					<i class="fas fa-key  fa-lg fa-fw"></i>
					<br> <span class="is-error-password"></span>
				</div>

				<br> <br> <input type="submit" name="login"
					value="管理者ログイン" class="btn btn-outline-success">
				<!-- <input type="submit" name="insert" value="新規登録"> -->

			</div>
		</form>
	</div>
	<%@ include file="/common/footer.jsp"%>

	<script src="https://code.jquery.com/jquery-1.9.1.min.js"
		integrity="sha256-wS9gmOZBqsqWxgIVgA8Y9WcQOa7PgSIX+rPA0VL2rbQ="
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js"></script>
	<script src="./js/login.js" type="text/javascript"></script>
</body>
</html>