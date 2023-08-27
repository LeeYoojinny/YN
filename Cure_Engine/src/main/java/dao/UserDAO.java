package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.User;

public class UserDAO {
		private Connection con = null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;
		
		private UserDAO() {}
		
		private static UserDAO userDAO;
		
		public static UserDAO getInstance() {
			if(userDAO == null) {//DogDAO 객체가 없으면
				userDAO = new UserDAO();
			}
			return userDAO; //기존 DogDAO객체가 있으면 주소를 리턴
		}
		
		
		/**
		 * 1.DB연결(Service 통해) → 2.sql 실행 → 3. 결과처리 → 4. 연결해제
		 * 
		 */
		
		//DB연결(Service)
		public void setConnection (Connection con){
			this.con = con;
		}


		public int insertUser(User user) {
			int insertUserCount = 0;
			
			String sql = "insert into tbl_user(user_category,user_id,user_pw,user_name,user_birth,user_gender,"
					+ "user_phone,user_email,user_zipcode,user_address1,user_address2)"
					+ " values(?,?,?,?,?,?,?,?,?,?,?)";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user.getUser_category());
				pstmt.setString(2, user.getUser_id());
				pstmt.setString(3, user.getUser_pw());
				pstmt.setString(4, user.getUser_name());
				pstmt.setString(5, user.getUser_birth());
				pstmt.setString(6, user.getUser_gender());
				pstmt.setString(7, user.getUser_phone());
				pstmt.setString(8, user.getUser_email());
				pstmt.setInt(9, user.getUser_zipcode());
				pstmt.setString(10, user.getUser_address1());
				pstmt.setString(11, user.getUser_address2());
				
				insertUserCount = pstmt.executeUpdate();
				
			}catch(Exception e) {
				System.out.println("UserDAO 클래스의 insertUser()에서 발생한 에러 : "+e);
			}finally {
				//close(rs);
				close(pstmt);
				//Connection 자원해제는 service에서 해줬기 때문에 여기서 하지 않음
			}
			return insertUserCount;
		}
		
		
		
		
		
		
}
