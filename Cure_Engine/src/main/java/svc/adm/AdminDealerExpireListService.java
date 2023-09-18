package svc.adm;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.UserDAO;
import vo.User;

public class AdminDealerExpireListService {
	public int getCustListCount() {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		int listCount = userDAO.getExpireDealerListCount();
		
		close(con);
		return listCount;
	}

	public ArrayList<User> getExpireList(int page, int limit) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		ArrayList<User> expireDealer = userDAO.getExpireDealerList(page,limit);
		
		close(con);
		return expireDealer;
	}

}
