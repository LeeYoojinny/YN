package svc.adm;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.UserDAO;
import vo.User;

public class AdminCustomerListService {
	public int getCustListCount() {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		int listCount = userDAO.getCustListCount();
		
		close(con);
		return listCount;
	}

	
	public ArrayList<User> getAllCustomer(int page, int limit) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		ArrayList<User> customerList = userDAO.getAllCustomer(page,limit);
		System.out.println("customer 정보 개수 : "+customerList.size());
		
		close(con);
		return customerList;
	}

	
}
