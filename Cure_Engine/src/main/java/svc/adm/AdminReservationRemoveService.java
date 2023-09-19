package svc.adm;

import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.UserDAO;

public class AdminReservationRemoveService {

	public int removeRev(String[] resernums, String user_id) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		int removeResult = userDAO.removeRev(resernums,user_id);
		
		if(removeResult > 0) {
			commit(con);
		}else {
			rollback(con);
		}
	
		return removeResult;
	}

}
