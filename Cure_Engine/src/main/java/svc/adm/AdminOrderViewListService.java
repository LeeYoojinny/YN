package svc.adm;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CarDAO;
import dao.CodeDAO;
import dao.OrderDAO;
import vo.Car;
import vo.Order;
import vo.Code;

public class AdminOrderViewListService {
	
	public int getOrderListCount(String dealer_id, String user_category) {
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		int result = 0;
		
		//딜러와 관리자는 가져오는 내용이 다르므로 쿼리문 각각 생성
		if(user_category.equals("dealer")) {//해당딜러의 주문건만 가져오기
			result = orderDAO.getmyOrderCount(dealer_id);
		}else if(user_category.equals("admin")) {//전체 주문건 가져오기
			result = orderDAO.getallOrderCount();
		}
		
		close(con);
		return result;
	}

	public ArrayList<Car> getOrderCarList(String dealer_id, int page, int limit) {
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		ArrayList<Car> carList = orderDAO.getOrderCarList(dealer_id,page,limit);				
		
		close(con);
		return carList;
		
	}
	
	public ArrayList<Order> getOrderList(String dealer_id, int page, int limit) {
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
	
		ArrayList<Order> orderList = orderDAO.getOrderList(dealer_id,page,limit);		
		
		close(con);
		return orderList;
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
