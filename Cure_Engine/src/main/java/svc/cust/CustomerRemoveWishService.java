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

public class CustomerRemoveWishService {

	public int removeWish_wish(String[] car_ids, String user_id) {
		Connection con = getConnection();
		WishlistDAO wishlistDAO = WishlistDAO.getInstance();
		wishlistDAO.setConnection(con);
		
		int result = wishlistDAO.removeWish(car_ids,user_id);
		
		if(result > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		
		return result;
	}
	
	public int removeLike_car(String[] car_ids) {
		Connection con = getConnection();
		CarDAO carDAO = CarDAO.getInstance();
		carDAO.setConnection(con);

		int likeDown = carDAO.likeManyDown(car_ids);
		
		System.out.println("car테이블에 좋아요 여러개 다운 : "+likeDown);
		
		if(likeDown > 0 ) {
			commit(con);
		}else {
			rollback(con);
		}

		close(con);
		return likeDown;
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
