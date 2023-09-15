package svc.adm;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CarDAO;
import dao.CodeDAO;
import vo.Car;
import vo.Code;

public class AdminMySaleCarService {
	public int getListCount(String user_id) {
		Connection con = getConnection();
		CarDAO carDAO = CarDAO.getInstance();
		carDAO.setConnection(con);
		
		int count = carDAO.getSaleCount(user_id);
		
		close(con);
		return count;
	}


	public ArrayList<Car> getmySaleCarList(String user_id, int page, int limit) {
		Connection con = getConnection();
		CarDAO carDAO = CarDAO.getInstance();
		carDAO.setConnection(con);
		
		ArrayList<Car> mySaleCarList = carDAO.selectmySaleCar(user_id, page, limit);
		
		close(con);
		return mySaleCarList;

	}

	public ArrayList<Code> getAllCode() {
		Connection con = getConnection();
		CodeDAO codeDAO = CodeDAO.getInstance();
		codeDAO.setConnection(con);
		
		ArrayList<Code> allCode = codeDAO.selectAllCode();
		
		close(con);
		return allCode;
	}


}
