/*
 * 作成者：武田
 * 作成日：2022/6/22
 *
 * order_infoテーブルへ更新・検索のクエリを実行するDAOクラス
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.OrderedItem;
import bean.User;

public class OrderedItemDao {
	// DB情報をフィールド変数に定義
	private static String RDB_DRIVE = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost/uniformdb";
	private static String USER = "root";
	private static String PASSWD = "root123";

	/*
	 * DBへの接続を行うメソッド
	 */
	public static Connection getConnection() {
		try {
			Class.forName(RDB_DRIVE);
			Connection con = DriverManager.getConnection(URL, USER, PASSWD);
			return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * データベースにカートの中身のまま登録するメソッド
	 *
	 * @uniformid 商品選択の時点でOrderedItemのArrayListに登録しておく
	 *
	 */
	public void insert(OrderedItem orderedItem, int userid) {
		Connection con = null;
		Statement smt = null;

		// SQL文
		String sql = "INSERT INTO order_info(quantity,sum,userid,uniformid,puchase_date,payment_status,delivery_status,memo)"
				+ " VALUES( " + orderedItem.getQuantity() + ",'" + orderedItem.getSum() + "'," + userid + ",'"
				+ orderedItem.getUniformId() + "',now(),'0','0','" + orderedItem.getMemo() + "')";

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
	 * userの情報が以前登録されたデータを同じかどうか検索する メソッド
	 *
	 */
	public int searchUserid(OrderedItem orderedItem) {
		Connection con = null;
		Statement smt = null;
		int userid = 0;

		try {
			String sql = "SELECT * From user_info WHERE name = '" + orderedItem.getUserName() + "' AND email = '"
					+ orderedItem.getEmail() + "'";
			con = getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			if (rs.next()) {
				User user = new User();
				userid = user.getUserid();
			} else {

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
		return userid;
	}

}
