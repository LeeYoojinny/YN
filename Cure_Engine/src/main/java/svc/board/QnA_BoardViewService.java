package svc.board;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import dao.QnABoardDAO;
import dao.CarDAO;
import dao.CodeDAO;
import vo.QnABoard;
import vo.Code;

public class QnA_BoardViewService {

	public QnABoard getBoardView(String qna_num) {
		Connection con = getConnection();
		QnABoardDAO boardDAO = QnABoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int boardHitCount = boardDAO.updateHit(qna_num);
		
		if(boardHitCount > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		QnABoard board = boardDAO.selectBoard(qna_num);
		close(con);
		return board;
	}
	
	public ArrayList<Code> getAllCode() {
		Connection con = getConnection();
		CodeDAO codeDAO = CodeDAO.getInstance();
		codeDAO.setConnection(con);
		
		ArrayList<Code> allCode = codeDAO.selectAllCode();
		
		close(con);
		return allCode;
	}

	public String getDealer_id(String car_id) {
		Connection con = getConnection();
		CarDAO carDAO = CarDAO.getInstance();
		carDAO.setConnection(con);
		
		String dealer_id = carDAO.getDealer_id(car_id);
		
		close(con);
		return dealer_id;
	}


}
