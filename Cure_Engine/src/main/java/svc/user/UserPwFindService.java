package svc.user;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.UserDAO;
import vo.User;

public class UserPwFindService {

	public boolean findPw(User findPwInfo) {
		Connection con = getConnection();//JdbcUtil. 생략 → 위에서 static으로 import 했으므로 생략가능
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		boolean userYN = false;
		int userOk = userDAO.findPw(findPwInfo);
		
		if(userOk > 0) {
			userYN = true;
			System.out.println("입력된 값 받아서 사용자 찾음 : "+findPwInfo.getUser_id()+", "+findPwInfo.getUser_email());
		}
		
		close(con);
		return userYN;
	}

	public boolean updateTmpPw(String user_id, String random_password) {
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		boolean istmpPwUpdateSuccess = false;
		int updateTmpPw = userDAO.updateTmpPw(user_id,random_password);
		
		if(updateTmpPw > 0) {
			istmpPwUpdateSuccess = true;	
			System.out.println("임시비밀번호 update성공 : "+user_id+", "+random_password);
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		return istmpPwUpdateSuccess;
	}

}
