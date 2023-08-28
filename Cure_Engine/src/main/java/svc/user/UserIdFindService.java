package svc.user;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.UserDAO;
import vo.User;

public class UserIdFindService {

	public String findId(User findIdInfo) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		String getId = userDAO.findId(findIdInfo);
		
		return getId;
	}

}
