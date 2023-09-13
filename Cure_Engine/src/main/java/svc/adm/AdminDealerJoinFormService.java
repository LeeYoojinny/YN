package svc.adm;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.UserDAO;

public class AdminDealerJoinFormService {

	public String getDealerId() {
		Connection con = getConnection();//JdbcUtil. 생략 → 위에서 static으로 import 했으므로 생략가능
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		String dealer_id = userDAO.getDealerId();
		
		close(con);
		return dealer_id;
	}

}
