package vo;

public class Code {
	private String code_category;
	private String code_name;
	private String code_value;
	
	public Code() {}
	
	public Code(String code_category, String code_name, String code_value) {
		super();
		this.code_category = code_category;
		this.code_name = code_name;
		this.code_value = code_value;
	}

	public String getCode_category() {
		return code_category;
	}

	public void setCode_category(String code_category) {
		this.code_category = code_category;
	}

	public String getCode_name() {
		return code_name;
	}

	public void setCode_name(String code_name) {
		this.code_name = code_name;
	}

	public String getCode_value() {
		return code_value;
	}

	public void setCode_value(String code_value) {
		this.code_value = code_value;
	}
	
	
	
}
