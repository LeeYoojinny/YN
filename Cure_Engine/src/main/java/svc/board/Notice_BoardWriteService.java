package svc.board;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.NoticeBoardDAO;
import vo.NoticeBoard;

public class Notice_BoardWriteService {

	public boolean writeNoticeBoard(NoticeBoard notice) {
		Connection con = getConnection();
		NoticeBoardDAO noticeBoardDAO = NoticeBoardDAO.getInstance();
		noticeBoardDAO.setConnection(con);
		
		int insertCount = noticeBoardDAO.insertBoard(notice);
		boolean isWriteSuccess = false;
		
		if(insertCount > 0) {
			commit(con);
			isWriteSuccess = true;
		}else {
			rollback(con);
		}
		close(con);
		return isWriteSuccess;
	}

}
