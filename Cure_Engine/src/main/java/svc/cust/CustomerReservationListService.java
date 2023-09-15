package svc.cust;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CodeDAO;
import dao.UserDAO;
import vo.Code;
import vo.Reservation;

public class CustomerReservationListService {

	public ArrayList<Reservation> getMyReservation(String user_id) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		ArrayList<Reservation> myReservation = userDAO.selectMyReservation(user_id);
		
		close(con);
		return myReservation;
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
