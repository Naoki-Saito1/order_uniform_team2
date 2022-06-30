<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.*,dao.*"%>

<%
ArrayList<OrderedItem> order_list = (ArrayList<OrderedItem>)request.getAttribute("orderedItem_list");
User user =(User)request.getAttribute("user");
%>

<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
	<title>購入確定</title>
</head>
<body>

	<!-- ヘッダー部分 -->
	<%@ include file="/common/header.jsp"%>

	<div class="alert alert-primary" role="alert">
		<h3 align="center">
		【<%= user.getName() %>】様<br>以下の商品の購入が確定しました。<br>ありがとうございました。
	</h3>
	</div>

<div class="buyConfirm_wrapper">
	<table class="table buy_table">
		<thead class="table-primary">
			<tr class="show_cart_tr">
				<th class="show_cart_th">商品</th>
				<th class="show_cart_th">値段</th>
				<th class="show_cart_th">個数</th>
				<th class="show_cart_th">&nbsp;合計金額&nbsp;</th>
				<th class="show_cart_th">備考欄</th>
			</tr>
		</thead>
		<%

					if(order_list!=null){
						for(int i = 0; i < order_list.size(); i++) {
							OrderedItem order = (OrderedItem)order_list.get(i);

							int sum;
							sum = Integer.parseInt(order.getSum());
							int price;
							price = sum / order.getQuantity();

							%>
		<tr class="show_cart_tr">
			<td class="show_cart_td"><%=order.getUniformName() %></td>
			<td class="show_cart_td"><%= price %>円</td>
			<td class="show_cart_td"><%=order.getQuantity() %></td>
			<td class="show_cart_td"><%=order.getSum() %></td>
			<td class="show_cart_td" width="300px"><%=order.getMemo() %></td>
		</tr>
		<%

						}
					}
					%>

	</table>

	<br>
	<br>
	<div align="center">
		【<a href="<%= request.getContextPath() %>/productList">ユニフォームリストへ戻る</a>】
	</div>
	</div>
	<%@ include file="/common/footer.jsp"%>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>