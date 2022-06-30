package servlet;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import bean.Product;
import dao.ProductDao;

public class ProductListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String error = "";

		ArrayList<Product> product_list = null;

		ProductDao productDao = new ProductDao();

		try {

			product_list = productDao.selectAll();

//			*****データがない場合、初期データ挿入*****

			ArrayList<Product> products = null;

			if (product_list.size() == 0) {
				products = new ArrayList<Product>();
				String[] init_date = { "A", "B", "C", "D", "E" };
				int[] init_price = { 1000, 1500, 2000, 2500, 3000 };
				for (int i = 0; i < 5; i++) {
					Product product = new Product("soccer" + i, "ユニフォーム " + init_date[i], 500, init_price[i]);
					products.add(product);
				}
				System.out.println(products.size());
				for (int n = 0; n < products.size(); n++) {
					productDao.insert(products.get(n));
					System.out.println(products.get(n).getUniform_name());
				}
				product_list = productDao.selectAll();
			}
//			*****データがない場合、初期データ挿入*****

			request.setAttribute("product_list", product_list);

		} catch (IllegalStateException e) {
			error = "db接続エラーのため一覧を表示できませんでした。";

			// TODO: handle exception
		} finally {
			if (error.equals("")) {
				request.getRequestDispatcher("/view/productList.jsp").forward(request, response);
			} else {
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

		}

	}
}