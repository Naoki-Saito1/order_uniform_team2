<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList, bean.*,dao.*, util.*"%>

<%
	ArrayList<Order> order_list = (ArrayList<Order>) request.getAttribute("ordered_list");
	ArrayList<OrderedItem> orderedItem_list = (ArrayList<OrderedItem>) request.getAttribute("orderedItem_list");
	DateFormat dateFormat = new DateFormat();
	int n = 0;



%>

<html>

<head>

<title>受注一覧</title>
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
</head>

<body>

	<%@ include file="/common/header.jsp"%>
	<div class="alert alert-success" role="alert">
		<h2 align="center">受注一覧</h2>
	</div>


	<div class="showOrderList_wrapper">
	<table class="table show_table">
		<thead class="table-success">
			<tr>
				<th class="show_cart_th">名前</th>
				<th class="show_cart_th">商品名</th>
				<th class="show_cart_th">個数</th>
				<th class="show_cart_th">価格</th>
				<th class="show_cart_th">日時</th>
				<th class="show_cart_th">備考</th>
				<th class="show_cart_th" colspan="2" width="100">発送状況</th>

			</tr>
		</thead>
		<%
			if (order_list != null) {
				for (int i = 0; i < orderedItem_list.size(); i++) {
					OrderedItem orderedItem = (OrderedItem) orderedItem_list.get(i);
					Order order = (Order) order_list.get(i);
		%>


		<tr>

			<td class="show_cart_td" width="120"><a
				href="<%=request.getContextPath()%>/detailOrderedInfo?order_no=<%=order.getOrder_no()%>"><%=orderedItem.getUserName()%></a></td>
			<td class="show_cart_td" width="120"><%=orderedItem.getUniformName()%></td>
			<td class="show_cart_td" width="50"><%=orderedItem.getQuantity()%></td>
			<td class="show_cart_td" width="80"><%=orderedItem.getSum()%></td>
			<td class="show_cart_td" width="150"><%=dateFormat.format(order.getPurchase_date())%></td>
			<td class="show_cart_td" width="300"><%=orderedItem.getMemo()%></td>

			<td class="show_cart_td" width="50">
				<%
					if (order.getPayment_status().equals("1")) {
				%>
				入金済
				<%
 	} else {
 %>

				<form action="<%=request.getContextPath()%>/showOrderedList">
					<input type="hidden" name="pay" value="1"> <input
						type="hidden" name="index" value="<%=i%>"> <input
						type="submit" value="未入金" id="btn">
				</form> <%
 	}
 %>
			</td>
			<td class="show_cart_td" width="50">
				<%
					if (order.getDelivery_status().equals("1")) {
				%>
				発送済
				<%
 	} else {
 %>
				<form action="<%=request.getContextPath()%>/showOrderedList">
					<input type="hidden" name="deli" value="1"> <input
						type="hidden" name="index" value="<%=i%>"> <input
						type="submit" value="未発送" id="btn">

				</form>
			</td>
<%
			}
			%>

		</tr>
		<%
			}

		%>



	</table>
	<%
		}else{
			%>
			何もないよ
			<%
		}
		%>
</div>
	<%@ include file="/common/footer.jsp"%>

</body>

</html>