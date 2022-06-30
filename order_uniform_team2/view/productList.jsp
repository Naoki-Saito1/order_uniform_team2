<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Product"%>

<%
	ArrayList<Product> product_list = (ArrayList<Product>) request.getAttribute("product_list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品一覧</title>
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
	<!-- ヘッダー部分 -->
	<%@ include file="/common/header.jsp"%>

<div class="alert alert-primary" role="alert">
		<h2 align="center">SALE！！</h2>
	</div>
	<div class="productList_wrapper">
		<%
			for (int i = 0; i < product_list.size(); i++) {
		%>


		<section class="card">
			<img class="card-img" src="img/uni<%=(i + 1)%>.png" alt="uni1">
			<div class="card-content">
				<h2 class="card-title">
					<a
						href="<%=request.getContextPath()%>/productForm?uniformid=<%=product_list.get(i).getUniformid()%>"><%=product_list.get(i).getUniform_name()%></a>
				</h2>
				<h2 class="card-title"><%=product_list.get(i).getPrice()%></h2>
				<p class="card-text">着れば試合に必ず勝てます。</p>
				<p class="card-text">
					在庫数/<%=product_list.get(i).getStock_quantity()%></p>
			</div>
			<div class="card-link">
				<a
					href="<%=request.getContextPath()%>/productForm?uniformid=<%=product_list.get(i).getUniformid()%>">購入はこちらをクリック</a>
			</div>

		</section>

		<%
			}
		%>
	</div>

	<br>
	<br>

	<%@ include file="/common/footer.jsp"%>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>









