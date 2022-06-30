package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.*;

public class AdminDao {

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

	public Admin selectByAdmin(String adminid) {

		// DBに接続
		Connection con = null;
		Statement smt = null;

		// Userクラスのオブジェクト生成
		Admin admin = new Admin();

		// SQL文を作成
		String sql = "SELECT * FROM admin_info WHERE adminid ='" + adminid + "'";

		try {
			// DBに接続
			con = AdminDao.getConnection();
			smt = con.createStatement();

			// SQL文を発行
			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				admin.setAdminid(rs.getString("adminid"));
				admin.setPassword(rs.getString("password"));

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
		return admin;
	}

}
