package svc.adm;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.UserDAO;
import vo.User;

public class AdminCustSearchService {

	public User getSearchUser(String keyword) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		User searchUser = userDAO.selectUserInfo(keyword);
				
		close(con);
		return searchUser;
	}

}
