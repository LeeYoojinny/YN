package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.SHA256;
import vo.Car;
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

		/*---- 고객 회원가입 ----------------------------------------------------------------------------------------------*/
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
		
		/*---- 딜러 등록 ----------------------------------------------------------------------------------------------*/
		public int insertDealer(User user) {
			int insertDealerCount = 0;
			
			String sql = "insert into tbl_user(user_category,user_id,user_pw,user_name,user_birth,user_gender,"
					+ "user_phone,user_email,user_zipcode,user_address1,user_address2,use_YN)"
					+ " values(?,?,?,?,?,?,?,?,?,?,?,?)";
			
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
				pstmt.setString(12, user.getUse_YN());
				
				insertDealerCount = pstmt.executeUpdate();
				
			}catch(Exception e) {
				System.out.println("UserDAO 클래스의 insertDealer()에서 발생한 에러 : "+e);
			}finally {
				close(pstmt);
			}			
			return insertDealerCount;
		}

		
		/*----로그인-----------------------------------------------------------------------------------------------*/
		public ArrayList<String> selectLoginId(User user) {
			ArrayList<String> loginCheck = null;
			
			String sql = "select user_id, user_pw, user_category, use_YN  from tbl_user where user_id=? and user_pw=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user.getUser_id());
				pstmt.setString(2, user.getUser_pw());

				rs = pstmt.executeQuery();

				if(rs.next()) {
					loginCheck = new ArrayList<String>();
					loginCheck.add(rs.getString("user_category"));
					loginCheck.add(rs.getString("use_YN"));
				}
				
			}catch(Exception e) {
				System.out.println("UserDAO 클래스의 selectLoginId()에서 발생한 에러 : "+e);
			}finally {
				close(rs);
				close(pstmt);
			}
			
			return loginCheck;
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
					userInfo.setUser_birth(rs.getString("user_birth"));
					userInfo.setUser_gender(rs.getString("user_gender"));
					userInfo.setUser_phone(rs.getString("user_phone"));
					userInfo.setUser_email(rs.getString("user_email"));
					userInfo.setUser_zipcode(rs.getInt("user_zipcode"));
					userInfo.setUser_address1(rs.getString("user_address1"));
					userInfo.setUser_address2(rs.getString("user_address2"));
					userInfo.setUse_YN(rs.getString("use_YN"));
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

		/*------ 회원정보수정-기본정보 ----------------------------------------------------------------*/
		public int updateUser(User user) {
			int updateUserCount = 0;

			String sql = "update tbl_user set user_name=?, user_birth=?, user_gender=?, user_phone=?, user_email=?, "
					+ "user_zipcode=?, user_address1=? , user_address2=?"
					+ " where user_id=?";

			try {
				//1. DB연결 (위에서 setConnection으로 연결 해놨으므로 생략가능)
				pstmt = con.prepareStatement(sql);				
				pstmt.setString(1, user.getUser_name());
				pstmt.setString(2, user.getUser_birth());
				pstmt.setString(3, user.getUser_gender());
				pstmt.setString(4, user.getUser_phone());
				pstmt.setString(5, user.getUser_email());
				pstmt.setInt(6, user.getUser_zipcode());
				pstmt.setString(7, user.getUser_address1());
				pstmt.setString(8, user.getUser_address2());
				pstmt.setString(9, user.getUser_id());
				
				updateUserCount = pstmt.executeUpdate();
				
			}catch(Exception e) {
				System.out.println("UserDAO 클래스의 updateUser()에서 발생한 에러 : "+e);
			}finally {
				close(pstmt);
			}
			
			return updateUserCount;
		}

		/*------ 딜러등록 시 아이디 자동생성 ----------------------------------------------------------------*/
		public String getDealerId() {
			String dealer_id = "";
			
			String sql = "select max(user_id) from tbl_user where user_category='dealer'";
			
			try {
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					dealer_id = rs.getString(1);
				}
				
			}catch(Exception e) {
				System.out.println("UserDAO 클래스의 getDealerId()에서 발생한 에러 : "+e);
			}finally {
				close(rs);
				close(pstmt);
			}
			
			return dealer_id;
		}

		/*------ 딜러 승인 요청건 가져오기 ----------------------------------------------------------------*/
		public ArrayList<User> disaprv_DealerList(int page, int limit) {
			ArrayList<User> disapprove_list = null;
			
			String sql = "select * from tbl_user where user_category='dealer' and use_YN='N'  limit ?,5";
			int startrow=(page-1)*5; 
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startrow);
				
				rs = pstmt.executeQuery();
								
				if(rs.next()) {
					disapprove_list = new ArrayList<User>();
					do {
						disapprove_list.add(new User(
								rs.getString("user_category"),
								rs.getString("user_id"),
								rs.getString("user_pw"),
								rs.getString("user_name"),
								rs.getString("user_birth"),
								rs.getString("user_gender"),
								rs.getString("user_phone"),
								rs.getString("user_email"),
								rs.getInt("user_zipcode"),
								rs.getString("user_address1"),
								rs.getString("user_address2"),
								rs.getString("use_YN")
								));
					}while(rs.next());
				}
			}catch(Exception e) {
				System.out.println("UserDAO 클래스의 disaprv_DealerList()에서 발생한 에러 : "+e);
			}finally {
				close(rs);
				close(pstmt);
			}
			
			return disapprove_list;
		}


		public int getdisaprv_listCount() {
			int listCount = 0;
			
			String sql = "select count(*) from tbl_user where user_category='dealer' and use_YN='N'";
			
			try {
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					listCount = rs.getInt(1);
				}
				
			}catch(Exception e) {
				System.out.println("UserDAO 클래스의 getDealerId()에서 발생한 에러 : "+e);
			}finally {
				close(rs);
				close(pstmt);
			}
			return listCount;
		}


		public int approveDealer(String[] dealer_ids) {
			int result = 0;
			
			String where_user_id = "";
			for(int i=0; i<dealer_ids.length; i++) {
				if(i == 0) {
					where_user_id += " (user_id='"+dealer_ids[i]+"'";
				}else {
					where_user_id += " or user_id='"+dealer_ids[i]+"'";
				}
			}
			where_user_id += ")";
			
			String sql = "update tbl_user set use_YN='Y' where"+where_user_id;
			try {
				pstmt = con.prepareStatement(sql);
				result = pstmt.executeUpdate();

			}catch(Exception e) {
				System.out.println("UserDAO 클래스의 approveDealer()에서 발생한 에러 : "+e);
			}finally {
				close(pstmt);
			}
			
			return result;
		}


		
		
		
		
		
		
		
}
