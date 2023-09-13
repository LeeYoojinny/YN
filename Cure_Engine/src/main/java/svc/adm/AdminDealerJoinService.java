package svc.adm;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.UserDAO;
import vo.User;

public class AdminDealerJoinService {

	public boolean userJoin(User user) {
		Connection con = getConnection();//JdbcUtil. 생략 → 위에서 static으로 import 했으므로 생략가능
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		int insertDealerCount = userDAO.insertDealer(user);
		
		boolean isDealerJoinResult = false;
		
		if(insertDealerCount > 0 ) {
			isDealerJoinResult = true;
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		return isDealerJoinResult;
	}

}
