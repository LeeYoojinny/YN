package svc.adm;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CarDAO;
import vo.Car;

public class AdminMySaleCarService {

	public ArrayList<Car> getmySaleCarList(String user_id) {
		Connection con = getConnection();
		CarDAO carDAO = CarDAO.getInstance();
		carDAO.setConnection(con);
		
		ArrayList<Car> mySaleCarList = carDAO.selectmySaleCar(user_id);
		
		close(con);
		return mySaleCarList;

	}

}
