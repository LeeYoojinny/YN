package svc.user;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.CarDAO;
import dao.UserDAO;
import vo.Car;
import vo.User;

public class UserCarDetailViewService {

	public Car getdetail(String car_id) {
		Connection con = getConnection();
		CarDAO carDAO = CarDAO.getInstance();
		carDAO.setConnection(con);
		
		Car carDetail = carDAO.selectDetail(car_id);
		
		close(con);
		return carDetail;
	}

	public User getDealerInfo(String dealer_id) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		User dealer = userDAO.getDealerInfo(dealer_id);
		
		close(con);
		return dealer;
	}

}
