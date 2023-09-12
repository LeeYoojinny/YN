package svc.board;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.NoticeBoardDAO;
import vo.NoticeBoard;

public class Notice_BoardUpdateFormService {

	public NoticeBoard getBoardInfo(String notice_num) {
		Connection con = getConnection();
		NoticeBoardDAO noticeBoardDAO = NoticeBoardDAO.getInstance();
		noticeBoardDAO.setConnection(con);
		
		NoticeBoard notice = noticeBoardDAO.selectBoardView(notice_num);
		
		close(con);
		return notice;
	}

}
