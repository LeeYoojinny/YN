package vo;

import java.util.Date;

public class Reservation {
	private String resernum;
	private String car_id;
	private String user_id;
	private String dealer_id;
	private Date rev_date;
	private String rev_time;
	private String approve_YN;
	
	public Reservation() {}
	
	public Reservation(String resernum, String car_id, String user_id, String dealer_id, Date rev_date, String rev_time,
			String approve_YN) {
		super();
		this.resernum = resernum;
		this.car_id = car_id;
		this.user_id = user_id;
		this.dealer_id = dealer_id;
		this.rev_date = rev_date;
		this.rev_time = rev_time;
		this.approve_YN = approve_YN;
	}
	public String getResernum() {
		return resernum;
	}
	public void setResernum(String resernum) {
		this.resernum = resernum;
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
	public String getDealer_id() {
		return dealer_id;
	}
	public void setDealer_id(String dealer_id) {
		this.dealer_id = dealer_id;
	}
	public Date getRev_date() {
		return rev_date;
	}
	public void setRev_date(Date rev_date) {
		this.rev_date = rev_date;
	}
	public String getRev_time() {
		return rev_time;
	}
	public void setRev_time(String rev_time) {
		this.rev_time = rev_time;
	}
	public String getApprove_YN() {
		return approve_YN;
	}
	public void setApprove_YN(String approve_YN) {
		this.approve_YN = approve_YN;
	}
	
}
