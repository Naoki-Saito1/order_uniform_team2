/*
 * お蔵入りにはしない！
 * 作成者：武田
 * 作成日：6/22～
 * 内容：一般ユーザーの購入の際に使用するためのサーブレット
 *
 */
package bean;

public class OrderedItem {
	private String uniformId; // uniformid (product_info)
	private String uniformName; // uniform_name(product_info)
	private String userName; // user_name(user_info)
	private String email; // email(user_info)
	private String postCode; // post_code(user_info)
	private String address; // adress(user_info)
	private int quantity; // quantity(order_info)
	private String sum; // sum(order_info)
	private String memo; // memo(user_info)

	// コンストラクタ
	public OrderedItem() {
		this.uniformId = null;
		this.uniformName = null;
		this.userName = null;
		this.email = null;
		this.postCode = null;
		this.address = null;
		this.quantity = 0;
		this.sum = null;
		this.memo = null;

	}

	public String getUniformName() {
		return uniformName;
	}

	public void setUniformName(String uniformName) {
		this.uniformName = uniformName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getUniformId() {
		return uniformId;
	}

	public void setUniformId(String uniformId) {
		this.uniformId = uniformId;
	}

}
