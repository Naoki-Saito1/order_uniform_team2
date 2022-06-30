package dao;

import java.util.*;
import java.util.Date;

import bean.Order;
import bean.Product;

import java.sql.*;
import java.text.SimpleDateFormat;

public class OrderDao {
	// データベース接続情報
	private static String RDB_DRIVE = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost/uniformdb";
	private static String USER = "root";
	private static String PASS = "root123";

	// データベースに接続するためのメソッド
	private static Connection getConnection() {
		try {
			Class.forName(RDB_DRIVE);
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			return con;

		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	// showOrderList.jspの購入日の覧で使用するメソッド
	public String showDate(Order order) { // while文の中で使ってね たけだより
		String date = null;

		Connection con = null;
		Statement smt = null;
		try {

			con = getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery("SELECT * FROM order_info WHERE =" + order.getOrder_no());
			Date datetime = rs.getTimestamp("purchase_date");

			// フォーマットの表示
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");

			date = sdf.format(datetime);

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
		return date;
	}

	/**
	 * showOrderList.jspへ値を表示させるメソッド
	 *
	 * @return list：ユーザーからの受注のデータをOrder型で返却
	 */
	public ArrayList<Order> selectAll() {
		ArrayList<Order> list = new ArrayList<Order>();
		String sql = "SELECT * FROM order_info ORDER BY puchase_date desc"; // 購入日に昇順

		Connection con = null;
		Statement smt = null;
		try {
			con = getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				Order order = new Order();
				order.setOrder_no(rs.getInt("order_no"));
				order.setUserid(rs.getInt("userid"));
				order.setUniformid(rs.getString("uniformid"));
				order.setQuantity(rs.getInt("quantity"));
				order.setSum(rs.getString("sum"));
				order.setPurchase_date(rs.getTimestamp("puchase_date"));
				order.setPayment_status(rs.getString("payment_status"));
				order.setDelivery_status(rs.getString("delivery_status"));
				order.setMemo(rs.getString("memo"));
				list.add(order);

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

	/**
	 * order.jspで入力された値を入力するメソッド
	 *
	 */
	public int insert(Order order, Product product) {
		Connection con = null;
		Statement smt = null;
		int sum = 0;

		try {

			// 合計値
			sum = order.getQuantity() * product.getPrice();
			order.setSum(String.valueOf(sum));

			// 合計値をフォーマットで，と円をつけて収納したい たけだ ！明日提言してみる！

			// sql
			String sql = "INSERT INTO order_info VALUES(" + 0 + "," + order.getUserid() + ",'" + order.getUniformid()
					+ "'," + order.getQuantity() + ",'" + order.getSum() + "', now(),'" + order.getPayment_status()
					+ "','" + order.getDelivery_status() + "','" + order.getMemo() + "')";

			con = getConnection();
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
		return sum;

	}

	/**
	 * 入金状況を変更するメソッド
	 *
	 * @return count：戻り値の有無によってエラーを判断するための変数
	 * @param payment_status：配送状況のボタンを押下時にステータスを変更するための変数
	 *
	 */
	public void updatePayment(String payment_status, int order_no) {

		Connection con = null;
		Statement smt = null;

		// SQL文
		String sql = "UPDATE order_info SET payment_status='" + payment_status + "' WHERE order_no=" + order_no;
		try {
			con = getConnection();
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

	/**
	 * 入金状況を変更するメソッド
	 *
	 * @return count：戻り値の有無によってエラーを判断するための変数
	 * @param delivery_status：配送状況のボタンを押下時にステータスを変更するための変数
	 *
	 */
	public void updateDelivery(String delivery_status, int order_no) {

		Connection con = null;
		Statement smt = null;

		// SQL文
		String sql = "UPDATE order_info SET delivery_status='" + delivery_status + "' WHERE order_no=" + order_no;
		try {
			con = getConnection();
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

	/**
	 * 0623追加/オーダー情報１件取得メソッド
	 *
	 *
	 */

	public Order selectByOrder(int order_no) {

		Order order = new Order();

		Connection con = null;
		Statement smt = null;
		try {
			con = getConnection();
			smt = con.createStatement();

			String sql = "SELECT * FROM order_info WHERE order_no=" + order_no + "";
			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				order.setOrder_no(rs.getInt("order_no"));
				order.setUserid(rs.getInt("userid"));
				order.setUniformid(rs.getString("uniformid"));
				order.setQuantity(rs.getInt("quantity"));
				order.setSum(rs.getString("sum"));
				order.setPurchase_date(rs.getTimestamp("puchase_date"));
				order.setPayment_status(rs.getString("payment_status"));
				order.setDelivery_status(rs.getString("delivery_status"));
				order.setMemo(rs.getString("memo"));
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
		return order;
	}

}