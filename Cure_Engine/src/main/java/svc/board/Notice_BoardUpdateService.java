package svc.board;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.NoticeBoardDAO;
import vo.NoticeBoard;

public class Notice_BoardUpdateService {

	public boolean updateNotice(NoticeBoard notice, String notice_num) {
		Connection con = getConnection();
		NoticeBoardDAO noticeBoardDAO = NoticeBoardDAO.getInstance();
		noticeBoardDAO.setConnection(con);
		
		int updatetCount = noticeBoardDAO.updateBoard(notice,notice_num);
		boolean isUpdateSuccess = false;
		
		if(updatetCount > 0) {
			commit(con);
			isUpdateSuccess = true;
		}else {
			rollback(con);
		}
		close(con);
		return isUpdateSuccess;
	}

}
