package svc.adm;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.CarDAO;
import dao.UserDAO;
import dao.WishlistDAO;

public class AdminDealerDeleteService {

	public int checkPw(String user_id, String user_pw) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		int checkUser = userDAO.checkPw(user_id,user_pw);
		
		close(con);
		return checkUser;
	}

	public int deleteDealer(String user_id) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		CarDAO carDAO = CarDAO.getInstance();
		carDAO.setConnection(con);
		
		WishlistDAO wishlistDAO = WishlistDAO.getInstance();
		wishlistDAO.setConnection(con);
		
		int dealerResult = userDAO.custDelete(user_id);
		int carResult = carDAO.deleteCar(user_id);
		int wishResult = wishlistDAO.update_delete();
		
		
		if(dealerResult > 0 && carResult > 0 && wishResult > 0) {			
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		return dealerResult;
	}

}
