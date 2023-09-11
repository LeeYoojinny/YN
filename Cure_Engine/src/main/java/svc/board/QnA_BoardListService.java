package svc.board;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import dao.CodeDAO;
import vo.Board;
import vo.Code;

public class QnA_BoardListService {
	
	public int getListCount() {
		
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int listCount = 0;
		listCount = boardDAO.selectListCount();
		
		close(con);
		return listCount;
	}
	
	public ArrayList<Board> selectBoardList(int page, int limit) {
		
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		ArrayList<Board> boardList = boardDAO.selectBoardList(page,limit);

		close(con);
		
		return boardList;
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
