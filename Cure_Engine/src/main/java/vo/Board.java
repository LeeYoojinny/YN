package vo;

import java.sql.Timestamp;

public class Board {
	private String qna_num;
	private String user_id;
	private String car_id;
	private String qna_pw;
	private String qna_title;	
	private String qna_content;
	private String qna_file;
	private String qna_file_origin;
	private Timestamp qna_date;
	private String secret_YN;
	private int qna_hit;
	
	public Board() {}
	
	public Board(String qna_num, String user_id, String car_id, String qna_pw, String qna_title, String qna_content,
			String qna_file, String qna_file_origin, Timestamp qna_date, String secret_YN, int qna_hit) {
		super();
		this.qna_num = qna_num;
		this.user_id = user_id;
		this.car_id = car_id;
		this.qna_pw = qna_pw;
		this.qna_title = qna_title;
		this.qna_content = qna_content;
		this.qna_file = qna_file;
		this.qna_file_origin = qna_file_origin;
		this.qna_date = qna_date;
		this.secret_YN = secret_YN;
		this.qna_hit = qna_hit;
	}
	public String getQna_num() {
		return qna_num;
	}
	public void setQna_num(String qna_num) {
		this.qna_num = qna_num;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getCar_id() {
		return car_id;
	}
	public void setCar_id(String car_id) {
		this.car_id = car_id;
	}
	public String getQna_pw() {
		return qna_pw;
	}
	public void setQna_pw(String qna_pw) {
		this.qna_pw = qna_pw;
	}
	public String getQna_title() {
		return qna_title;
	}
	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}
	public String getQna_content() {
		return qna_content;
	}
	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}
	public String getQna_file() {
		return qna_file;
	}
	public void setQna_file(String qna_file) {
		this.qna_file = qna_file;
	}
	public String getQna_file_origin() {
		return qna_file_origin;
	}
	public void setQna_file_origin(String qna_file_origin) {
		this.qna_file_origin = qna_file_origin;
	}
	public Timestamp getQna_date() {
		return qna_date;
	}
	public void setQna_date(Timestamp qna_date) {
		this.qna_date = qna_date;
	}
	public String getSecret_YN() {
		return secret_YN;
	}
	public void setSecret_YN(String secret_YN) {
		this.secret_YN = secret_YN;
	}
	public int getQna_hit() {
		return qna_hit;
	}
	public void setQna_hit(int qna_hit) {
		this.qna_hit = qna_hit;
	}
	
	
}
