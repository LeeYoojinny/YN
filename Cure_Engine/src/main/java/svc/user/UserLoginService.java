package svc.user;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.UserDAO;
import vo.User;

public class UserLoginService {

	//로그인 시 아이디와 패스워드 검증
	public boolean login(User user) {
		Connection con = getConnection();//JdbcUtil. 생략 → 위에서 static으로 import 했으므로 생략가능
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		String loginId = userDAO.selectLoginId(user);
		
		boolean isLoginResult = false;
		
		System.out.println("dao에서 로그인 결과 가지고 옴");
		
		if(loginId != null) {
			isLoginResult = true;
			System.out.println("dao에서 성공? : "+isLoginResult);
		}
		
		//4. 자원해제
		close(con); //JdbcUtil. 생략 → 위에서 static으로 import 했으므로 생략가능
		
		return isLoginResult;

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
	
	

}
