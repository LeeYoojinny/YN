package svc.adm;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.OrderDAO;
import dao.UserDAO;

public class AdminOrderDecisionService {

	//승인,거절 시 : order-approve 수정, car-sale 수정
	public boolean updateOrderY(String[] car_ids) {
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		boolean result = false;
		int orderY = orderDAO.orderApprove(car_ids);
		int carSaleN = userDAO.saleN(car_ids);
		
		if(orderY > 0 && carSaleN > 0) {
			commit(con);
			result = true; //성공했으므로 true로 바꿔줌
		}else {
			rollback(con);
		}
			
		close(con);
		return result;
	}

	public boolean updateOrderN(String[] car_ids) {
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		boolean result = false;
		int orderN = orderDAO.orderRefuse(car_ids);
		int carSaleY = userDAO.saleY(car_ids);
		
		if(orderN > 0 && carSaleY > 0) {
			commit(con);
			result = true;
		}else {
			rollback(con);
		}
			
		close(con);
		return result;
	}

}
