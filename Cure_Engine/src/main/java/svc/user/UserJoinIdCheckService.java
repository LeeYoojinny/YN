package svc.user;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.UserDAO;
import vo.User;

public class UserJoinIdCheckService {

	public boolean idCheckResult(String inputId) {
		Connection con = getConnection();//JdbcUtil. 생략 → 위에서 static으로 import 했으므로 생략가능
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		User isUsedId = userDAO.selectUserInfo(inputId);
		
		boolean idCheckResult = false;
		
		if(isUsedId == null ) {
			idCheckResult = true;
		}
		
		close(con);
		return idCheckResult;

	}

}
