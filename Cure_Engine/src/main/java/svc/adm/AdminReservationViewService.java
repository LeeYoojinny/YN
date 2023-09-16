package svc.adm;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;


import dao.UserDAO;
import vo.Reservation;

public class AdminReservationViewService {

	public ArrayList<Reservation> getReservation(String user_id,int page,int limit) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		ArrayList<Reservation> reservation = userDAO.selectReservation(user_id,page, limit);
		
		close(con);
		
		return reservation;
	}

	public ArrayList<Reservation> getAllReservation(int page,int limit) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		ArrayList<Reservation> reservation = userDAO.selectAllReservation(page, limit);
		
		close(con);		
		return reservation;
	}

	public int getRsvListCount() {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		int listCount = userDAO.getRsvListCount();
		
		close(con);
		return listCount;
	}

}
