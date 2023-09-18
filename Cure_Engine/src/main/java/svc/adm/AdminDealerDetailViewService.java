package svc.adm;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CodeDAO;
import dao.UserDAO;
import vo.Code;
import vo.User;

public class AdminDealerDetailViewService {
	public User getDealerInfo(String user_id) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		User dealerInfo = userDAO.selectUserInfo(user_id);
		
		close(con);
		return dealerInfo;
	}

	public ArrayList<Code> getAllCode() {
		Connection con = getConnection();
		CodeDAO codeDAO = CodeDAO.getInstance();
		codeDAO.setConnection(con);
		
		ArrayList<Code> allCode = codeDAO.selectAllCode();
		
		close(con);
		return allCode;
	}
}
