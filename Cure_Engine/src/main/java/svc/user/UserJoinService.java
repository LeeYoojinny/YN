package svc.user;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.UserDAO;
import vo.Coupon;
import vo.User;

public class UserJoinService {

	public boolean userJoin(User user) {
		Connection con = getConnection();//JdbcUtil. 생략 → 위에서 static으로 import 했으므로 생략가능
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		int insertUserCount = userDAO.insertUser(user);
		
		boolean isUserJoinResult = false;
		
		if(insertUserCount > 0 ) {
			isUserJoinResult = true;
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		return isUserJoinResult;

	}

	public boolean userJoinCoupon(Coupon joinCoupon) {
		Connection con = getConnection();//JdbcUtil. 생략 → 위에서 static으로 import 했으므로 생략가능
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		int result = userDAO.userJoinCoupon(joinCoupon);
		boolean isCouponSuccess = false;
		
		if(result > 0 ) {
			isCouponSuccess = true;
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);		
		return isCouponSuccess;
	}

}
