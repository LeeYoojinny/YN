package svc.board;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.QnABoardDAO;
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

}
