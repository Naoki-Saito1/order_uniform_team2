<%@page contentType="text/html; charset=UTF-8"%>



<html>

<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>神田ユニフォーム</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style2.css">
</head>

<body>
<%@ include file="/common/header.jsp"%>


<div class="top_img">
	<div class="btn" align="center">
		<form action="<%=request.getContextPath()%>/productList">

			<div class="admin_button_uni" align="center"  >
			<button type="submit">ユニフォームの購入はこちら</button>
</div>
		</form>



		<form action="<%=request.getContextPath()%>/view/login.jsp" method="post">

			<div class="admin_button" align="center"  >
			<button type="submit">管理者はこちらから</button>
			</div>
		</form>
	</div>


</div>
<%@ include file="/common/footer.jsp"%>
</body>

</html>