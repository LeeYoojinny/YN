package svc.board;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CodeDAO;
import dao.NoticeBoardDAO;
import vo.Code;
import vo.NoticeBoard;


public class Notice_BoardListService {

	public int getListCount() {
		Connection con = getConnection();
		NoticeBoardDAO noticeBoardDAO = NoticeBoardDAO.getInstance();
		noticeBoardDAO.setConnection(con);
		
		int listCount = 0;
		listCount = noticeBoardDAO.selectListCount();
		
		close(con);
		return listCount;
	}

	public ArrayList<NoticeBoard> selectBoardList(int page, int limit) {
		Connection con = getConnection();
		NoticeBoardDAO noticeBoardDAO = NoticeBoardDAO.getInstance();
		noticeBoardDAO.setConnection(con);
		
		ArrayList<NoticeBoard> boardList = noticeBoardDAO.selectBoardList(page,limit);

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
