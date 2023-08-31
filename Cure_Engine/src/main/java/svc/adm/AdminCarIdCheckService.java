package svc.adm;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.CarDAO;
import vo.Car;

public class AdminCarIdCheckService {

	public boolean idCheckResult(String inputCarId) {
		Connection con = getConnection();
		CarDAO carDAO = CarDAO.getInstance();
		carDAO.setConnection(con);
		
		Car isUsedCarId = carDAO.selectCarInfo(inputCarId);
		
		boolean idCheckResult = false;
		
		if(isUsedCarId == null ) {
			idCheckResult = true;
		}
		
		close(con);
		return idCheckResult;
	}

}
