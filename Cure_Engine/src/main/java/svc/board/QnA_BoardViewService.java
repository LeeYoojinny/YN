package svc.board;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import dao.CodeDAO;
import vo.Board;
import vo.Code;

public class QnA_BoardViewService {

	public Board getBoardView(String qna_num) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int boardHitCount = boardDAO.updateHit(qna_num);
		
		if(boardHitCount > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		Board board = boardDAO.selectBoard(qna_num);
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
