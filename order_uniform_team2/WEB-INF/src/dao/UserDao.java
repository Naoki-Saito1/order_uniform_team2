package dao;

import java.sql.*;
import java.util.ArrayList;
import bean.*;

public class UserDao {

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

	/*
	 * DBのuser_infoテーブルから指定ユーザーの条件に 合致する情報を取得するメソッド
	 */
	public User selectByUserid(int userid) {

		Connection con = null;
		Statement smt = null;

		// 検索した情報ユーザー情報を格納するUserブジェクトを生成
		User user = new User();

		// 引数の情報を利用し、検索用のSQL文を文字列として定義
		String sql = "SELECT * FROM user_info WHERE userid ='" + userid + "'";

		try {

			con = getConnection();
			smt = con.createStatement();

			// 結果セットから書籍データを取り出し、Userオブジェクトに格納
			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				user.setUserid(rs.getInt("userid"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPost_code(rs.getString("post_code"));
				user.setAddress(rs.getString("address"));
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
		return user;
	}

	/*
	 * DBから全ユーザー情報を取得するメソッド
	 */
	public ArrayList<User> selectAll() {

		Connection con = null;
		Statement smt = null;

		// 取得した全ユーザー情報を格納するArrayListを宣言
		ArrayList<User> user_list = new ArrayList<User>();

		// SQL文
		String sql = "SELECT * FROM user_info";

		try {

			con = getConnection();
			smt = con.createStatement();

			// DBへSQL文を発行し、全ユーザー情報を取得
			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				User user = new User();
				user.setUserid(rs.getInt("userid"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPost_code(rs.getString("post_code"));
				user.setAddress(rs.getString("address"));
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
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException ignore) {
			}
		}
		return user_list;
	}

	/*
	 * DBにユーザー情報を新規登録するメソッド
	 */
	public void insert(User user) {

		Connection con = null;
		Statement smt = null;

		// SQL文
		String sql = "INSERT INTO user_info VALUES('" + user.getUserid() + "','" + user.getName() + "','"
				+ user.getEmail() + "','" + user.getPost_code() + "','" + user.getAddress() + "')";

		try {

			con = getConnection();
			smt = con.createStatement();

			// DBへSQL文を発行し、ユーザー情報を新規登録
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

	public User selectByUser(User user) {

		Connection con = null;
		Statement smt = null;

		// 引数の情報を利用し、検索用のSQL文を文字列として定義
		String sql = "SELECT * FROM user_info WHERE name ='" + user.getName() + "' AND email='" + user.getEmail() + "'";

		try {

			con = getConnection();
			smt = con.createStatement();

			// 結果セットから書籍データを取り出し、Userオブジェクトに格納
			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				user.setUserid(rs.getInt("userid"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPost_code(rs.getString("post_code"));
				user.setAddress(rs.getString("address"));
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
		return user;
	}
}