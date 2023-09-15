package svc.cust;

import vo.Reservation;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.UserDAO;

public class CustomerReservationService {

	public boolean reserve(Reservation reservation) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		boolean isReserveSuccess = false;
		int reserveResult = userDAO.insertReserve(reservation);
		
		if(reserveResult > 0) {
			commit(con);
			isReserveSuccess = true;
		}else {
			rollback(con);
		}
		
		return isReserveSuccess;
	}

}
