/*
 * 作成日：武田
 * 作成日：2022/6/22
 * ファイルの内容：カートに追加(InsertCartServlet)からの遷移
 */

package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Order;
import bean.OrderedItem;
import bean.Product;
import bean.User;
import dao.ProductDao;

public class ShowCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String error = "";
		String cmd = "";

		try {
			// 文字エンコーディングの指定
			request.setCharacterEncoding("UTF-8");

			// セッションスコープからの取得
			HttpSession session = request.getSession();
			ArrayList<Order> list = (ArrayList<Order>) session.getAttribute("order_list");
			User user = (User) session.getAttribute("user");

			ArrayList<OrderedItem> cartList = new ArrayList<OrderedItem>();

			// 商品名を取得するためのオブジェクト生成
			ProductDao productDao = new ProductDao();

			// 画面からインデックス№を取得
			String num = request.getParameter("num");

			// 削除ボタン押下時 ArrayListから該当のインデックスを削除する
			if (num != null) {
				list.remove(Integer.parseInt(num));
			}

			if (list != null) {

				for (int i = 0; i < list.size(); i++) {
					Order order = (Order) list.get(i);
					;
					// カート内容を表示するだけのDTOクラスのオブジェクト生成
					OrderedItem orderedItem = new OrderedItem();

					// 商品情報テーブルから該当情報を取得するメソッドを呼出
					Product product = productDao.selectByProduct(order.getUniformid());

					orderedItem.setUniformId(order.getUniformid());
					orderedItem.setUniformName(product.getUniform_name());
					orderedItem.setQuantity(order.getQuantity());
					orderedItem.setSum(order.getSum());
					orderedItem.setMemo(order.getMemo());

					orderedItem.setUserName(user.getName()); // ユーザー名
					orderedItem.setEmail(user.getEmail());
					orderedItem.setPostCode(user.getPost_code());
					orderedItem.setAddress(user.getAddress());

					cartList.add(orderedItem);
				}


			}
			// カート情報をセッションに登録
			session.setAttribute("cartList", cartList);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、購入状況確認は出来ません。";
			cmd = "top";

		} finally {
			if (error.equals("")) {
				request.getRequestDispatcher("/view/showCart.jsp").forward(request, response);

				// エラーが発生している場合
			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);

				request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			}

		}

	}
}
