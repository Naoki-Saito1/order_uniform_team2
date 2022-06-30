package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.*;
import dao.*;

public class DetailOrderedInfoServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String error = "";
		String cmd = "";
		String str_order_no = request.getParameter("order_no");
		try {

			int order_no = Integer.parseInt(str_order_no);

//		DAOクラスを生成
			OrderDao orderDao = new OrderDao();
			UserDao userDao = new UserDao();
			ProductDao productDao = new ProductDao();

//		オーダー情報に紐づく主キーをもとにデータ取得
			Order o = orderDao.selectByOrder(order_no);
			User u = userDao.selectByUserid(o.getUserid());
			Product p = productDao.selectByProduct(o.getUniformid());

			request.setAttribute("order", o);
			request.setAttribute("user", u);
			request.setAttribute("product", p);

		} catch (IllegalStateException e) {
			error = "db接続エラーのため受注詳細画面を表示できませんでした。";
			cmd = "menu";

		} catch (Exception e) {
			error = "予期せぬエラーが発生したので、受注詳細画面を表示できません。";
			cmd = "menu";

		} finally {
			if (error.equals("")) {
				request.getRequestDispatcher("/view/detailOrderedInfo.jsp").forward(request, response);

			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

		}

	}
}
