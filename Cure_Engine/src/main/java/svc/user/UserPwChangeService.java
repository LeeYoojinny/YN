package svc.user;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.UserDAO;

public class UserPwChangeService {

	public boolean checkUser(String user_id, String user_pw) {
		Connection con = getConnection();//JdbcUtil. 생략 → 위에서 static으로 import 했으므로 생략가능
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		int checkUser = userDAO.checkPw(user_id,user_pw);
		boolean checkOk = false;
		
		if(checkUser > 0) {
			checkOk = true;
		}
		
		close(con);
		return checkOk;
	}

	public boolean changePw(String user_id, String change_pw) {
		Connection con = getConnection();//JdbcUtil. 생략 → 위에서 static으로 import 했으므로 생략가능
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		int changePw = userDAO.changePw(user_id,change_pw);
		
		boolean changeOk = false;
		
		if(changePw > 0 ) {
			changeOk = true;
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		return changeOk;
	}

}
