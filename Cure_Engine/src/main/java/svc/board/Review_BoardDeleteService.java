package svc.board;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.ReviewBoardDAO;

public class Review_BoardDeleteService {

	public boolean removeBoard(String review_num) {
		Connection con = getConnection();
		ReviewBoardDAO revieweBoardDAO = ReviewBoardDAO.getInstance();
		revieweBoardDAO.setConnection(con);
		
		int deleteCount = revieweBoardDAO.deleteBoard(review_num);
		boolean isRemoveSuccess = false;
		
		if(deleteCount > 0) {
			commit(con);
			isRemoveSuccess = true;
		}else {
			rollback(con);
		}
		close(con);
		return isRemoveSuccess;

	}

}
