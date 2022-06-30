
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.*,dao.*,java.util.*"%>

<%
	// セッションからの取得
	ArrayList<OrderedItem> list = (ArrayList<OrderedItem>) session.getAttribute("cartList");

	User user = (User)session.getAttribute("user");
%>

<html>
<head>

<title>カート内</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<!-- ヘッダー部分 -->
	<%@ include file="/common/header.jsp"%>

	<h2 class="" align="right"></h2>


	<% if(list != null && user != null){ %>
	<div class="alert alert-primary" role="alert">
		<h2 align="center"><%=user.getName()%></h2>
		<h5 align="right">さんのカート</h5>
	</div>

	<div class="showCart_wrapper">
	<form action="<%=request.getContextPath()%>/buyConfirm">
		<table class="table show_table">
			<thead class="table-primary">
				<tr class="show_cart_tr">
					<th class="show_cart_th">商品</th>
					<th class="show_cart_th">値段</th>
					<th class="show_cart_th">個数</th>
					<th class="show_cart_th">&nbsp;合計金額&nbsp;</th>
					<th class="show_cart_th">備考欄</th>
					<th class="show_cart_th">削除</th>

				</tr>
			</thead>
			<%
				// 繰り返し文中でカート情報を表示
				for (int i = 0; i < list.size(); i++) {
					OrderedItem orderedItem = (OrderedItem) list.get(i);
					int sum;
					sum = Integer.parseInt(orderedItem.getSum());
					int price;
					price = sum / orderedItem.getQuantity();
			%>
			<tr class="show_cart_tr">
				<!-- uniformidはセッションに格納されているままです。 -->
				<td class="show_cart_td" width="150"><%=orderedItem.getUniformName()%></td>
				<td class="show_cart_td" width="100"><%=price%></td>
				<td class="show_cart_td" width="50"><%=orderedItem.getQuantity()%></td>
				<td class="show_cart_td" width="100"><%=orderedItem.getSum()%></td>
				<td class="show_cart_td memo" width="350px"><%=orderedItem.getMemo()%></td>
				<td class="show_cart_td" width="100px"><a
					class="btn btn-primary"
					href="<%=request.getContextPath()%>/showCart?num=<%=i%>">削除</a> <!-- 削除ボタン押下時、登録前の情報がlistから削除される -->
				</td>
			</tr>

			<%
				}
			%>

		</table>

		<input type="submit" value="購入"
			class="btn btn-outline-primary btn-lg show_cart_btn">
	</form>
	</div>
		<% }else{ %>
		<div class="alert alert-secondary aleat-showCart" role="alert">
				<h2 align="center">カートに何も入っていません。</h2>
		</div>
			<div class="alert alert-secondary aleat-showCart" role="alert">
				<h2 align="center"><a href="<%=request.getContextPath() %>/productList">一覧に戻る</a></h2>
		</div>
		<% } %>
	<%@ include file="/common/footer.jsp"%>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

</body>
</html>

