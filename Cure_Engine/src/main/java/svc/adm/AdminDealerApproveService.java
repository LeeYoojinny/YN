package svc.adm;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.CodeDAO;
import dao.UserDAO;

public class AdminDealerApproveService {

	public int joinApprove_Y(String[] dealer_ids) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		int approveResult = userDAO.approveDealer(dealer_ids);
		
		if(approveResult > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		return approveResult;

	}

	public int codeUpdate(String[] dealer_ids) {
		Connection con = getConnection();
		CodeDAO codeDAO = CodeDAO.getInstance();
		codeDAO.setConnection(con);
		
		int insertResult = codeDAO.insertDealer(dealer_ids);
		
		if(insertResult > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		return insertResult;
	}

}
