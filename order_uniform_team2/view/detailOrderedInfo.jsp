<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="bean.*,util.*"%>

<%
	Order order = (Order) request.getAttribute("order");
	User user = (User) request.getAttribute("user");
	Product product = (Product) request.getAttribute("product");

	DateFormat df = new DateFormat();

	String str_date = df.format(order.getPurchase_date());

	int sum;
	sum = Integer.parseInt(order.getSum());
	int price;
	price = sum / order.getQuantity();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>受注詳細</title>
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
	<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style2.css">
</head>
<body>
	<!-- ヘッダー部分　たけだ追記 　6/27-->
	<!-- ヘッダー部分 -->
	<%@ include file="/common/header.jsp"%>

	<div class="alert alert-success" role="alert">
		<h2 align="center">受注詳細</h2>
	</div>
	<div class="detailOrderedInfo_wrapper">
		<table align="center">

			<tr>
				<th class="insert_cart_th">名前</th>
				<td class="insert_cart_td"><%=user.getName()%></td>
			</tr>

			<tr>
				<th class="insert_cart_th">Email</th>
				<td class="insert_cart_td"><%=user.getEmail()%></td>
			</tr>

			<tr>
				<th class="insert_cart_th">〒</th>
				<td class="insert_cart_td"><%=user.getPost_code()%></td>
			</tr>

			<tr>
				<th class="insert_cart_th">住所</th>
				<td class="insert_cart_td"><%=user.getAddress()%></td>
			</tr>

			<tr>
				<th class="insert_cart_th">商品名</th>
				<td class="insert_cart_td"><%=product.getUniform_name()%></td>
			</tr>

			<tr>
				<th class="insert_cart_th">価格</th>
				<td class="insert_cart_td"><%=price%>円</td>
			</tr>

			<tr>
				<th class="insert_cart_th">個数</th>
				<td class="insert_cart_td"><%=order.getQuantity()%>枚</td>
			</tr>

			<tr>
				<th class="insert_cart_th">合計</th>
				<td class="insert_cart_td"><%=order.getSum()%>円</td>
			</tr>

			<tr>
				<th class="insert_cart_th">日時</th>
				<td class="insert_cart_td"><%=str_date%></td>
			</tr>

			<tr>
				<th class="insert_cart_th">備考</th>
				<td class="insert_cart_td"><%=order.getMemo()%></td>
			</tr>


		</table>
	</div>
	<!--  ヘッダーたけだ追記　6/27 -->
	<%@ include file="/common/footer.jsp"%>
</body>
</html>