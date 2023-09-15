package svc.adm;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.UserDAO;

public class AdminCustRejoinService {

	public int custRejoin(String user_id) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		int result = userDAO.custRejoin(user_id);
		
		if(result > 0 ) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		return result;
		
	}

}
