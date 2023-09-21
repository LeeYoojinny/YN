package svc.adm;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CarDAO;
import dao.CodeDAO;
import vo.Car;
import vo.Code;

public class AdminRemoveCarListService {

	public int getListCount() {
		Connection con = getConnection();
		CarDAO carDAO = CarDAO.getInstance();
		carDAO.setConnection(con);
		
		int count = carDAO.getdeleteCarCount();
		
		close(con);
		return count;
	}

	public ArrayList<Car> getRemoveCarList(int page, int limit) {
		Connection con = getConnection();
		CarDAO carDAO = CarDAO.getInstance();
		carDAO.setConnection(con);
		
		ArrayList<Car> removeCarList = carDAO.selectRemoveCar(page,limit);
		
		close(con);
		return removeCarList;
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
