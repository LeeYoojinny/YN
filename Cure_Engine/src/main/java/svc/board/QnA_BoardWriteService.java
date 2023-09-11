package svc.board;

import static db.JdbcUtil.*;

import java.sql.Connection;
import vo.Board;
import dao.BoardDAO;

public class QnA_BoardWriteService {

	public boolean writeQnABoard(Board board) {
		Connection con= getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
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
