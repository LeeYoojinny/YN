package svc.board;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.BoardDAO;
import vo.Board;

public class QnA_BoardUpdateService {

	public boolean updateBoard(Board board, String qna_num) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int updateCount = boardDAO.updateBoard(board,qna_num);
		boolean isUpdateSuccess = false;
		
		if(updateCount > 0) {
			commit(con);
			isUpdateSuccess = true;
		}else {
			rollback(con);
		}
		
		close(con);
		return isUpdateSuccess;
	}

}
