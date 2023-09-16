package vo;

public class Deliveryfee {
	private String region;
	private int fee;
	
	public Deliveryfee() {}
	
	public Deliveryfee(String region, int fee) {
		super();
		this.region = region;
		this.fee = fee;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}
	
	
	
	
}
