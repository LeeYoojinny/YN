package svc.cust;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.UserDAO;
import vo.Order;
import vo.Payment;

public class CustomerOrderService {

	public int insertOrder(Order order, Payment payment) {
		Connection con = getConnection();//JdbcUtil. 생략 → 위에서 static으로 import 했으므로 생략가능
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		int orderSuccess = userDAO.insertOrder(order);
		String orderNum = userDAO.getOrderNum(order.getCar_id());
		int paySuccess = userDAO.insertPay(payment, orderNum);
		
		if(orderSuccess > 0 && paySuccess > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);		
		return orderSuccess;
	}

}
