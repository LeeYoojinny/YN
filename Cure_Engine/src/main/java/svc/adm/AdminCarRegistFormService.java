package svc.adm;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CodeDAO;
import vo.Code;

public class AdminCarRegistFormService {

	public ArrayList<Code> getAllCode() {
		Connection con = getConnection();
		CodeDAO codeDAO = CodeDAO.getInstance();
		codeDAO.setConnection(con);
		
		ArrayList<Code> allCode = codeDAO.selectAllCode();
		
		close(con);
		return allCode;
	}

}
