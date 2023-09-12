package svc.board;

import static db.JdbcUtil.*;

import java.sql.Connection;
import vo.QnABoard;
import dao.QnABoardDAO;

public class QnA_BoardWriteService {

	public boolean writeQnABoard(QnABoard board) {
		Connection con= getConnection();
		QnABoardDAO boardDAO = QnABoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		
		int insertCount = boardDAO.insertBoard(board);
		boolean isWriteSuccess = false;
		
		if(insertCount > 0) {
			commit(con);
			isWriteSuccess = true;
		}else {
			rollback(con);
		}
		close(con);
		return isWriteSuccess;
	}

}
