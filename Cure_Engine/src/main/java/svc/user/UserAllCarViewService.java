package svc.user;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CarDAO;
import vo.Car;

public class UserAllCarViewService {

	public ArrayList<Car> getAllCarList() {
		Connection con = getConnection();
		CarDAO carDAO = CarDAO.getInstance();
		carDAO.setConnection(con);
		
		ArrayList<Car> allCarList = carDAO.selectAllCarInfo();
		
		close(con);
		return allCarList;
	}

}
