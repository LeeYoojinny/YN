package svc.board;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.ReviewBoardDAO;
import vo.ReviewBoard;

public class Review_BoardUpdateService {

	public boolean updateReview(ReviewBoard review, String review_num) {
		Connection con = getConnection();
		ReviewBoardDAO revieweBoardDAO = ReviewBoardDAO.getInstance();
		revieweBoardDAO.setConnection(con);
		
		int updateCount = revieweBoardDAO.updateReview(review,review_num);
		boolean isUpdateSuccess = false;
		
		if(updateCount > 0) {
			commit(con);
			isUpdateSuccess = true;
		}else {
			rollback(con);
		}
		
		close(con);
		return isUpdateSuccess;
		
	}

}
