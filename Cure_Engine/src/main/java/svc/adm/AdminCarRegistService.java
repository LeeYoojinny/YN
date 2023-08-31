package svc.adm;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.CarDAO;
import vo.Car;

public class AdminCarRegistService {

	public boolean registCar(Car car) {
		Connection con = getConnection();
		CarDAO carDAO = CarDAO.getInstance();
		carDAO.setConnection(con);
		
		boolean isRegistSuccess = false;
		
		int insertResult = carDAO.insertCar(car);
		
		if(insertResult > 0) {
			commit(con);
			isRegistSuccess = true; //성공했으므로 true로 바꿔줌
		}else {
			rollback(con);
		}
		
		close(con);
		return isRegistSuccess;
	}

}
