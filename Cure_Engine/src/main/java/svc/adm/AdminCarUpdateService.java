package svc.adm;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.CarDAO;
import vo.Car;

public class AdminCarUpdateService {

	public boolean updateCar(Car car) {
		Connection con = getConnection();
		CarDAO carDAO = CarDAO.getInstance();
		carDAO.setConnection(con);
		
		boolean isUpdateSuccess = false;
		
		int insertResult = carDAO.updateCar(car);
		
		if(insertResult > 0) {
			commit(con);
			isUpdateSuccess = true; //성공했으므로 true로 바꿔줌
		}else {
			rollback(con);
		}
		
		close(con);
		return isUpdateSuccess;
	}

}
