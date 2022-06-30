package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.util.ArrayList;
import dao.*;
import bean.*;

public class InsertCartServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// エラー用変数
		String error = "";
		// 遷移先用変数
		String cmd = "";

		ProductDao productDao = new ProductDao();
		Product product = new Product();

		try {

			// 文字コード設定
			request.setCharacterEncoding("UTF-8");

			/*
			 * パラメーター取得
			 */
			String uniformid = request.getParameter("uniformid");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String post_code1 = request.getParameter("post_code1");
			String post_code2 = request.getParameter("post_code2");
			String post_code = (post_code1 + post_code2);
			String address = request.getParameter("address");
			String strQuantity = request.getParameter("quantity");
			String memo = request.getParameter("memo");

			/*
			 * パラメーターの入力判定
			 */
			// name
			if (name.equals("")) {
				error = "名前を入力して下さい。";
				cmd = "productForm";
				return;
			}
			// email
			if (email.equals("")) {
				error = "メールアドレスを入力して下さい。";
				cmd = "productForm";
				return;
			}
			// post_code
			if (post_code.equals("")) {
				error = "郵便番号を入力して下さい。";
				cmd = "productForm";
				return;
			}
			// address
			if (address.equals("")) {
				error = "住所を入力して下さい。";
				cmd = "productForm";
				return;
			}
			// quantity
			int quantity;
			try {
				quantity = Integer.parseInt(strQuantity);

			} catch (NumberFormatException e) {
				error = "数値を入力して下さい。";
				cmd = "productForm";
				return;
			}

			/*
			 * Userオブジェクトを生成し、 Userオブジェクトにユーザー情報格納
			 */
			User user = new User();
			user.setName(name);
			user.setEmail(email);
			user.setPost_code(post_code);
			user.setAddress(address);

			/*
			 * ユニフォーム情報を取得し、合計金額を算出
			 */
			product = productDao.selectByProduct(uniformid);
			String sum = String.valueOf(product.getPrice() * quantity);

			/*
			 * Orderオブジェクトを生成し、 各データを格納
			 */
			Order order = new Order();
			order.setUserid(user.getUserid());
			order.setUniformid(product.getUniformid());
			order.setQuantity(quantity);
			order.setSum(sum);
			order.setMemo(memo);

			/*
			 * 注文されたデータをArrayListに格納 注文されたデータがない場合は新しく生成
			 */
			HttpSession session = request.getSession();
			ArrayList<Order> order_list = (ArrayList<Order>) session.getAttribute("order_list");
			if (order_list == null) {
				order_list = new ArrayList<Order>();
			}
			order_list.add(order);

			/*
			 * UserとOrderをセッションに格納
			 */
			session.setAttribute("user", user);
			session.setAttribute("order_list", order_list);

			/*
			 * ProductとOrderをリクエストスコープに格納
			 */
			request.setAttribute("product", product);
			request.setAttribute("order", order);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、商品をカートに追加できませんでした。";
			cmd = "list";

		} catch (Exception e) {
			error = "予期せぬエラーが発生したので、商品をカートに追加できませんでした。";
			cmd = "menu";

		} finally {
			// エラーがない場合
			if (error.equals("")) {
				request.getRequestDispatcher("/view/insertCart.jsp").forward(request, response);

				// エラーありの場合
			} else {
				if (cmd.equals("productForm")) {
					request.setAttribute("product", product);
					request.setAttribute("error", error);
					request.getRequestDispatcher("/view/productForm.jsp").forward(request, response);
				} else {
					request.setAttribute("error", error);
					request.setAttribute("cmd", cmd);
					request.getRequestDispatcher("/view/error.jsp").forward(request, response);
				}
			}

		}
	}
}
