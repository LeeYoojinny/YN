package vo;

import java.sql.Timestamp;

public class Order {
	private String order_num;
	private String car_id;
	private String user_id;
	private String coupon_id;
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
	private int payment;
	
	public Order() {}
		
	public Order(String order_num, String car_id, String user_id, String coupon_id, Timestamp order_date, 
			int car_price, int car_tax,int sale_expense, String region, int deliveryfee, int user_zipcode, 
			String user_address1, String user_address2,	String user_phone, int payment) {
		super();
		this.order_num = order_num;
		this.car_id = car_id;
		this.user_id = user_id;
		this.coupon_id = coupon_id;
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
		this.payment = payment;
	}

	public String getOrder_num() {
		return order_num;
	}

	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}

	public String getCar_id() {
		return car_id;
	}

	public void setCar_id(String car_id) {
		this.car_id = car_id;
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
	
	
	
}
