package svc.cust;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.CodeDAO;
import dao.OrderDAO;
import vo.Car;
import vo.Code;
import vo.Order;
import vo.Payment;

public class CustomerOrderListService {

	public int getOrderListCount(String user_id) {
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		int result = 0;
		
		result = orderDAO.getCustOrderCount(user_id);
		
		close(con);
		return result;
	}

	public ArrayList<Car> getOrderCarList(String user_id, int page, int limit) {
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		ArrayList<Car> carList = null;		
		carList = orderDAO.getCustOrderCarList(user_id,page,limit);				
		
		close(con);
		return carList;
	}

	public ArrayList<Order> getOrderList(String user_id, int page, int limit) {
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		ArrayList<Order> orderList = null;
		
		orderList = orderDAO.getCustOrderList(user_id,page,limit);		
		
		
		close(con);
		return orderList;
	}

	public ArrayList<Payment> getAllPayList(List<String> ordernumList) {
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		ArrayList<Payment> allPayList = orderDAO.getAllPayList(ordernumList);
		
		close(con);
		return allPayList;
	}

	public ArrayList<Code> getAllCode() {
		Connection con = getConnection();
		CodeDAO codeDAO = CodeDAO.getInstance();
		codeDAO.setConnection(con);
		
		ArrayList<Code> allCode = codeDAO.selectAllCode();
		
		close(con);
		return allCode;
	}

}
