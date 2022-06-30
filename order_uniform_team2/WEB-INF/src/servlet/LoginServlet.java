package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Admin;
import dao.AdminDao;

public class LoginServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 変数を宣言
		String error = "";


		// 文字エンコーディングの設定
		request.setCharacterEncoding("UTF-8");

		// セッションオブジェクトを生成
		HttpSession session = request.getSession();

		// userオブジェクトの生成
		Admin admin = null;

		try {
			// adminid,passwordのパラメータの取得(login.jspより)
			String adminid = request.getParameter("adminid");
			String password = request.getParameter("password");

			// UserDAOをインスタンス化し、関連メソッドを呼び出す
			AdminDao adminDao = new AdminDao();

			// adminデータを取得するメソッド
			admin = adminDao.selectByAdmin(adminid);

			if (admin.getAdminid() == null) {
				error = "入力情報が間違っています。";
				return;


			}else if(!admin.getPassword().equals(password)) {
				error = "パスワードが間違っています。";
				return;

			}else {


				// 取得したAdminオブジェクトをセッションスコープに"admin"という名前で登録
				session.setAttribute("admin", admin);

				// adminidとpasswordをそれぞれクッキーに登録
				Cookie userCookie = new Cookie("adminid", adminid);
				userCookie.setMaxAge(60 * 60 * 24 * 5);
				response.addCookie(userCookie);

				Cookie passwordCookie = new Cookie("password", password);
				passwordCookie.setMaxAge(60 * 60 * 24 * 5);
				response.addCookie(passwordCookie);
			}

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示は行えませんでした。";

		} finally {
			// エラーがない場合
			if (error.equals("")) {
				// orderList.jspにフォワード
				request.getRequestDispatcher("/view/adminMenu.jsp").forward(request, response);
				// エラーがある場合
			} else {
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/login.jsp").forward(request, response);
			}

		}

	}
}