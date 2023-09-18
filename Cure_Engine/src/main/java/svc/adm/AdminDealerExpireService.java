package svc.adm;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.CarDAO;
import dao.UserDAO;

public class AdminDealerExpireService {

	public boolean dealerExpire(String user_id) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		CarDAO carDAO = CarDAO.getInstance();
		carDAO.setConnection(con);
		
		boolean deleteResult = false;
		int result = userDAO.dealerDelete(user_id);
		System.out.println("딜러 삭제 :" + result);
		int myCar = carDAO.getSaleCount(user_id);
		
		if(myCar > 0) {
			int carResult = carDAO.deleteCar(user_id);
			System.out.println("딜러의 자동차 삭제 :" + carResult);
			if(result > 0 && carResult > 0 ) {
				commit(con);
				deleteResult = true;
			}else {
				rollback(con);
			}
		}else {
			if(result > 0 ) {
				commit(con);
				deleteResult = true;
			}else {
				rollback(con);
			}
		}	
		
		close(con);
		return deleteResult;
	}

}
