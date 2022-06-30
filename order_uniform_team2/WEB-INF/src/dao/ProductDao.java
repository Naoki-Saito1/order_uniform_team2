package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Product;

public class ProductDao {
	private static String RDB_DRIVE = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost/uniformdb";
	private static String USER = "root";
	private static String PASSWD = "root123";

	public static Connection getConnection() {
		try {
			Class.forName(RDB_DRIVE);
			Connection con = DriverManager.getConnection(URL, USER, PASSWD);
			return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	// ----------------------------------selectAll------------------------------------

	public ArrayList<Product> selectAll() {

//		リターン用のアレーリスト
		ArrayList<Product> list = new ArrayList<Product>();

		// 変更
		String sql = "SELECT * FROM product_info Order BY uniformid";

		Connection con = null;
		Statement smt = null;

		try {
			con = ProductDao.getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				Product product = new Product();
				product.setUniformid(rs.getString("uniformid"));
				product.setUniform_name(rs.getString("uniform_name"));
				product.setStock_quantity(rs.getInt("stock_quantity"));
				product.setPrice(rs.getInt("price"));
				list.add(product);
			}

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return list;

	}
	// ----------------------------------selectAll------------------------------------

//	----------------------------------selectByProduct------------------------------------
	public Product selectByProduct(String uniformid) {

//		リターン用オブジェクト

		Product product = new Product();

		Connection con = null;
		Statement smt = null;
		try {
			con = getConnection();
			smt = con.createStatement();

			String sql = "SELECT * FROM product_info WHERE uniformid='" + uniformid + "'";
			ResultSet rs = smt.executeQuery(sql);

			if (rs.next()) {
				product.setUniformid(rs.getString("uniformid"));
				product.setUniform_name(rs.getString("uniform_name"));
				product.setStock_quantity(rs.getInt("stock_quantity"));
				product.setPrice(rs.getInt("price"));
			}

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return product;
	}

//	----------------------------------selectByProduct------------------------------------

	// ----------------------------------insert------------------------------------
	public void insert(Product product) {
		Connection con = null;
		Statement smt = null;
		try {
			con = ProductDao.getConnection();
			smt = con.createStatement();

			String sql = "INSERT INTO product_info(uniformid,uniform_name,stock_quantity,price) " + "VALUES('"
					+ product.getUniformid() + "','" + product.getUniform_name() + "'," + product.getStock_quantity()
					+ "," + product.getPrice() + ")";

			// 確認用＊最後削除＊
			System.out.println(sql);

			smt.executeUpdate(sql);

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}
	// ----------------------------------insert------------------------------------

	//----------------------------------stock_quantity------------------------------------
	public void updateStock_quantity(int orderedQuantity, String uniformid) {

		Connection con = null;
		Statement smt = null;

		String sql = "UPDATE product_info SET stock_quantity=" + orderedQuantity + " WHERE uniformid='" + uniformid + "'";

		try {
			con = ProductDao.getConnection();
			smt = con.createStatement();

			smt.executeUpdate(sql);


		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}

}
