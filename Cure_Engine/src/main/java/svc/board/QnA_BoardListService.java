package svc.board;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.QnABoardDAO;
import dao.CarDAO;
import dao.CodeDAO;
import vo.QnABoard;
import vo.Car;
import vo.Code;

public class QnA_BoardListService {
	
	public int getListCount() {
		
		Connection con = getConnection();
		QnABoardDAO boardDAO = QnABoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int listCount = 0;
		listCount = boardDAO.selectListCount();
		
		close(con);
		return listCount;
	}
	
	public ArrayList<QnABoard> selectBoardList(int page, int limit) {
		
		Connection con = getConnection();
		QnABoardDAO boardDAO = QnABoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		ArrayList<QnABoard> boardList = boardDAO.selectBoardList(page,limit);

		close(con);		
		return boardList;
	}

	public ArrayList<Code> getAllCode() {
		Connection con = getConnection();
		CodeDAO codeDAO = CodeDAO.getInstance();
		codeDAO.setConnection(con);
		
		ArrayList<Code> allCode = codeDAO.selectAllCode();
		
		close(con);
		return allCode;
	}

	public ArrayList<Car> getAllCarList() {
		Connection con = getConnection();
		CarDAO carDAO = CarDAO.getInstance();
		carDAO.setConnection(con);
		
		ArrayList<Car> allCarList = carDAO.selectAllCarInfo();
		
		close(con);
		return allCarList;
	}

}
