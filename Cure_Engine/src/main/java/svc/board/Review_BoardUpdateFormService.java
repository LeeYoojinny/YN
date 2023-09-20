package svc.board;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;


import java.sql.Connection;

import dao.ReviewBoardDAO;
import vo.ReviewBoard;

public class Review_BoardUpdateFormService {

	public ReviewBoard getBoardInfo(String review_num) {
		Connection con = getConnection();
		ReviewBoardDAO revieweBoardDAO = ReviewBoardDAO.getInstance();
		revieweBoardDAO.setConnection(con);
		
		ReviewBoard board = revieweBoardDAO.selectReview(review_num);
		
		close(con);
		return board;

	}

}
