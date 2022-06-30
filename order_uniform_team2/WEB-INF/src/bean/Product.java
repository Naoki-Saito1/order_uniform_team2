package bean;

public class Product {
	private String uniformid;
	private String uniform_name;
	private int stock_quantity;
	private int price;

	public Product() {
		this.uniformid = "";
		this.uniform_name = "";
		this.stock_quantity = 0;
		this.price = 0;
	}

	public Product(String uniformid, String uniform_name, int stock_quantity, int price) {
		this.uniformid = uniformid;
		this.uniform_name = uniform_name;
		this.stock_quantity = stock_quantity;
		this.price = price;

	}

	// ユニフォームIDの取得
	public String getUniformid() {
		return uniformid;
	}

	// ユニフォームIDの設定
	public void setUniformid(String uniformid) {
		this.uniformid = uniformid;
	}

	// ユニフォーム名の取得
	public String getUniform_name() {
		return uniform_name;
	}

	// ユニフォーム名の設定
	public void setUniform_name(String uniform_name) {
		this.uniform_name = uniform_name;
	}

	// ユニフォーム在庫数の取得
	public int getStock_quantity() {
		return stock_quantity;
	}

	// ユニフォーム在庫数の設定
	public void setStock_quantity(int stock_quantity) {
		this.stock_quantity = stock_quantity;
	}

	// ユニフォームの値段の取得
	public int getPrice() {
		return price;
	}

	// ユニフォームの値段の設定
	public void setPrice(int price) {
		this.price = price;
	}

}
