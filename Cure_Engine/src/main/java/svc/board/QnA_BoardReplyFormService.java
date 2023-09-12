package svc.board;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CarDAO;
import dao.CodeDAO;
import dao.QnABoardDAO;
import vo.Car;
import vo.Code;
import vo.QnABoard;

public class QnA_BoardReplyFormService {

	public QnABoard getboardInfo(String qna_num) {
		Connection con = getConnection();
		QnABoardDAO boardDAO = QnABoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		QnABoard board = boardDAO.selectBoard(qna_num);
		
		close(con);
		return board;
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
