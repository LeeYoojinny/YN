package svc.adm;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.UserDAO;
import vo.User;

public class AdminDealerListService {


	public int getDealerListCount() {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		int listCount = userDAO.getDealerListCount();
		
		close(con);
		return listCount;
	}

	
	public ArrayList<User> getAllDealer(int page, int limit) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		ArrayList<User> dealerList = userDAO.getAllDealer(page,limit);
		System.out.println("dealer 정보 개수 : "+dealerList.size());
		
		close(con);
		return dealerList;
	}

}
