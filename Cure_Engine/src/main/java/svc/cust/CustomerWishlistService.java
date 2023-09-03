package svc.cust;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CarDAO;
import vo.Car;

public class CustomerWishlistService {

	public ArrayList<Car> getWishCar(String[] all_car_id) {
		Connection con = getConnection();
		CarDAO carDAO = CarDAO.getInstance();
		carDAO.setConnection(con);
		
		ArrayList<Car> wishCar = carDAO.selectmyWishCar(all_car_id);
		
		close(con);
		return wishCar;
	}

}
