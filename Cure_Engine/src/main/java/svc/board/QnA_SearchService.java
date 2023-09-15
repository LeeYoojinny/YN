package svc.board;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CodeDAO;
import dao.QnABoardDAO;
import vo.Code;
import vo.QnABoard;

public class QnA_SearchService {
	public int getListCount(String keyword,int searchMethod) {
		Connection con = getConnection();
		QnABoardDAO boardDAO = QnABoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int listCount = 0;
		listCount = boardDAO.getSearchCount(keyword,searchMethod);
		
		close(con);
		return listCount;
	}

	public ArrayList<QnABoard> getSearchResult_1(String keyword) {
		Connection con= getConnection();
		QnABoardDAO boardDAO = QnABoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		ArrayList<QnABoard> result = boardDAO.getSearchResult_1(keyword);

		close(con);
		return result;
	}

	public ArrayList<QnABoard> getSearchResult_2(String keyword) {
		Connection con= getConnection();
		QnABoardDAO boardDAO = QnABoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		ArrayList<QnABoard> result = boardDAO.getSearchResult_2(keyword);

		close(con);
		return result;
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
