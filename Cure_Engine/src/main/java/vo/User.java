package vo;

import util.SHA256;

public class User {
	private String user_category;
	private String user_id;
	private String user_pw;
	private String user_name;
	private String user_birth;
	private String user_gender;
	private String user_phone;
	private String user_email;
	private int user_zipcode;
	private String user_address1;
	private String user_address2;
	
	//회원가입에 없는것
	private String user_joindate;
	private String use_YN;
	private String user_expiredate;
	
	public User() {}
	
	public User(String user_category, String user_id, String user_pw, String user_name, String user_birth,
			String user_gender, String user_phone, String user_email, int user_zipcode, String user_address1,
			String user_address2) {
		super();
		this.user_category = user_category;
		this.user_id = user_id;
		this.user_pw = SHA256.encodeSHA256(user_pw);
		this.user_name = user_name;
		this.user_birth = user_birth;
		this.user_gender = user_gender;
		this.user_phone = user_phone;
		this.user_email = user_email;
		this.user_zipcode = user_zipcode;
		this.user_address1 = user_address1;
		this.user_address2 = user_address2;
	}

	public User(String user_id, String user_name, String user_birth, String user_gender, String user_phone,
			String user_email, int user_zipcode, String user_address1, String user_address2) {
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_birth = user_birth;
		this.user_gender = user_gender;
		this.user_phone = user_phone;
		this.user_email = user_email;
		this.user_zipcode = user_zipcode;
		this.user_address1 = user_address1;
		this.user_address2 = user_address2;		
	}

	public String getUser_category() {
		return user_category;
	}

	public void setUser_category(String user_category) {
		this.user_category = user_category;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_birth() {
		return user_birth;
	}

	public void setUser_birth(String user_birth) {
		this.user_birth = user_birth;
	}

	public String getUser_gender() {
		return user_gender;
	}

	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
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

	public String getUser_joindate() {
		return user_joindate;
	}

	public void setUser_joindate(String user_joindate) {
		this.user_joindate = user_joindate;
	}

	public String getUse_YN() {
		return use_YN;
	}

	public void setUse_YN(String use_YN) {
		this.use_YN = use_YN;
	}

	public String getUser_expiredate() {
		return user_expiredate;
	}

	public void setUser_expiredate(String user_expiredate) {
		this.user_expiredate = user_expiredate;
	}
	
	
	
	
}
