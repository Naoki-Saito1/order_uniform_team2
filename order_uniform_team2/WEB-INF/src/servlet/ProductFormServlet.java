package servlet;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import bean.Product;
import dao.ProductDao;

public class ProductFormServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String error = "";
		String uniformid = request.getParameter("uniformid");

		ProductDao productDao = new ProductDao();

		Product product = null;

		try {

			product = productDao.selectByProduct(uniformid);

			request.setAttribute("product", product);

		} catch (IllegalStateException e) {
			error = "db接続エラーのため入力フォームを表示できませんでした。";

			// TODO: handle exception
		} finally {

			if (error.equals("")) {
				request.getRequestDispatcher("/view/productForm.jsp").forward(request, response);
			} else {
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

		}

	}
}
