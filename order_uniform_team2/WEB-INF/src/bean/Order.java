package bean;

import java.sql.Timestamp;

public class Order {

	private int order_no;
	private int userid;
	private String uniformid;
	private int quantity;
	private String sum;
	private Timestamp purchase_date;
	private String payment_status;
	private String delivery_status;
	private String memo;

	// 初期設定
	public Order() {
		this.order_no = 0;
		this.userid = 0;
		this.uniformid = null;
		this.quantity = 0;
		this.sum = null;
		this.payment_status = null;
		this.delivery_status = null;
		this.memo = null;
	}

	// オーダーナンバーの取得
	public int getOrder_no() {
		return order_no;
	}

	// オーダーナンバーの設定
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}

	// ユーザーIDの取得
	public int getUserid() {
		return userid;
	}

	// ユーザーIDの設定
	public void setUserid(int userid) {
		this.userid = userid;
	}

	// ユニフォームIDの取得
	public String getUniformid() {
		return uniformid;
	}

	// ユニフォームIDの設定
	public void setUniformid(String uniformid) {
		this.uniformid = uniformid;
	}

	// ユニフォーム個数の取得
	public int getQuantity() {
		return quantity;
	}

	// ユニフォーム個数の設定
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	// ユニフォームの合計の取得
	public String getSum() {
		return sum;
	}

	// ユニフォームの合計の設定
	public void setSum(String sum) {
		this.sum = sum;
	}

	// 入金状況の取得
	public String getPayment_status() {
		return payment_status;
	}

	// 入金状況の設定
	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	// 購入状況の取得
	public String getDelivery_status() {
		return delivery_status;
	}

	// 購入状況の設定
	public void setDelivery_status(String delivery_status) {
		this.delivery_status = delivery_status;
	}

	// 新規追加0623/購入時刻の設定
	public void setPurchase_date(Timestamp purchase_date) {
		this.purchase_date = purchase_date;
	}

	// 新規追加0623/購入時刻の取得
	public Timestamp getPurchase_date() {
		return purchase_date;
	}

	// オーダーの備考を設定
	public void setMemo(String memo) {
		this.memo = memo;
	}

	// オーダーの備考を取得
	public String getMemo() {
		return memo;
	}

}
