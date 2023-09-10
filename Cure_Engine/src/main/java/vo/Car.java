package vo;

public class Car {
	private String dealer_id;
	private String car_id;
	private String car_brand;
	private String car_name;
	private String car_color;
	private int car_price;
	private int car_capacity;
	private String car_fuel;
	private String car_transmission;
	private String car_type;
	private String car_accident;
	private int car_year;
	private int car_distance;
	private String car_image1;
	private String car_image2;
	private String car_image3;
	private String car_image4;
	private String car_image5;
	private String car_content;
	private String car_accident_detail;
	private int car_like;
	private String sale_YN;
	private String car_delete;
	
	public Car() {}
	
	public Car(String dealer_id, String car_id, String car_brand, String car_name, String car_color, int car_price,
			int car_capacity, String car_fuel, String car_transmission, String car_type, String car_accident,
			int car_year, int car_distance, String car_image1, String car_image2, String car_image3, String car_image4,
			String car_image5,String car_content,
			String car_accident_detail) {
		super();
		this.dealer_id = dealer_id;
		this.car_id = car_id;
		this.car_brand = car_brand;
		this.car_name = car_name;
		this.car_color = car_color;
		this.car_price = car_price;
		this.car_capacity = car_capacity;
		this.car_fuel = car_fuel;
		this.car_transmission = car_transmission;
		this.car_type = car_type;
		this.car_accident = car_accident;
		this.car_year = car_year;
		this.car_distance = car_distance;
		this.car_image1 = car_image1;
		this.car_image2 = car_image2;
		this.car_image3 = car_image3;
		this.car_image4 = car_image4;
		this.car_image5 = car_image5;
		this.car_content = car_content;
		this.car_accident_detail = car_accident_detail;
	}
	
	
	//상세보기용
	public Car(String dealer_id, String car_id, String car_brand, String car_name, String car_color, int car_price,
			int car_capacity, String car_fuel, String car_transmission, String car_type, String car_accident,
			int car_year, int car_distance, String car_image1, String car_image2, String car_image3, String car_image4,
			String car_image5, String car_content, String car_accident_detail, int car_like, String sale_YN) {
		super();
		this.dealer_id = dealer_id;
		this.car_id = car_id;
		this.car_brand = car_brand;
		this.car_name = car_name;
		this.car_color = car_color;
		this.car_price = car_price;
		this.car_capacity = car_capacity;
		this.car_fuel = car_fuel;
		this.car_transmission = car_transmission;
		this.car_type = car_type;
		this.car_accident = car_accident;
		this.car_year = car_year;
		this.car_distance = car_distance;
		this.car_image1 = car_image1;
		this.car_image2 = car_image2;
		this.car_image3 = car_image3;
		this.car_image4 = car_image4;
		this.car_image5 = car_image5;
		this.car_content = car_content;
		this.car_accident_detail = car_accident_detail;
		this.car_like = car_like;
		this.sale_YN = sale_YN;
	}

	//'등록된 차량 뷰'용 생성자
	public Car(String dealer_id, String car_id, String car_brand, String car_name, int car_price, int car_year,
			String car_image1, int car_like, String sale_YN, String car_delete) {
		super();
		this.dealer_id = dealer_id;
		this.car_id = car_id;
		this.car_brand = car_brand;
		this.car_name = car_name;
		this.car_price = car_price;
		this.car_year = car_year;
		this.car_image1 = car_image1;
		this.car_like = car_like;
		this.sale_YN = sale_YN;
		this.car_delete = car_delete;
		
	}

	
	
	public String getDealer_id() {
		return dealer_id;
	}

	public void setDealer_id(String dealer_id) {
		this.dealer_id = dealer_id;
	}

	public String getCar_id() {
		return car_id;
	}

	public void setCar_id(String car_id) {
		this.car_id = car_id;
	}

	public String getCar_brand() {
		return car_brand;
	}

	public void setCar_brand(String car_brand) {
		this.car_brand = car_brand;
	}

	public String getCar_name() {
		return car_name;
	}

	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}

	public String getCar_color() {
		return car_color;
	}

	public void setCar_color(String car_color) {
		this.car_color = car_color;
	}

	public int getCar_price() {
		return car_price;
	}

	public void setCar_price(int car_price) {
		this.car_price = car_price;
	}

	public int getCar_capacity() {
		return car_capacity;
	}

	public void setCar_capacity(int car_capacity) {
		this.car_capacity = car_capacity;
	}

	public String getCar_fuel() {
		return car_fuel;
	}

	public void setCar_fuel(String car_fuel) {
		this.car_fuel = car_fuel;
	}

	public String getCar_transmission() {
		return car_transmission;
	}

	public void setCar_transmission(String car_transmission) {
		this.car_transmission = car_transmission;
	}

	public String getCar_type() {
		return car_type;
	}

	public void setCar_type(String car_type) {
		this.car_type = car_type;
	}

	public String getCar_accident() {
		return car_accident;
	}

	public void setCar_accident(String car_accident) {
		this.car_accident = car_accident;
	}

	public int getCar_year() {
		return car_year;
	}

	public void setCar_year(int car_year) {
		this.car_year = car_year;
	}

	public int getCar_distance() {
		return car_distance;
	}

	public void setCar_distance(int car_distance) {
		this.car_distance = car_distance;
	}

	public String getCar_image1() {
		return car_image1;
	}

	public void setCar_image1(String car_image1) {
		this.car_image1 = car_image1;
	}

	public String getCar_image2() {
		return car_image2;
	}

	public void setCar_image2(String car_image2) {
		this.car_image2 = car_image2;
	}
	
	public String getCar_image3() {
		return car_image3;
	}
	
	public void setCar_image3(String car_image3) {
		this.car_image3 = car_image3;
	}
	
	public String getCar_image4() {
		return car_image4;
	}
	
	public void setCar_image4(String car_image4) {
		this.car_image4 = car_image4;
	}
	
	public String getCar_image5() {
		return car_image5;
	}
	
	public void setCar_image5(String car_image5) {
		this.car_image5 = car_image5;
	}

	public String getCar_content() {
		return car_content;
	}

	public void setCar_content(String car_content) {
		this.car_content = car_content;
	}

	public String getCar_accident_detail() {
		return car_accident_detail;
	}

	public void setCar_accident_detail(String car_accident_detail) {
		this.car_accident_detail = car_accident_detail;
	}

	public int getCar_like() {
		return car_like;
	}

	public void setCar_like(int car_like) {
		this.car_like = car_like;
	}

	public String getSale_YN() {
		return sale_YN;
	}

	public void setSale_YN(String sale_YN) {
		this.sale_YN = sale_YN;
	}

	public String getCar_delete() {
		return car_delete;
	}

	public void setCar_delete(String car_delete) {
		this.car_delete = car_delete;
	}
	
	
	
	
}
