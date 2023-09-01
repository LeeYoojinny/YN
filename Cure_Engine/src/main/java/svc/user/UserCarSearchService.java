package svc.user;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CarDAO;
import vo.Car;

public class UserCarSearchService {

	public ArrayList<Car> searchResult(String car_brand, String car_color, String car_type, int car_distance) {
		Connection con = getConnection();
		CarDAO carDAO = CarDAO.getInstance();
		carDAO.setConnection(con);
		
		ArrayList<Car> searchCar = carDAO.selectSearchCar(car_brand,car_color,car_type,car_distance);
		
		close(con);
		return searchCar;
	}

}
