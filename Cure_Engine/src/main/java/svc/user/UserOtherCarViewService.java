package svc.user;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CarDAO;
import dao.CodeDAO;
import vo.Car;
import vo.Code;

public class UserOtherCarViewService {
	public int getListCount(String dealer_id) {
		Connection con = getConnection();
		CarDAO carDAO = CarDAO.getInstance();
		carDAO.setConnection(con);
		
		int count = carDAO.getSaleCount(dealer_id);
		
		close(con);
		return count;
	}

	public ArrayList<Car> getOtherCarList(String dealer_id, int page, int limit) {
		Connection con = getConnection();
		CarDAO carDAO = CarDAO.getInstance();
		carDAO.setConnection(con);
		
		ArrayList<Car> otherCarList = carDAO.selectmySaleCar(dealer_id,page,limit);
		
		close(con);
		return otherCarList;
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
