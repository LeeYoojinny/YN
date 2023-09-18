package svc.adm;

import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.UserDAO;

public class AdminReservationCheckService {

	public boolean revCheck(String resernum, String check) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		int updateRevCount = userDAO.updateResevation(resernum,check);
		
		boolean isUpdateSuccess = false;
		
		if(updateRevCount > 0) {
			commit(con);
			isUpdateSuccess = true;
		}else {
			rollback(con);
		}
		return isUpdateSuccess;
	}

}
