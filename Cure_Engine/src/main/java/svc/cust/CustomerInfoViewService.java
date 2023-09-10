package svc.cust;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.UserDAO;
import vo.User;

public class CustomerInfoViewService {

	public User getUserInfo(String user_id) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		User userInfo = userDAO.selectUserInfo(user_id);
		
		close(con);
		return userInfo;
	}

}
