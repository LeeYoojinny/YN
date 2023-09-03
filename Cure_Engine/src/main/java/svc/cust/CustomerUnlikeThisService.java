package svc.cust;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CarDAO;
import dao.WishlistDAO;
import vo.Wishlist;

public class CustomerUnlikeThisService {

	public int carUnlikeThis(String car_id) {
		Connection con = getConnection();
		CarDAO carDAO = CarDAO.getInstance();
		carDAO.setConnection(con);
		
		int likeDown = carDAO.likeDown(car_id);
		
		if(likeDown > 0 ) {
			commit(con);
		}else {
			rollback(con);
		}

		close(con);
		return likeDown;
	}

	public int wishDelete(String car_id, String user_id) {
		Connection con = getConnection();
		WishlistDAO wishlistDAO = WishlistDAO.getInstance();
		wishlistDAO.setConnection(con);
		
		int wishResult = wishlistDAO.deleteWish(car_id,user_id);
		
		if(wishResult > 0 ) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return wishResult;

	}

	public ArrayList<Wishlist> getWishInfo(String user_id) {
		Connection con = getConnection();
		WishlistDAO wishlistDAO = WishlistDAO.getInstance();
		wishlistDAO.setConnection(con);
		
		ArrayList<Wishlist> userWish = wishlistDAO.getWishList(user_id);
		
		close(con);
		return userWish;
	}

}
