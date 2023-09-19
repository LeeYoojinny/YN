package vo;

import java.sql.Timestamp;

public class Order {
	private String ordernum;
	private String car_id;
	private String dealer_id;	
	private String user_id;
	private String user_name;
	private String coupon_id;
	private int discount_price;
	private Timestamp order_date;
	private int car_price;
	private int car_tax;
	private int sale_expense;
	private String region;
	private int deliveryfee;
	private int user_zipcode;
	private String user_address1;
	private String user_address2;
	private String user_phone;
	private String user_email;
	private int payment;
	private String order_approve_YN;
	
	public Order() {}
		
	public Order(String ordernum, String car_id, String dealer_id,String user_id, String user_name, String coupon_id, int discount_price, 
			Timestamp order_date, int car_price, int car_tax,int sale_expense, String region, int deliveryfee, int user_zipcode, 
			String user_address1, String user_address2,	String user_phone, String user_email, int payment,String order_approve_YN) {
		super();
		this.ordernum = ordernum;
		this.car_id = car_id;
		this.dealer_id = dealer_id;
		this.user_id = user_id;
		this.user_name = user_name;
		this.coupon_id = coupon_id;
		this.discount_price = discount_price;
		this.order_date = order_date;
		this.car_price = car_price;
		this.car_tax = car_tax;
		this.sale_expense = sale_expense;
		this.region = region;
		this.deliveryfee = deliveryfee;
		this.user_zipcode = user_zipcode;
		this.user_address1 = user_address1;
		this.user_address2 = user_address2;
		this.user_phone = user_phone;
		this.user_email = user_email;
		this.payment = payment;
		this.order_approve_YN = order_approve_YN;
	}

	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}

	public String getCar_id() {
		return car_id;
	}

	public void setCar_id(String car_id) {
		this.car_id = car_id;
	}	
	
	public String getDealer_id() {
		return dealer_id;
	}

	public void setDealer_id(String dealer_id) {
		this.dealer_id = dealer_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getCoupon_id() {
		return coupon_id;
	}

	public void setCoupon_id(String coupon_id) {
		this.coupon_id = coupon_id;
	}

	public int getDiscount_price() {
		return discount_price;
	}

	public void setDiscount_price(int discount_price) {
		this.discount_price = discount_price;
	}

	public Timestamp getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Timestamp order_date) {
		this.order_date = order_date;
	}

	public int getCar_price() {
		return car_price;
	}

	public void setCar_price(int car_price) {
		this.car_price = car_price;
	}	
	
	public int getCar_tax() {
		return car_tax;
	}

	public void setCar_tax(int car_tax) {
		this.car_tax = car_tax;
	}

	public int getSale_expense() {
		return sale_expense;
	}

	public void setSale_expense(int sale_expense) {
		this.sale_expense = sale_expense;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getDeliveryfee() {
		return deliveryfee;
	}

	public void setDeliveryfee(int deliveryfee) {
		this.deliveryfee = deliveryfee;
	}

	public int getUser_zipcode() {
		return user_zipcode;
	}

	public void setUser_zipcode(int user_zipcode) {
		this.user_zipcode = user_zipcode;
	}

	public String getUser_address1() {
		return user_address1;
	}

	public void setUser_address1(String user_address1) {
		this.user_address1 = user_address1;
	}

	public String getUser_address2() {
		return user_address2;
	}

	public void setUser_address2(String user_address2) {
		this.user_address2 = user_address2;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public String getOrder_approve_YN() {
		return order_approve_YN;
	}

	public void setOrder_approve_YN(String order_approve_YN) {
		this.order_approve_YN = order_approve_YN;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	
	
	
}
