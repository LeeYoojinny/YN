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
		
		CodeDAO codeDAO = CodeDAO.getInstance();
		codeDAO.setConnection(con);
		
		//user 테이블의 use_YN 컬럼에 Y update하는 작업
		int approveResult = userDAO.approveDealer(dealer_ids);
		
		//코드 테이블에 insert 하는 작업
		String[] user_name = userDAO.getDealerName(dealer_ids);
		int insertResult = codeDAO.insertDealer(dealer_ids,user_name);
		
		if(approveResult > 0 && insertResult > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		return approveResult;

	}


}
