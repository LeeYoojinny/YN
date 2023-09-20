package svc.board;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.CarDAO;
import dao.ReviewBoardDAO;
import vo.Car;
import vo.ReviewBoard;

public class Review_BoardListService {

	public int getListCount() {
		Connection con = getConnection();
		ReviewBoardDAO revieweBoardDAO = ReviewBoardDAO.getInstance();
		revieweBoardDAO.setConnection(con);
		
		int listCount = 0;
		listCount = revieweBoardDAO.selectListCount();
		
		close(con);
		return listCount;
	}

	public ArrayList<ReviewBoard> selectBoardList(int page, int limit) {
		Connection con = getConnection();
		ReviewBoardDAO revieweBoardDAO = ReviewBoardDAO.getInstance();
		revieweBoardDAO.setConnection(con);
		
		ArrayList<ReviewBoard> boardList = revieweBoardDAO.selectBoardList(page, limit);
				
		close(con);		
		return boardList;
	}

	public ArrayList<Car> selectAllCarInfo(List<String> car_id_List) {
		Connection con = getConnection();
		CarDAO carDAO = CarDAO.getInstance();
		carDAO.setConnection(con);
		
		ArrayList<Car> carList = carDAO.selectAllCarInfo(car_id_List);
		
		close(con);	
		return carList;
	}

}
