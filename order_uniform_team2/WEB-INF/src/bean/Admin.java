package bean;

public class Admin {

	private String adminid;
	private String password;

	// 管理者IDの取得
	public String getAdminid() {
		return adminid;
	}

	// 管理者IDの設定
	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}

	// 管理者パスワードの取得
	public String getPassword() {
		return password;
	}

	// 管理者パスワードの設定
	public void setPassword(String passwaord) {
		this.password = passwaord;
	}
}