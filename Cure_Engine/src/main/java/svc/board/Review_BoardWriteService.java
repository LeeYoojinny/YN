package svc.board;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.ReviewBoardDAO;
import vo.ReviewBoard;

public class Review_BoardWriteService {

	public boolean writeReviewBoard(ReviewBoard review) {
		Connection con = getConnection();
		ReviewBoardDAO revieweBoardDAO = ReviewBoardDAO.getInstance();
		revieweBoardDAO.setConnection(con);
		
		int insertCount = revieweBoardDAO.insertBoard(review);
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
