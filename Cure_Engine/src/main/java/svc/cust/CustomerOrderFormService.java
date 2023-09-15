package svc.cust;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CarDAO;
import dao.CodeDAO;
import dao.UserDAO;
import vo.Car;
import vo.Code;
import vo.User;

public class CustomerOrderFormService {

	public ArrayList<Code> getAllCode() {
		Connection con = getConnection();
		CodeDAO codeDAO = CodeDAO.getInstance();
		codeDAO.setConnection(con);
		
		ArrayList<Code> allCode = codeDAO.selectAllCode();
		
		close(con);
		return allCode;
	}

	public User getUserInfo(String user_id) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		User userInfo = userDAO.selectUserInfo(user_id);
		
		close(con);
		return userInfo;
	}

	public Car getCarInfo(String car_id) {
		Connection con = getConnection();
		CarDAO carDAO = CarDAO.getInstance();
		carDAO.setConnection(con);
		
		Car carInfo = carDAO.selectDetail(car_id);
		
		close(con);
		return carInfo;
	}

}
