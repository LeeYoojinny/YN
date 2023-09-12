package svc.board;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.NoticeBoardDAO;
import vo.NoticeBoard;

public class Notice_BoardViewService {

	public NoticeBoard getBoardView(String notice_num) {
		Connection con = getConnection();
		NoticeBoardDAO noticeBoardDAO = NoticeBoardDAO.getInstance();
		noticeBoardDAO.setConnection(con);
		
		int boardHitCount = noticeBoardDAO.updateHit(notice_num);
		NoticeBoard notice = null;
		if(boardHitCount > 0) {
			commit(con);
			notice = noticeBoardDAO.selectBoardView(notice_num);
		}else {
			rollback(con);
		}		
		
		close(con);
		return notice;
	}

}
