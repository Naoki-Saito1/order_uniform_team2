package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.util.ArrayList;
import bean.*;
import dao.*;
import util.SendMail;

public class ShowOrderedListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// エラー用変数
		String error = "";
		// 遷移先用変数
		String cmd = "";

		try {
			// 文字コード設定
			request.setCharacterEncoding("UTF-8");

			OrderDao orderDao = new OrderDao();

			/*
			 * DBから注文されたデータを取得
			 */
			ArrayList<Order> ordered_list = orderDao.selectAll();

			// "ordered_list"にデータがあるか確認
			if (ordered_list == null) {
				error = "注文されたデータはありません。";
				cmd = "menu";
				return;
			}


			/*
			 * payment_statusとdelivery_statusのパラメータ取得
			 */
			String pay = request.getParameter("pay");
			String deli = request.getParameter("deli");
			String index = request.getParameter("index");
			int Index = 0 ;
			if(index != null) {
				Index = Integer.parseInt(index);
			}

			/*
			 * 入金・発送が完了したら、ユーザーにメールを送る
			 */
			String email = null;
			String subject = null;
			String text = null;

			/*
			 *受注一覧画面の入金発送ボタンが押されたら、
			 *DBのステータスデータを更新する処理
			 *入金済み・発送済みの際はメールを送信する。
			 */
			Order Order = new Order();
			UserDao userDao = new UserDao();
			SendMail sendMail = new SendMail();
			if (pay != null && index != null) {
				Order = (Order) ordered_list.get(Index);
				orderDao.updatePayment(pay, Order.getOrder_no());
				ordered_list.set(Index, Order);

				if(pay.equals("1")) {
					User user = userDao.selectByUserid(Order.getUserid());
					email = user.getEmail();
					subject = "入金受付のご連絡 | 株式会社神田ユニフォーム";
					text = "株式会社神田ユニフォームです。\n\n"
							+ "この度は弊社からユニフォームのお買い上げありがとうございました。\n"
							+ "お客様からのご入金を確認いたしましたので、商品の発送まで今しばらくお待ちください。\n\n"
							+ "以上、よろしくお願いいたします。";

					sendMail.SendingMail(email, subject, text);
				}
			}

			if (deli != null && index != null) {
				Order = (Order) ordered_list.get(Index);
				orderDao.updateDelivery(deli, Order.getOrder_no());
				ordered_list.set(Index, Order);

				if(deli.equals("1")) {
					User user = userDao.selectByUserid(Order.getUserid());
					email = user.getEmail();
					subject = "発送完了のご連絡 | 株式会社神田ユニフォーム";
					text = "株式会社神田ユニフォームです。\n\n"
							+ "この度は弊社からユニフォームのお買い上げありがとうございました。\n"
							+ "ご注文いただいた商品の発送が完了いたしました。\n\n"
							+ "以上、よろしくお願いいたします。";

					sendMail.SendingMail(email, subject, text);
				}
			}




			/*
			 * 受注一覧画面に表示するデータを
			 * "OrderedItem"オブジェクトに格納
			 */
			ArrayList<OrderedItem> orderedItem_list = new ArrayList<OrderedItem>();
			ProductDao productDao = new ProductDao();
			for (int i = 0; i < ordered_list.size(); i++) {
				OrderedItem orderedItem = new OrderedItem();
				Order ordered = (Order) ordered_list.get(i);
				User user = userDao.selectByUserid(ordered.getUserid());
				Product product = productDao.selectByProduct(ordered.getUniformid());
				orderedItem.setUserName(user.getName());
				orderedItem.setUniformName(product.getUniform_name());
				orderedItem.setQuantity(ordered.getQuantity());
				orderedItem.setSum(String.valueOf(ordered.getQuantity() * product.getPrice()));
				orderedItem.setMemo(ordered.getMemo());
				orderedItem_list.add(orderedItem);
			}


			/*
			 * 注文されたデータがあった場合、 リクエストスコープに格納
			 */
			request.setAttribute("ordered_list", ordered_list);
			request.setAttribute("orderedItem_list", orderedItem_list);


		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、受注内容を表示できません。";
			cmd = "logout";

		} catch (Exception e) {
			error = "予期せぬエラーが発生したので、受注内容を表示できません。";
			cmd = "top";

		} finally {
			if (error.equals("")) {
				request.getRequestDispatcher("/view/showOrderedList.jsp").forward(request, response);

			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}

}
