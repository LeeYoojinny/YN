package svc.adm;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CarDAO;
import dao.CodeDAO;
import dao.OrderDAO;
import dao.UserDAO;
import vo.Car;
import vo.Code;
import vo.Order;
import vo.Reservation;
import vo.User;

public class AdminDealerDetailViewService {
	public User getDealerInfo(String user_id) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		User dealerInfo = userDAO.selectUserInfo(user_id);
		
		close(con);
		return dealerInfo;
	}

	public ArrayList<Code> getAllCode() {
		Connection con = getConnection();
		CodeDAO codeDAO = CodeDAO.getInstance();
		codeDAO.setConnection(con);
		
		ArrayList<Code> allCode = codeDAO.selectAllCode();
		
		close(con);
		return allCode;
	}

	public int getListCount(String user_id) {
		Connection con = getConnection();
		CarDAO carDAO = CarDAO.getInstance();
		carDAO.setConnection(con);
		
		int count = carDAO.getSaleCount(user_id);
		
		close(con);
		return count;
	}

	public ArrayList<Car> getMySaleCarList(String user_id, int page, int limit) {
		Connection con = getConnection();
		CarDAO carDAO = CarDAO.getInstance();
		carDAO.setConnection(con);
		
		ArrayList<Car> mySaleCarList = carDAO.selectmySaleCar(user_id, page, limit);
		
		close(con);
		return mySaleCarList;
	}

	public ArrayList<Reservation> getReservation(String user_id, int page, int limit) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		ArrayList<Reservation> reservation = userDAO.selectReservation(user_id,page, limit);
		
		close(con);
		
		return reservation;
	}

	public ArrayList<Order> getOrderList(String user_id, int page, int limit) {
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		ArrayList<Order> orderList = null;		
		orderList = orderDAO.getOrderList(user_id,page,limit);			
		
		close(con);
		return orderList;
	}
}
