package svc.adm;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.UserDAO;
import vo.User;

public class AdminDealerInfoUpdateService {

	public boolean dealerUpdate(User user) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		int updatetUserCount = userDAO.updateUser(user);
		
		boolean isUserUpdateResult = false;
		
		if(updatetUserCount > 0) {
			isUserUpdateResult = true;
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		return isUserUpdateResult;
	}

}
