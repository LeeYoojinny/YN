package svc.user;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.CarDAO;
import vo.Car;

public class UserCarDetailViewService {

	public Car getdetail(String car_id) {
		Connection con = getConnection();
		CarDAO carDAO = CarDAO.getInstance();
		carDAO.setConnection(con);
		
		Car carDetail = carDAO.selectDetail(car_id);
		
		close(con);
		return carDetail;
	}

}
