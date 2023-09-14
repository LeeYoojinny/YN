package svc.adm;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CodeDAO;
import dao.QnABoardDAO;
import dao.UserDAO;
import vo.Code;
import vo.QnABoard;
import vo.User;

public class AdminCustDetailViewService {

	public User getCustInfo(String user_id) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		User custInfo = userDAO.selectUserInfo(user_id);
		
		close(con);
		return custInfo;
	}

	public ArrayList<QnABoard> getCustQna(String user_id) {
		Connection con = getConnection();
		QnABoardDAO boardDAO = QnABoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		ArrayList<QnABoard> custQna = boardDAO.getCustQna(user_id);
		
		close(con);
		return custQna;
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
