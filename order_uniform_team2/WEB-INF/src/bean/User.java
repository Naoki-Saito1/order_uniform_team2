package bean;

public class User {

	// ユーザーID
	private int userid;

	// ユーザーネーム
	private String name;

	// ユーザーメールアドレス
	private String email;

	// 郵便番号
	private String post_code;

	// 住所
	private String address;

	// 初期設定
	public User() {
		this.userid = 0;
		this.name = null;
		this.email = null;
		this.post_code = null;
		this.address = null;
	}

	// ユーザーIDを取得する
	public int getUserid() {
		return userid;
	}

	// ユーザーIDを設定する
	public void setUserid(int userid) {
		this.userid = userid;
	}

	// ユーザーネームを取得する
	public String getName() {
		return name;
	}

	// ユーザーネームを設定する
	public void setName(String name) {
		this.name = name;
	}

	// メールアドレスを取得する
	public String getEmail() {
		return email;
	}

	// メールアドレスを設定する
	public void setEmail(String email) {
		this.email = email;
	}

	// 郵便番号取得する
	public String getPost_code() {
		return post_code;
	}

	// 郵便番号を設定する
	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}

	// 住所を取得する
	public String getAddress() {
		return address;
	}

	// 住所を設定する
	public void setAddress(String address) {
		this.address = address;
	}

}
