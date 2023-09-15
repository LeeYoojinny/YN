package svc.cust;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CarDAO;
import dao.CodeDAO;
import dao.WishlistDAO;
import vo.Car;
import vo.Code;
import vo.Wishlist;

public class CustomerWishlistService {
	public int getListCount(String user_id) {
		Connection con = getConnection();
		WishlistDAO wishlistDAO = WishlistDAO.getInstance();
		wishlistDAO.setConnection(con);
		
		int count = wishlistDAO.getWishCount(user_id);
		
		close(con);
		return count;
	}
	
	public ArrayList<Wishlist> getMyWishlist(String user_id) {
		Connection con = getConnection();
		WishlistDAO wishlistDAO = WishlistDAO.getInstance();
		wishlistDAO.setConnection(con);
		
		ArrayList<Wishlist> myWish = wishlistDAO.getWishList(user_id);
		
		close(con);
		return myWish;
	}


	public ArrayList<Car> getWishCar(String[] all_car_id, int page, int limit) {
		Connection con = getConnection();
		CarDAO carDAO = CarDAO.getInstance();
		carDAO.setConnection(con);
		
		ArrayList<Car> wishCar = carDAO.selectmyWishCar(all_car_id,page,limit);
		
		close(con);
		return wishCar;
	}

	public ArrayList<Code> getAllCode() {
		Connection con = getConnection();
		CodeDAO codeDAO = CodeDAO.getInstance();
		codeDAO.setConnection(con);
		
		ArrayList<Code> allCode = codeDAO.selectAllCode();
		
		close(con);
		return allCode;
	}


	
	
}
