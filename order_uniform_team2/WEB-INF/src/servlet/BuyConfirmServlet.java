package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.*;
import dao.*;
import util.SendMail;

public class BuyConfirmServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// エラーメッセージ用変数
		String error = "";
		// 遷移先情報格納用変数
		String cmd = "";


		try {

			// 文字コード設定
			request.setCharacterEncoding("UTF-8");

			// セッションから"user"を取得
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");

			/*
			 * セッション切れの場合
			 */
			if (user == null) {
				error = "セッション切れの為、最初からやり直してください。";
				cmd = "tpo";
				return;
			}

			// セッションから"cart_list"を取得
			ArrayList<OrderedItem> orderedItem_list = (ArrayList<OrderedItem>) session.getAttribute("cartList");

			/*
			 * カートの中身がない場合はerror.jspに遷移
			 */
			if (orderedItem_list.size() == 0) {
				error = "カートの中に何もなかったので購入はできません。";
				cmd = "top";
				return;
			}

			/*
			 * 購入が確定したため、User情報をDBに登録
			 */
			UserDao userDao = new UserDao();
			userDao.insert(user);

			/*
			 * DBに登録したUser情報を取得
			 */
			User User = userDao.selectByUser(user);

			/*
			 *DBの"order_info"に購入情報とユーザーの"userid"を登録
			 */
			OrderedItemDao orderedDao = new OrderedItemDao();
			for (int i = 0; i < orderedItem_list.size(); i++) {
				OrderedItem orderedItem = (OrderedItem) orderedItem_list.get(i);
				orderedDao.insert(orderedItem, User.getUserid());

			}

			/*
			 * "order_list"の注文情報内容をメール送信
			 */

			// メール宛先を指定
			String email = User.getEmail();
			// メール件名
			String subject = "ご注文確認メール";
			// メール本文
			// 合計金額
			int total = 0;
			int sum = 0;
			String text = "ユニフォームのご購入ありがとうございます。\n" + "以下の内容でご注文を受け付けましたので、ご連絡いたします。\n\nユニフォーム名\t\t枚数\t金額\n";

			for (int i = 0; i < orderedItem_list.size(); i++) {
				OrderedItem orderedItem = (OrderedItem) orderedItem_list.get(i);
				text += orderedItem.getUniformName() + "\t\t" + orderedItem.getQuantity() + "\t" + orderedItem.getSum()
						+ "円\n";
				sum = Integer.parseInt(orderedItem.getSum());
				total += sum;
			}

			text += "\n合計 " + total + "円\n";
			text += "\nまたのご利用よろしくお願いいたします。";

			SendMail sendMail = new SendMail();
			sendMail.SendingMail(email, subject, text);

			/*
			 *購入された枚数をDBの在庫数から引く
			 */
			ProductDao productDao = new ProductDao();
			int orderedQuantity = 0;
			int stock_quantity = 0;
			for (int i = 0; i < orderedItem_list.size(); i++) {
				OrderedItem ordered = (OrderedItem) orderedItem_list.get(i);
				Product product = productDao.selectByProduct(ordered.getUniformId());

				if (product.getUniformid().equals("soccer0")) {
					orderedQuantity += ordered.getQuantity();
				}
				if (product.getUniformid().equals("soccer1")) {
					orderedQuantity += ordered.getQuantity();
				}
				if (product.getUniformid().equals("soccer2")) {
					orderedQuantity += ordered.getQuantity();
				}
				if (product.getUniformid().equals("soccer3")) {
					orderedQuantity += ordered.getQuantity();
				}
				if (product.getUniformid().equals("soccer4")) {
					orderedQuantity += ordered.getQuantity();
				}

				stock_quantity = product.getStock_quantity() - orderedQuantity;
				productDao.updateStock_quantity(stock_quantity, ordered.getUniformId());
			}



			/*
			 * セッションに登録されていたユーザー情報と
			 * カート内情報を削除
			 */
			session.invalidate();

			/*
			 * リクエストスコープに受注内容を格納
			 */
			request.setAttribute("orderedItem_list", orderedItem_list);
			request.setAttribute("user", user);


		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、購入はできません。";
			cmd = "logout";
		} finally {
			if (error.equals("")) {
					// buyConrirm.jspにフォワード
					request.getRequestDispatcher("/view/buyConfirm.jsp").forward(request, response);

			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}

}
