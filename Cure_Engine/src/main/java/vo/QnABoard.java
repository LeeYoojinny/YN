package vo;

import java.sql.Timestamp;

public class QnABoard {
	private String qna_num;
	private int qna_viewNum;
	private int qna_replyNum;
	private String user_id;
	private String car_id;
	private String qna_pw;
	private String qna_title;	
	private String qna_content;
	private String qna_file;
	private String qna_file_origin;
	private Timestamp qna_date;
	private String secret_YN;
	private String reply_YN;
	private int qna_hit;
	
	public QnABoard() {}
	
	public QnABoard(String qna_num, String user_id, String car_id, String qna_pw, String qna_title, String qna_content,
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

	public QnABoard(String qna_num, int qna_viewNum, int qna_replyNum, String user_id, String car_id, String qna_pw,
			String qna_title, String qna_content, String qna_file, String qna_file_origin, Timestamp qna_date,
			String secret_YN, String reply_YN, int qna_hit) {
		super();
		this.qna_num = qna_num;
		this.qna_viewNum = qna_viewNum;
		this.qna_replyNum = qna_replyNum;
		this.user_id = user_id;
		this.car_id = car_id;
		this.qna_pw = qna_pw;
		this.qna_title = qna_title;
		this.qna_content = qna_content;
		this.qna_file = qna_file;
		this.qna_file_origin = qna_file_origin;
		this.qna_date = qna_date;
		this.secret_YN = secret_YN;
		this.reply_YN = reply_YN;
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

	public int getQna_viewNum() {
		return qna_viewNum;
	}

	public void setQna_viewNum(int qna_viewNum) {
		this.qna_viewNum = qna_viewNum;
	}

	public int getQna_replyNum() {
		return qna_replyNum;
	}

	public void setQna_replyNum(int qna_replyNum) {
		this.qna_replyNum = qna_replyNum;
	}

	public String getReply_YN() {
		return reply_YN;
	}

	public void setReply_YN(String reply_YN) {
		this.reply_YN = reply_YN;
	}
	
	
}
