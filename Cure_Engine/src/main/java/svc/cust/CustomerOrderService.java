package svc.cust;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.CarDAO;
import dao.OrderDAO;
import dao.UserDAO;
import vo.Order;
import vo.Payment;

public class CustomerOrderService {

	public boolean insertOrder(Order order, Payment payment) {
		Connection con = getConnection();//JdbcUtil. 생략 → 위에서 static으로 import 했으므로 생략가능
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		CarDAO carDAO = CarDAO.getInstance();
		carDAO.setConnection(con);
		
		boolean allSuccess = false;
		
		int orderSuccess = orderDAO.insertOrder(order); //order 테이블 추가
		System.out.println("orderSuccess  : "+orderSuccess);
		String orderNum = orderDAO.getOrderNum(order.getCar_id());//orderNum 가져오기
		System.out.println("orderNum : " + orderNum);
		int paySuccess = orderDAO.insertPay(payment, orderNum);//payment테이블에 추가
		System.out.println("paySuccess : " + paySuccess);
		int saleChange = carDAO.saleChangeW(order.getCar_id());//자동차 sale 상태를 W로 바꿈
		System.out.println("saleChange : " + saleChange);
		int couponChange = 0;
		
		if(order.getCoupon_id() != null && !order.getCoupon_id().isEmpty()) {//만약 쿠폰 적용을 했다면
			couponChange = orderDAO.couponChangeY(order.getCoupon_id());//쿠폰 사용을 Y로 바꾸기	
			System.out.println("couponChange : " + couponChange);
			if(orderSuccess > 0 && paySuccess > 0 && saleChange > 0 && couponChange > 0) {			
				commit(con);
				allSuccess = true;
			}else {
				rollback(con);
			}
		}else {		
			if(orderSuccess > 0 && paySuccess > 0 && saleChange > 0) {			
				commit(con);
				allSuccess = true;
			}else {
				rollback(con);
			}
		}
		
		close(con);		
		return allSuccess;
	}

}
