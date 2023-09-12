package vo;

import java.sql.Timestamp;

public class NoticeBoard {
	private String notice_num;
	private String user_id;
	private String notice_title;
	private String notice_content;
	private String notice_file;
	private String notice_file_origin;
	private Timestamp notice_date;
	private int notice_hit;
	
	public NoticeBoard() {}
	
	public NoticeBoard(String notice_num, String user_id, String notice_title, String notice_content,
			String notice_file, String notice_file_origin, Timestamp notice_date, int notice_hit) {
		super();
		this.notice_num = notice_num;
		this.user_id = user_id;
		this.notice_title = notice_title;
		this.notice_content = notice_content;
		this.notice_file = notice_file;
		this.notice_file_origin = notice_file_origin;
		this.notice_date = notice_date;
		this.notice_hit = notice_hit;
	}

	public String getNotice_num() {
		return notice_num;
	}

	public void setNotice_num(String notice_num) {
		this.notice_num = notice_num;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getNotice_title() {
		return notice_title;
	}

	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}

	public String getNotice_content() {
		return notice_content;
	}

	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}

	public String getNotice_file() {
		return notice_file;
	}

	public void setNotice_file(String notice_file) {
		this.notice_file = notice_file;
	}

	public int getNotice_hit() {
		return notice_hit;
	}

	public void setNotice_hit(int notice_hit) {
		this.notice_hit = notice_hit;
	}

	public Timestamp getNotice_date() {
		return notice_date;
	}

	public void setNotice_date(Timestamp notice_date) {
		this.notice_date = notice_date;
	}

	public String getNotice_file_origin() {
		return notice_file_origin;
	}

	public void setNotice_file_origin(String notice_file_origin) {
		this.notice_file_origin = notice_file_origin;
	}
	
	
}
