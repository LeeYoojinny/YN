package dao;

import static db.JdbcUtil.close;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Car;
import vo.Order;
import vo.Payment;

public class OrderDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private OrderDAO() {}
	
	private static OrderDAO orderDAO;
	
	public static OrderDAO getInstance() {
		if(orderDAO == null) {
			orderDAO = new OrderDAO();
		}
		return orderDAO;
	}
	
	public void setConnection (Connection con){
		this.con = con;
	}
	
	/*---- 주문하기 ----------------------------------------------------------------------------------------------*/
	public int insertOrder(Order order) {
		int result = 0;
		
		String sql = "insert into tbl_order(car_id,dealer_id,user_id,user_name,coupon_id,discount_price,car_price,"
				+ "car_tax,region,deliveryfee,user_zipcode,user_address1,user_address2,user_phone,user_email,payment) "
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, order.getCar_id());
			pstmt.setString(2, order.getDealer_id());
			pstmt.setString(3, order.getUser_id());
			pstmt.setString(4, order.getUser_name());
			pstmt.setString(5, order.getCoupon_id());
			pstmt.setInt(6, order.getDiscount_price());
			pstmt.setInt(7, order.getCar_price());
			pstmt.setInt(8, order.getCar_tax());
			pstmt.setString(9, order.getRegion());
			pstmt.setInt(10, order.getDeliveryfee());
			pstmt.setInt(11, order.getUser_zipcode());
			pstmt.setString(12, order.getUser_address1());
			pstmt.setString(13, order.getUser_address2());
			pstmt.setString(14, order.getUser_phone());
			pstmt.setString(15, order.getUser_email());
			pstmt.setInt(16, order.getPayment());
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("OrderDAO 클래스의 insertOrder()에서 발생한 에러 : "+e);
		}finally {
			close(pstmt);
		}
		return result;
	}

	/*---- payment 테이블 insert 위해 orderNum 가져오기 ---------------------------------------------------------------------*/
	public String getOrderNum(String car_id) {
		String orderNum = "";
		
		String sql = "select ordernum from tbl_order where car_id=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, car_id);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				orderNum = rs.getString(1);
			}
			
		}catch(Exception e) {
			System.out.println("UserDAO 클래스의 getOrderNum()에서 발생한 에러 : "+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return orderNum;
	}

	/*---- 주문 시 payment 테이블 insert  ---------------------------------------------------------------------*/
	public int insertPay(Payment payment, String orderNum) {
		int result = 0;
		
		String sql = "insert into tbl_payment values(?,?,?,?,?,?,?,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, orderNum);
			pstmt.setInt(2, payment.getPay_by());
			pstmt.setInt(3, payment.getPay_price());
			pstmt.setString(4, payment.getPay_depositor_name());
			pstmt.setString(5, payment.getPay_creditcard_name());
			pstmt.setString(6, payment.getPay_creditcard_num());
			pstmt.setInt(7, payment.getPay_creditcard_cvc());
			pstmt.setString(8, payment.getPay_creditcard_date());
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("OrderDAO 클래스의 insertPay()에서 발생한 에러 : "+e);
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	/*---- 주문 시 사용한 쿠폰 사용완료 상태로 만들기  ---------------------------------------------------------------------*/
	public int couponChangeY(String coupon_id) {
		int result = 0;
		
		String sql = "update tbl_coupon set coupon_use='Y' where coupon_id=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,coupon_id);
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("OrderDAO 클래스의 couponChangeY()에서 발생한 에러 : "+e);
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	/*---- 딜러용 주문현황 리스트 가져오기 - 개수  ---------------------------------------------------------------------*/
	public int getmyOrderCount(String dealer_id) {
		int result = 0;
		
		String sql = "SELECT count(*)"
				+ " FROM tbl_car c"
				+ " INNER JOIN tbl_order o ON c.car_id = o.car_id"
				+ " LEFT JOIN tbl_payment p ON o.ordernum = p.ordernum"
				+ " WHERE c.dealer_id =?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dealer_id);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		}catch(Exception e) {
			System.out.println("OrderDAO 클래스의 getmyOrderCount()에서 발생한 에러 : "+e);
		}finally {
			close(rs);
			close(pstmt);
		}		
		return result;
	}
	
	/*---- 딜러용 주문현황 리스트 가져오기 - Car객체  ---------------------------------------------------------------------*/
	public ArrayList<Car> getOrderCarList(String dealer_id, int page, int limit) {
		ArrayList<Car> carList = null;
		
		String sql = "SELECT c.*, o.*, p.*"
				+ " FROM tbl_car c"
				+ " INNER JOIN tbl_order o ON c.car_id = o.car_id"
				+ " LEFT JOIN tbl_payment p ON o.ordernum = p.ordernum"
				+ " WHERE c.dealer_id =? limit ?,5";
		
		int startrow = (page - 1) * 5;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dealer_id);
			pstmt.setInt(2, startrow);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				carList = new ArrayList<Car>();
				do {
					carList.add(new Car(
							rs.getString("c.dealer_id"),
							rs.getString("c.car_id"),
							rs.getString("c.car_brand"),
							rs.getString("c.car_name"),
							rs.getInt("c.car_price"),
							rs.getInt("c.car_year"),
							rs.getString("c.car_image1"),
							rs.getInt("c.car_like"),
							rs.getString("c.sale_YN"),
							rs.getString("c.car_delete")
							));
				}while(rs.next());
			}
		}catch(Exception e) {
			System.out.println("OrderDAO 클래스의 getOrderCarList()에서 발생한 에러 : "+e);
		}finally {
			close(rs);
			close(pstmt);
		}	
		return carList;
	}
	
	/*---- 딜러용 주문현황 리스트 가져오기 - order객체  ---------------------------------------------------------------------*/
	public ArrayList<Order> getOrderList(String dealer_id, int page, int limit) {
		ArrayList<Order> orderList = null;
		
		String sql = "SELECT c.*, o.*, p.*"
				+ " FROM tbl_car c"
				+ " INNER JOIN tbl_order o ON c.car_id = o.car_id"
				+ " LEFT JOIN tbl_payment p ON o.ordernum = p.ordernum"
				+ " WHERE c.dealer_id =? limit ?,5";
		
		int startrow = (page - 1) * 5;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dealer_id);
			pstmt.setInt(2, startrow);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				orderList = new ArrayList<Order>();
				do {
					orderList.add(new Order(
							rs.getString("o.ordernum"),
							rs.getString("o.car_id"),
							rs.getString("o.dealer_id"),
							rs.getString("o.user_id"),
							rs.getString("o.user_name"),
							rs.getString("o.coupon_id"),
							rs.getInt("o.discount_price"),
							rs.getTimestamp("o.order_date"),
							rs.getInt("o.car_price"),
							rs.getInt("o.car_tax"),
							rs.getInt("o.sale_expense"),
							rs.getString("o.region"),
							rs.getInt("o.deliveryfee"),
							rs.getInt("o.user_zipcode"),
							rs.getString("o.user_address1"),
							rs.getString("o.user_address2"),
							rs.getString("o.user_phone"),
							rs.getString("o.user_email"),
							rs.getInt("o.payment"),
							rs.getString("o.order_approve_YN")
							));
				}while(rs.next());
			}
		}catch(Exception e) {
			System.out.println("OrderDAO 클래스의 getOrderList()에서 발생한 에러 : "+e);
		}finally {
			close(rs);
			close(pstmt);
		}	
		return orderList;
	}
	
	/*---- 관리자용 주문현황 리스트 가져오기 - 개수  ---------------------------------------------------------------------*/
	public int getallOrderCount() {
		int result = 0;
		
		String sql = "SELECT count(*)"
				+ " FROM tbl_car c"
				+ " INNER JOIN tbl_order o ON c.car_id = o.car_id"
				+ " LEFT JOIN tbl_payment p ON o.ordernum = p.ordernum";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		}catch(Exception e) {
			System.out.println("OrderDAO 클래스의 getallOrderCount()에서 발생한 에러 : "+e);
		}finally {
			close(rs);
			close(pstmt);
		}		
		return result;
	}
	
	/*---- 관리자용 주문현황 리스트 가져오기 - Car객체  ---------------------------------------------------------------------*/
	public ArrayList<Car> getOrderCarList(int page, int limit) {
		ArrayList<Car> carList = null;
		
		String sql = "SELECT c.*, o.*, p.*"
				+ " FROM tbl_car c"
				+ " INNER JOIN tbl_order o ON c.car_id = o.car_id"
				+ " LEFT JOIN tbl_payment p ON o.ordernum = p.ordernum"
				+ " limit ?,5";
		
		int startrow = (page - 1) * 5;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				carList = new ArrayList<Car>();
				do {
					carList.add(new Car(
							rs.getString("c.dealer_id"),
							rs.getString("c.car_id"),
							rs.getString("c.car_brand"),
							rs.getString("c.car_name"),
							rs.getInt("c.car_price"),
							rs.getInt("c.car_year"),
							rs.getString("c.car_image1"),
							rs.getInt("c.car_like"),
							rs.getString("c.sale_YN"),
							rs.getString("c.car_delete")
							));
				}while(rs.next());
			}
		}catch(Exception e) {
			System.out.println("OrderDAO 클래스의 getOrderCarList()에서 발생한 에러 : "+e);
		}finally {
			close(rs);
			close(pstmt);
		}	
		return carList;
	}
	
	/*---- 관리자용 주문현황 리스트 가져오기 - order객체  ---------------------------------------------------------------------*/
	public ArrayList<Order> getOrderList(int page, int limit) {
		ArrayList<Order> orderList = null;
		
		String sql = "SELECT c.*, o.*, p.*"
				+ " FROM tbl_car c"
				+ " INNER JOIN tbl_order o ON c.car_id = o.car_id"
				+ " LEFT JOIN tbl_payment p ON o.ordernum = p.ordernum"
				+ " limit ?,5";
		
		int startrow = (page - 1) * 5;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				orderList = new ArrayList<Order>();
				do {
					orderList.add(new Order(
							rs.getString("o.ordernum"),
							rs.getString("o.car_id"),
							rs.getString("o.dealer_id"),
							rs.getString("o.user_id"),
							rs.getString("o.user_name"),
							rs.getString("o.coupon_id"),
							rs.getInt("o.discount_price"),
							rs.getTimestamp("o.order_date"),
							rs.getInt("o.car_price"),
							rs.getInt("o.car_tax"),
							rs.getInt("o.sale_expense"),
							rs.getString("o.region"),
							rs.getInt("o.deliveryfee"),
							rs.getInt("o.user_zipcode"),
							rs.getString("o.user_address1"),
							rs.getString("o.user_address2"),
							rs.getString("o.user_phone"),
							rs.getString("o.user_email"),
							rs.getInt("o.payment"),
							rs.getString("o.order_approve_YN")
							));
				}while(rs.next());
			}
		}catch(Exception e) {
			System.out.println("OrderDAO 클래스의 getOrderList()에서 발생한 에러 : "+e);
		}finally {
			close(rs);
			close(pstmt);
		}	
		return orderList;
	}
	
	/*---- 주문상세보기 - order 객체  ---------------------------------------------------------------------*/
	public Order getOrderDetail(String ordernum) {
		Order orderInfo = null;
		
		String sql = "SELECT c.*, o.*, p.*"
				+ " FROM tbl_car c"
				+ " INNER JOIN tbl_order o ON c.car_id = o.car_id"
				+ " LEFT JOIN tbl_payment p ON o.ordernum = p.ordernum"
				+ " WHERE o.ordernum =?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ordernum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				orderInfo = new Order();
				orderInfo.setOrdernum(rs.getString("o.ordernum"));
				orderInfo.setCar_id(rs.getString("o.car_id"));
				orderInfo.setDealer_id(rs.getString("o.dealer_id"));
				orderInfo.setUser_id(rs.getString("o.user_id"));
				orderInfo.setUser_name(rs.getString("o.user_name"));
				orderInfo.setCoupon_id(rs.getString("o.coupon_id"));
				orderInfo.setDiscount_price(rs.getInt("o.discount_price"));
				orderInfo.setOrder_date(rs.getTimestamp("o.order_date"));
				orderInfo.setCar_price(rs.getInt("o.car_price"));
				orderInfo.setCar_tax(rs.getInt("o.car_tax"));
				orderInfo.setSale_expense(rs.getInt("o.sale_expense"));
				orderInfo.setRegion(rs.getString("o.region"));
				orderInfo.setDeliveryfee(rs.getInt("o.deliveryfee"));
				orderInfo.setUser_zipcode(rs.getInt("o.user_zipcode"));
				orderInfo.setUser_address1(rs.getString("o.user_address1"));
				orderInfo.setUser_address2(rs.getString("o.user_address2"));
				orderInfo.setUser_phone(rs.getString("o.user_phone"));
				orderInfo.setUser_email(rs.getString("user_email"));
				orderInfo.setPayment(rs.getInt("o.payment"));
				orderInfo.setOrder_approve_YN(rs.getString("o.order_approve_YN"));			
			}
		}catch(Exception e) {
			System.out.println("OrderDAO 클래스의 getOrderDetail()에서 발생한 에러 : "+e);
		}finally {
			close(rs);
			close(pstmt);
		}	
		
		return orderInfo;
	}
	
	/*---- 주문상세보기 - payment 객체  ---------------------------------------------------------------------*/
	public Payment getPayDetail(String ordernum) {
		Payment payInfo = null;
		
		String sql = "SELECT c.*, o.*, p.*"
				+ " FROM tbl_car c"
				+ " INNER JOIN tbl_order o ON c.car_id = o.car_id"
				+ " LEFT JOIN tbl_payment p ON o.ordernum = p.ordernum"
				+ " WHERE p.ordernum =?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ordernum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				payInfo = new Payment();
				payInfo.setOrdernum(rs.getString("ordernum"));
				payInfo.setPay_by(rs.getInt("pay_by"));
				payInfo.setPay_price(rs.getInt("pay_price"));
				payInfo.setPay_depositor_name(rs.getString("pay_depositor_name"));
				payInfo.setPay_creditcard_name(rs.getString("pay_creditcard_name"));
				payInfo.setPay_creditcard_num(rs.getString("pay_creditcard_num"));
				payInfo.setPay_creditcard_cvc(rs.getInt("pay_creditcard_cvc"));
				payInfo.setPay_creditcard_date(rs.getString("pay_creditcard_date"));
			}
		}catch(Exception e) {
			System.out.println("OrderDAO 클래스의 getOrderDetail()에서 발생한 에러 : "+e);
		}finally {
			close(rs);
			close(pstmt);
		}	
		
		return payInfo;
	}
	
	/*---- 모든주문정보 가져오기 ---------------------------------------------------------------------*/
	public ArrayList<Payment> getAllPayList() {
		ArrayList<Payment> allPayList = null;
		
		String sql = "select * from tbl_payment";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				allPayList = new ArrayList<Payment>();
				do {
					allPayList.add(new Payment(
							rs.getString("ordernum"),
							rs.getInt("pay_by"),
							rs.getInt("pay_price"),
							rs.getString("pay_depositor_name"),
							rs.getString("pay_creditcard_name"),
							rs.getString("pay_creditcard_num"),
							rs.getString("pay_creditcard_date"),
							rs.getInt("pay_creditcard_cvc")							
							));
				}while(rs.next());
			}
		}catch(Exception e) {
			System.out.println("OrderDAO 클래스의 getAllPayList()에서 발생한 에러 : "+e);
		}finally {
			close(rs);
			close(pstmt);
		}	
		
		return allPayList;
	}
	
	/*---- 주문상세보기 - 주문승인 ---------------------------------------------------------------------*/
	public int orderApprove(String[] car_ids) {
		int orderY = 0;
		
		String where_car_id = "";
		for(int i=0; i<car_ids.length; i++) {
			if(i == 0) {
				where_car_id += " (car_id='"+car_ids[i]+"'";
			}else {
				where_car_id += " or car_id='"+car_ids[i]+"'";
			}
		}
		where_car_id += ")";
		
		String sql = "update tbl_order set order_approve_YN='Y' where"+where_car_id;
		
		try {
			pstmt = con.prepareStatement(sql);
			orderY = pstmt.executeUpdate();

		}catch(Exception e) {
			System.out.println("OrderDAO 클래스의 orderApprove()에서 발생한 에러 : "+e);
		}finally {
			close(pstmt);
		}
		return orderY;
	}

	/*---- 주문상세보기 - 주문거절 ---------------------------------------------------------------------*/
	public int orderRefuse(String[] car_ids) {
		int orderN = 0;
		
		String where_car_id = "";
		for(int i=0; i<car_ids.length; i++) {
			if(i == 0) {
				where_car_id += " (car_id='"+car_ids[i]+"'";
			}else {
				where_car_id += " or car_id='"+car_ids[i]+"'";
			}
		}
		where_car_id += ")";
		
		String sql = "update tbl_order set order_approve_YN='N' where"+where_car_id;
		
		try {
			pstmt = con.prepareStatement(sql);
			orderN = pstmt.executeUpdate();

		}catch(Exception e) {
			System.out.println("OrderDAO 클래스의 orderRefuse()에서 발생한 에러 : "+e);
		}finally {
			close(pstmt);
		}
		return orderN;
	}

	

	
	
	
	
}



