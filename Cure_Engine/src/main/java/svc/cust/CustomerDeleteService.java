package svc.cust;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.UserDAO;

public class CustomerDeleteService {

	public int checkPw(String user_id, String user_pw) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		int checkUser = userDAO.checkPw(user_id,user_pw);
		
		close(con);
		return checkUser;
	}

	public int deleteCust(String user_id) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		int result = userDAO.custDelete(user_id);
		
		if(result > 0) {			
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		return result;
	}

}
