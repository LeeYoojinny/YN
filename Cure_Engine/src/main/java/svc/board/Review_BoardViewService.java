package svc.board;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.ReviewBoardDAO;
import vo.ReviewBoard;

public class Review_BoardViewService {

	public ReviewBoard getBoardView(String review_num) {
		Connection con = getConnection();
		ReviewBoardDAO revieweBoardDAO = ReviewBoardDAO.getInstance();
		revieweBoardDAO.setConnection(con);
		
		//조회수 증가
		int boardHitCount = revieweBoardDAO.updateHit(review_num);
		
		if(boardHitCount > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		ReviewBoard board = revieweBoardDAO.selectReview(review_num);
		
		close(con);
		return board;
	}

}
