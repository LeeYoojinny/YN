package svc.board;

import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.NoticeBoardDAO;

public class Notice_BoardDeleteService {

	public boolean removeBoard(String notice_num) {
		Connection con = getConnection();
		NoticeBoardDAO noticeBoardDAO = NoticeBoardDAO.getInstance();
		noticeBoardDAO.setConnection(con);
		
		int deleteCount = noticeBoardDAO.deleteBoard(notice_num);
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
