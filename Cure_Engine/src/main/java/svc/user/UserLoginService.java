package svc.user;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import vo.Wishlist;
import dao.UserDAO;
import dao.WishlistDAO;
import vo.User;

public class UserLoginService {

	//로그인 시 아이디와 패스워드 검증
	public String login(User user) {
		Connection con = getConnection();//JdbcUtil. 생략 → 위에서 static으로 import 했으므로 생략가능
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		String use_YN = userDAO.selectLoginId(user);
		
		System.out.println("dao에서 로그인 결과 가지고 옴");
		
		//4. 자원해제
		close(con);
		return use_YN;

	}
	
	//고객정보를 담기위한 메소드
	public User getUserInfo(String user_id) {
		Connection con = getConnection();//JdbcUtil. 생략 → 위에서 static으로 import 했으므로 생략가능
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		User userInfo = userDAO.selectUserInfo(user_id);
		
		close(con);
		return userInfo;
		
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
