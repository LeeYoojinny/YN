package vo;

import java.sql.Timestamp;

public class ReviewBoard {
	private String review_num;
	private String car_id;
	private String user_id;
	private String ordernum;
	private String review_title;
	private String review_content;
	private String review_file1;
	private String review_file1_origin;
	private String review_file2;
	private String review_file2_origin;
	private String review_file3;
	private String review_file3_origin;
	private Timestamp review_date;
	private int review_hit;
	
	public ReviewBoard() {}
	
	public ReviewBoard(String review_num, String car_id, String user_id, String ordernum, String review_title,
			String review_content, String review_file1, String review_file1_origin, String review_file2,
			String review_file2_origin, String review_file3, String review_file3_origin, Timestamp review_date,
			int review_hit) {
		super();
		this.review_num = review_num;
		this.car_id = car_id;
		this.user_id = user_id;
		this.ordernum = ordernum;
		this.review_title = review_title;
		this.review_content = review_content;
		this.review_file1 = review_file1;
		this.review_file1_origin = review_file1_origin;
		this.review_file2 = review_file2;
		this.review_file2_origin = review_file2_origin;
		this.review_file3 = review_file3;
		this.review_file3_origin = review_file3_origin;
		this.review_date = review_date;
		this.review_hit = review_hit;
	}

	public String getReview_num() {
		return review_num;
	}

	public void setReview_num(String review_num) {
		this.review_num = review_num;
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

	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}

	public String getReview_title() {
		return review_title;
	}

	public void setReview_title(String review_title) {
		this.review_title = review_title;
	}

	public String getReview_content() {
		return review_content;
	}

	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}

	public String getReview_file1() {
		return review_file1;
	}

	public void setReview_file1(String review_file1) {
		this.review_file1 = review_file1;
	}

	public String getReview_file1_origin() {
		return review_file1_origin;
	}

	public void setReview_file1_origin(String review_file1_origin) {
		this.review_file1_origin = review_file1_origin;
	}

	public String getReview_file2() {
		return review_file2;
	}

	public void setReview_file2(String review_file2) {
		this.review_file2 = review_file2;
	}

	public String getReview_file2_origin() {
		return review_file2_origin;
	}

	public void setReview_file2_origin(String review_file2_origin) {
		this.review_file2_origin = review_file2_origin;
	}

	public String getReview_file3() {
		return review_file3;
	}

	public void setReview_file3(String review_file3) {
		this.review_file3 = review_file3;
	}

	public String getReview_file3_origin() {
		return review_file3_origin;
	}

	public void setReview_file3_origin(String review_file3_origin) {
		this.review_file3_origin = review_file3_origin;
	}

	public Timestamp getReview_date() {
		return review_date;
	}

	public void setReview_date(Timestamp review_date) {
		this.review_date = review_date;
	}

	public int getReview_hit() {
		return review_hit;
	}

	public void setReview_hit(int review_hit) {
		this.review_hit = review_hit;
	}
	
	
	
	
	
}
