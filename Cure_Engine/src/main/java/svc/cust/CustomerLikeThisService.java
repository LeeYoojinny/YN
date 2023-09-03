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

public class CustomerLikeThisService {

	public int carLikeThis(String car_id) {
		Connection con = getConnection();
		CarDAO carDAO = CarDAO.getInstance();
		carDAO.setConnection(con);

		int likeUp = carDAO.likeUpdate(car_id);
		
		System.out.println("car테이블에 좋아요 숫자 업 : "+likeUp);
		
		if(likeUp > 0 ) {
			commit(con);
		}else {
			rollback(con);
		}

		close(con);
		return likeUp;
	}

	public int wishInsert(String car_id, String car_image1, int car_price, String user_id) {
		Connection con = getConnection();
		WishlistDAO wishlistDAO = WishlistDAO.getInstance();
		wishlistDAO.setConnection(con);
		
		int wishResult = wishlistDAO.insertWish(car_id,car_image1,car_price,user_id);
		
		System.out.println("wish테이블에 insert성공? : "+wishResult);
		
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
