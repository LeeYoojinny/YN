package vo;

public class Wishlist {
	private String car_id;
	private String car_image;
	private int car_price;
	private String user_id;
	
	public Wishlist() {}
	
	public Wishlist(String car_id, String car_image, int car_price, String user_id) {
		super();
		this.car_id = car_id;
		this.car_image = car_image;
		this.car_price = car_price;
		this.user_id = user_id;
	}

	public String getCar_id() {
		return car_id;
	}

	public void setCar_id(String car_id) {
		this.car_id = car_id;
	}

	public String getCar_image() {
		return car_image;
	}

	public void setCar_image(String car_image) {
		this.car_image = car_image;
	}

	public int getCar_price() {
		return car_price;
	}

	public void setCar_price(int car_price) {
		this.car_price = car_price;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
	
}
