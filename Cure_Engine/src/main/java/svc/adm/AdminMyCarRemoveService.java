package svc.adm;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.CarDAO;
import dao.WishlistDAO;

public class AdminMyCarRemoveService {

	public int removeCarByAdmin(String[] car_ids) {
		Connection con = getConnection();
		CarDAO carDAO = CarDAO.getInstance();
		carDAO.setConnection(con);
		
		int removeResult = carDAO.removeCarByAdmin(car_ids);
		
		if(removeResult > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		return removeResult;
	}


	public int removeMyCar_wish() {
		Connection con = getConnection();
		WishlistDAO wishlistDAO = WishlistDAO.getInstance();
		wishlistDAO.setConnection(con);
		
		int wishResult = wishlistDAO.update_delete();
		
		if(wishResult > 0 ) {
			commit(con);
		}
		if(wishResult < 0 ) {
			rollback(con);
		}
		
		close(con);		
		return wishResult;
	}



	
}
