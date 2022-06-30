<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.*"%>

<%
	Order order = (Order) request.getAttribute("order");
	User user = (User) session.getAttribute("user");
	Product product = (Product) request.getAttribute("product");

	//合計金額
	//int sum = order.getQuantity() * product.getPrice();
%>


<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<title>カート追加</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<!-- ヘッダー部分 -->
	<%@ include file="/common/header.jsp"%>
	<p class="insertCart_comment" align="right">
		<font size="4"><strong>以下のご注文でお間違いがなければ、<br>
				カート追加ボタンをおしてください。
		</strong></font>
	</p>

	<br>
	<form action="<%=request.getContextPath()%>/showCart" method="get">
		<table align="center">
			<tr>
				<th class="insert_cart_th">商品</th>
				<td class="insert_cart_td"><%=product.getUniform_name()%></td>
			</tr>
			<tr>
				<th class="insert_cart_th">名前</th>
				<td class="insert_cart_td"><%=user.getName()%></td>
			</tr>
			<tr>
				<th class="insert_cart_th">メール</th>
				<td class="insert_cart_td"><%=user.getEmail()%></td>
			</tr>
			<tr>
				<th class="insert_cart_th">郵便番号</th>
				<td class="insert_cart_td"><%=user.getPost_code()%></td>
			</tr>
			<tr>
				<th class="insert_cart_th">住所</th>
				<td class="insert_cart_td"><%=user.getAddress()%></td>
			</tr>
			<tr>
				<th class="insert_cart_th">枚数</th>
				<td class="insert_cart_td"><%=order.getQuantity()%>枚</td>
			</tr>
			<tr>
				<th class="insert_cart_th">合計金額</th>
				<td class="insert_cart_td"><%=order.getSum()%>円</td>
			</tr>
			<tr>
				<th class="insert_cart_th">備考</th>
				<td class="insert_cart_td"><%=order.getMemo()%></td>
			</tr>
		</table>
		<input type="submit" value="カート追加"
			class="btn btn-outline-primary insert_btn">
	</form>
	<a class="btn btn-outline-primary insert_btn_return"
		href="<%=request.getContextPath()%>/productForm?uniformid=<%=product.getUniformid()%>">戻る</a>
	<%@ include file="/common/footer.jsp"%>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>