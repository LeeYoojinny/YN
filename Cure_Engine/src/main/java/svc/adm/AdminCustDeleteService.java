package svc.adm;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.UserDAO;

public class AdminCustDeleteService {

	public int custDelete(String user_id) {
		Connection con = getConnection();//JdbcUtil. 생략 → 위에서 static으로 import 했으므로 생략가능
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		int result = userDAO.custDelete(user_id);
		
		if(result > 0 ) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		return result;
	}

}
