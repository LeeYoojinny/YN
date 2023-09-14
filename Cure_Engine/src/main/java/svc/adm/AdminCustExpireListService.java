package svc.adm;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.UserDAO;
import vo.User;

public class AdminCustExpireListService {
	public int getCustListCount() {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		int listCount = userDAO.getExpireCustListCount();
		
		close(con);
		return listCount;
	}
	
	public ArrayList<User> getExpireList(int page, int limit) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		ArrayList<User> expireCust = userDAO.getExpireCustList(page,limit);
		
		close(con);
		return expireCust;
	}

	

}
