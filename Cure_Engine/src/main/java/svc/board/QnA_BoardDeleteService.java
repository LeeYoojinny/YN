package svc.board;

import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.BoardDAO;

public class QnA_BoardDeleteService {

	public boolean removeBoard(String qna_num) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int deleteCount = boardDAO.deleteBoard(qna_num);
		boolean isRemoveSuccess = false;
		
		if(deleteCount > 0) {
			isRemoveSuccess = true;
			commit(con);
		}else {
			rollback(con);
		}
		return isRemoveSuccess;
	}
	
}
