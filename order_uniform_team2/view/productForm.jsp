<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.*"%>

<%
	Product product = (Product) request.getAttribute("product");
	String error = (String) request.getAttribute("error");
	User user = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文情報入力</title>
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

<link rel="stylesheet" href="<%=request.getContextPath()%>/js/style.js">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style2.css">
</head>
<body>
	<!-- ヘッダー部分 -->
	<%@ include file="/common/header.jsp"%>


	<div class="alert alert-primary" role="alert">
		<span class="uniform_title">選択された商品</span>
		<h2 align="center"><%=product.getUniform_name()%></h2>
	</div>


	<%
		if (error != null) {
	%>

	<%
		}
	%>

	<div class="product_form">

		<form action="<%=request.getContextPath()%>/insertCart" method="post"
			id="form">

			<input type="hidden" name="uniformid"
				value="<%=product.getUniformid()%>">



			<div class="product_input">

				<%
					if (user != null) {
				%><label for="name">お名前</label>
				<%
					}
				%>
				<input type="text" name="name" placeholder="お名前"
					value="<%if (user != null) {%><%=user.getName()%><%}%>"> <i
					class="fa fa-user fa-lg fa-fw" aria-hidden="true"></i> <br> <span
					class="is-error-name"></span>
			</div>



			<div class="product_input">

				<%
					if (user != null) {
				%><label for="name">email</label>
				<%
					}
				%>
				<input type="text" name="email" placeholder="Email"
					value="<%if (user != null) {%><%=user.getEmail()%><%}%>"> <i
					class="fas fa-envelope fa-lg fa-fw"></i> <br> <span
					class="is-error-email"></span>
			</div>


			<div class="product_input">
				<%
					if (user != null) {
				%><label for="post_code">郵便番号</label>
				<%
					}
				%>
				<input type="text" size="6" name="post_code1" placeholder="000"
					value="<%if (user != null) {%><%=user.getPost_code().substring(0, 3)%><%}%>">
				-<input type="text" size="10" name="post_code2" placeholder="0000"
					value="<%if (user != null) {%><%=user.getPost_code().substring(3, 7)%><%}%>">
				<i class="fa fa-location-arrow fa-lg fa-fw"></i> <br> <span
					class="is-error-post_code1"></span><span
					class="is-error-post_code2"></span>
			</div>


			<div class="product_input">
				<%
					if (user != null) {
				%><label for="address">住所</label>
				<%
					}
				%>
				<input type="text" name="address" placeholder="住所"
					value="<%if (user != null) {%><%=user.getAddress()%><%}%>">
				<i class="fas fa-map-marker-alt fa-lg fa-fw"></i> <br> <span
					class="is-error-address"></span>
			</div>


			<div class="product_input">
			<%
					if (user != null) {
				%><label for="address">枚数</label>
				<%
					}
				%>
				<input type="number" name="quantity" placeholder="枚数"> <i
					class="fa fa-check fa-lg fa-fw"></i> <br> <span
					class="is-error-quantity"></span>
			</div>



			<div class="product_textarea">
				<label for="memo"></label>
				<textarea name="memo" rows="7" cols="54" placeholder="備考"></textarea>
			</div>

			<button class="btn btn-outline-primary product_btn" type="submit">送信</button>


		</form>

	</div>
	<%@ include file="/common/footer.jsp"%>
	<script src="https://code.jquery.com/jquery-1.9.1.min.js"
		integrity="sha256-wS9gmOZBqsqWxgIVgA8Y9WcQOa7PgSIX+rPA0VL2rbQ="
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js"></script>
	<script src="./js/style.js" type="text/javascript"></script>
</body>
</html>



