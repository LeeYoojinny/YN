package vo;

import java.sql.Timestamp;

public class Coupon {
	private String coupon_id;
	private String user_id;
	private int discount_rate;
	private Timestamp coupon_expiredate;
	
	public Coupon() {}
	
	public Coupon(String coupon_id, String user_id, int discount_rate, Timestamp coupon_expiredate) {
		super();
		this.coupon_id = coupon_id;
		this.user_id = user_id;
		this.discount_rate = discount_rate;
		this.coupon_expiredate = coupon_expiredate;
	}

	public String getCoupon_id() {
		return coupon_id;
	}

	public void setCoupon_id(String coupon_id) {
		this.coupon_id = coupon_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getDiscount_rate() {
		return discount_rate;
	}

	public void setDiscount_rate(int discount_rate) {
		this.discount_rate = discount_rate;
	}

	public Timestamp getCoupon_expiredate() {
		return coupon_expiredate;
	}

	public void setCoupon_expiredate(Timestamp coupon_expiredate) {
		this.coupon_expiredate = coupon_expiredate;
	}
	
	
	
	
}
