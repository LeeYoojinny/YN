package svc.adm;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.CarDAO;
import dao.UserDAO;

public class AdminDealerRejoinService {

	public boolean dealerRejoin(String user_id) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		CarDAO carDAO = CarDAO.getInstance();
		carDAO.setConnection(con);
		
		boolean rejoinResult = false;
		int result = userDAO.dealerRejoin(user_id);
		int myCar = carDAO.getMyCarCount(user_id);
		
		
		if(myCar > 0) {
			int carResult = carDAO.reSaleCar(user_id);
			System.out.println("딜러의 자동차 복구:" + carResult);
			if(result > 0 && carResult > 0 ) {
				commit(con);
				rejoinResult = true;
			}else {
				rollback(con);
			}
		}else {
			if(result > 0 ) {
				commit(con);
				rejoinResult = true;
			}else {
				rollback(con);
			}
		}	

		close(con);
		return rejoinResult;
	}

}
