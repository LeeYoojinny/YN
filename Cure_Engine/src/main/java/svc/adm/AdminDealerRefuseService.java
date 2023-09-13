package svc.adm;

import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.UserDAO;

public class AdminDealerRefuseService {

	public int setDealerRefuse(String dealer_id) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		int refuseResult = userDAO.dealerRefuse(dealer_id);
		
		if(refuseResult > 0 ) {
			commit(con);
		}else {
			rollback(con);
		}
		
		return refuseResult;
	}

}
