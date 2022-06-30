package servlet;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//セッションオブジェクトを生成
		HttpSession session=request.getSession();

		//セッションがある場合、無効にする
		if(session!=null) {
			session.invalidate();
		}
			request.getRequestDispatcher("/view/login.jsp").forward(request,response);
	}
}

