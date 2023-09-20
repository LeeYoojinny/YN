package svc.cust;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.CarDAO;
import dao.OrderDAO;

public class CustomerCancelOrderService {

	public boolean cancelOrder(String car_id) {
		Connection con = getConnection();
		CarDAO carDAO = CarDAO.getInstance();
		carDAO.setConnection(con);
		
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		boolean result = false;
		int carResult = carDAO.saleChangeY(car_id);
		System.out.println("car테이블 update : " + carResult);
		
		int orderResult = orderDAO.cancelOrder(car_id);
		System.out.println("order테이블 update : " + orderResult);
		
		if(carResult > 0 && orderResult > 0) {
			commit(con);
			result = true;
		}else {
			rollback(con);
		}
		
		close(con);
		return result;
	}

}
