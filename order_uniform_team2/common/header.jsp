<%--
 作成者：たけだ
 作成日：6/28
 --%>

<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.Admin"%>
<%
 //セッションからユーザー情報を取得
	Admin admin = (Admin) session.getAttribute("admin");
%>
<html>
	<head>
		<title>ヘッダー</title>
		<%-- ヘッダーのJSP --%>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style2.css">
	</head>
	<body>
		<!-- 会社名画像 -->
		<img class="title_img" src="<%=request.getContextPath() %>/img/bigletter.png">

		<!-- プルダウンメニュー -->
			<!-- 管理者ユーザー情報があるかないかで、判断する -->
			<ul class="menu">
			<%
			if(admin != null){	// 管理ログインがされたとき
			%>
			<li class="menu__single"><a href="#" class="init-bottom">  管理者メニュー</a>
				<ul class="menu__second-level">
					<li><a href="<%=request.getContextPath() %>/showOrderedList">受注一覧</a></li>
					<li><a href="<%=request.getContextPath() %>/logout">ログアウト</a></li>
				</ul></li>

			<%

			}else{

			%>

			<li class="menu__single"><a href="#" class="init-bottom">  ユーザーメニュー</a>
				<ul class="menu__second-level">
					<li><a href="<%=request.getContextPath() %>/showCart">カート確認</a></li>
					<li><a href="<%=request.getContextPath() %>/productList" class="init-bottom">ユニフォーム一覧</a></li>
				</ul></li>
			<%
			}
			%>
			<li class="menu__single"><a href="<%=request.getContextPath() %>/view/top.jsp" class="init-bottom">  トップメニュー</a>
			</li>
		</ul>
	</body>
</html>
