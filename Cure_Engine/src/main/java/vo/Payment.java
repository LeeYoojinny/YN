package vo;

public class Payment {
	private String order_num;
	private int pay_by;
	private int pay_price;
	private String pay_depositor_name;
	private String pay_creditcard_name;
	private String pay_creditcard_num;
	private String pay_creditcard_date;
	private int pay_creditcard_cvc;	
	
	public Payment() {}
	
	public Payment(String order_num, int pay_by, int pay_price, String pay_depositor_name, String pay_creditcard_name,
			String pay_creditcard_num, String pay_creditcard_date, int pay_creditcard_cvc) {
		super();
		this.order_num = order_num;
		this.pay_by = pay_by;
		this.pay_price = pay_price;
		this.pay_depositor_name = pay_depositor_name;
		this.pay_creditcard_name = pay_creditcard_name;
		this.pay_creditcard_num = pay_creditcard_num;
		this.pay_creditcard_date = pay_creditcard_date;
		this.pay_creditcard_cvc = pay_creditcard_cvc;
	}

	public String getOrder_num() {
		return order_num;
	}

	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}

	public int getPay_by() {
		return pay_by;
	}

	public void setPay_by(int pay_by) {
		this.pay_by = pay_by;
	}

	public int getPay_price() {
		return pay_price;
	}

	public void setPay_price(int pay_price) {
		this.pay_price = pay_price;
	}

	public String getPay_depositor_name() {
		return pay_depositor_name;
	}

	public void setPay_depositor_name(String pay_depositor_name) {
		this.pay_depositor_name = pay_depositor_name;
	}

	public String getPay_creditcard_name() {
		return pay_creditcard_name;
	}

	public void setPay_creditcard_name(String pay_creditcard_name) {
		this.pay_creditcard_name = pay_creditcard_name;
	}

	public String getPay_creditcard_num() {
		return pay_creditcard_num;
	}

	public void setPay_creditcard_num(String pay_creditcard_num) {
		this.pay_creditcard_num = pay_creditcard_num;
	}
	
	public String getPay_creditcard_date() {
		return pay_creditcard_date;
	}

	public void setPay_creditcard_date(String pay_creditcard_date) {
		this.pay_creditcard_date = pay_creditcard_date;
	}

	public int getPay_creditcard_cvc() {
		return pay_creditcard_cvc;
	}

	public void setPay_creditcard_cvc(int pay_creditcard_cvc) {
		this.pay_creditcard_cvc = pay_creditcard_cvc;
	}
	
	
}
