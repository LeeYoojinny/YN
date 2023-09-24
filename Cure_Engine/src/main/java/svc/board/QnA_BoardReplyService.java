package svc.board;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.QnABoardDAO;
import vo.QnABoard;

public class QnA_BoardReplyService {

	public boolean insertReply(QnABoard board,String parent_qna_num) {
		Connection con= getConnection();
		QnABoardDAO boardDAO = QnABoardDAO.getInstance();
		boardDAO.setConnection(con);		
		
		int replyCount = boardDAO.insertReply(board);
		int parent_replyY = boardDAO.parentReplyYN(parent_qna_num);
		boolean replySuccess = false;
		
		if(replyCount > 0 && parent_replyY >0) {			
			commit(con);
			replySuccess = true;
		}else {
			rollback(con);
		}
		close(con);
		return replySuccess;

	}

	public String createQna_num() {
		Connection con= getConnection();
		QnABoardDAO boardDAO = QnABoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		String qna_num = boardDAO.createQna_num();
		
		close(con);
		return qna_num;
	}

}
