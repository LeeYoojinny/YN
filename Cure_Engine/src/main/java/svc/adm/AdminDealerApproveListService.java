package svc.adm;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.UserDAO;
import vo.User;

public class AdminDealerApproveListService {

	public ArrayList<User> getDealerList(int page, int limit) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		ArrayList<User> disapprove_list = userDAO.disaprv_DealerList(page,limit);
		
		close(con);
		return disapprove_list;
	}

	public int getListCount() {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		int listCount = userDAO.getdisaprv_listCount();
		
		close(con);
		return listCount;
	}

}
