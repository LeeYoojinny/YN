package dao;

import static db.JdbcUtil.close;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
		
		String sql = "insert into tbl_order(car_id,user_id,coupon_id,discount_price,car_price,car_tax,"
				+ "region,deliveryfee,user_zipcode,user_address1,user_address2,user_phone,payment) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, order.getCar_id());
			pstmt.setString(2, order.getUser_id());
			pstmt.setString(3, order.getCoupon_id());
			pstmt.setInt(4, order.getDiscount_price());
			pstmt.setInt(5, order.getCar_price());
			pstmt.setInt(6, order.getCar_tax());
			pstmt.setString(7, order.getRegion());
			pstmt.setInt(8, order.getDeliveryfee());
			pstmt.setInt(9, order.getUser_zipcode());
			pstmt.setString(10, order.getUser_address1());
			pstmt.setString(11, order.getUser_address2());
			pstmt.setString(12, order.getUser_phone());
			pstmt.setInt(13, order.getPayment());
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("OrderDAO 클래스의 insertOrder()에서 발생한 에러 : "+e);
		}finally {
			close(pstmt);
		}
		return result;
	}


	public String getOrderNum(String car_id) {
		String orderNum = "";
		
		String sql = "select orderNum from tbl_order where car_id=?";
		
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
			pstmt.setInt(6, payment.getPay_creditcard_num());
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


	
}



