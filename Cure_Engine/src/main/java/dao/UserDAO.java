package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.SHA256;
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

		/*----회원가입-----------------------------------------------------------------------------------------------*/
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

		/*----로그인-----------------------------------------------------------------------------------------------*/
		public String selectLoginId(User user) {
			String use_YN = null;
			
			String sql = "select user_id, user_pw, use_YN  from tbl_user where user_id=? and user_pw=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user.getUser_id());
				pstmt.setString(2, user.getUser_pw());

				rs = pstmt.executeQuery();

				if(rs.next()) {
					use_YN = rs.getString(3);
				}
				
			}catch(Exception e) {
				System.out.println("UserDAO 클래스의 selectLoginId()에서 발생한 에러 : "+e);
			}finally {
				close(rs);
				close(pstmt);
				//Connection 자원해제는 service에서 해줬기 때문에 여기서 하지 않음
			}
			
			return use_YN;
		}

		/*----로그인 시 세션에 담을 고객정보------------------------------------------------------------------------------*/
		public User selectUserInfo(String user_id) {
			User userInfo = null;
			
			String sql = "select * from tbl_user where user_id=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user_id);

				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					userInfo = new User();
					userInfo.setUser_category(rs.getString("user_category"));
					userInfo.setUser_id(rs.getString("user_id"));
					userInfo.setUser_name(rs.getString("user_name"));
					userInfo.setUser_phone(rs.getString("user_phone"));
				}
				
			}catch(Exception e) {
				System.out.println("UserDAO 클래스의 selectLoginId()에서 발생한 에러 : "+e);
			}finally {
				close(rs);
				close(pstmt);
			}
			
			return userInfo;
		}

		/*---- 아이디 찾기 -----------------------------------------------------------------------------------------------*/
		public String findId(User findIdInfo) {
			String getId = null;
			
			String sql = "select user_id from tbl_user where user_name=? and user_birth=? and user_email=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, findIdInfo.getUser_name());
				pstmt.setString(2, findIdInfo.getUser_birth());
				pstmt.setString(3, findIdInfo.getUser_email());

				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					getId = rs.getString(1);
				}
				
			}catch(Exception e) {
				System.out.println("UserDAO 클래스의 findId()에서 발생한 에러 : "+e);
			}finally {
				close(rs);
				close(pstmt);
			}
			
			return getId;
		}

		/*---- 비밀번호 찾기 -----------------------------------------------------------------------------------------------*/
		public int findPw(User findPwInfo) {
			int userYN = 0; 
			
			String sql = "select count(user_pw) from tbl_user where user_id=? and user_birth=? and user_email=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, findPwInfo.getUser_id());
				pstmt.setString(2, findPwInfo.getUser_birth());
				pstmt.setString(3, findPwInfo.getUser_email());
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					userYN = rs.getInt(1);
				}

			}catch(Exception e) {
				System.out.println("UserDAO 클래스의 findPw()에서 발생한 에러 : "+e);
			}finally {
				close(rs);
				close(pstmt);
			}
			
			
			return userYN;
		}

		/*---- 임시비밀번호 업데이트 -----------------------------------------------------------------------------------------------*/
		public int updateTmpPw(String user_id, String random_password) {
			int updateTmpPw = 0;

			String sql = "update tbl_user set user_pw=? where user_id=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, SHA256.encodeSHA256(random_password));
				pstmt.setString(2, user_id);
				updateTmpPw = pstmt.executeUpdate();
				
			}catch(Exception e) {
				System.out.println("UserDAO 클래스의 updateTmpPw()에서 발생한 에러 : "+e);
			}finally {
				close(pstmt);
			}
			return updateTmpPw;
		}

		/*---- 차량상세보기 딜러정보 -----------------------------------------------------------------------------------------------*/
		public User getDealerInfo(String dealer_id) {
			User dealer = null;
			
			String sql = "select * from tbl_user where user_id=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, dealer_id);

				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					dealer = new User();
					dealer.setUser_name(rs.getString("user_name"));
					dealer.setUser_phone(rs.getString("user_phone"));
					dealer.setUser_email(rs.getString("user_email"));
					dealer.setUser_gender(rs.getString("user_gender"));
				}
				
			}catch(Exception e) {
				System.out.println("UserDAO 클래스의 getDealerInfo()에서 발생한 에러 : "+e);
			}finally {
				close(rs);
				close(pstmt);
			}
			return dealer;
		}


		
		
		
		
		
		
}
