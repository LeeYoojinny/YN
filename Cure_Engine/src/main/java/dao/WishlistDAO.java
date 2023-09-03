package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Wishlist;

public class WishlistDAO {
		private Connection con = null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;
		
		private WishlistDAO() {}
		
		private static WishlistDAO wishlistDAO;
		
		public static WishlistDAO getInstance() {
			if(wishlistDAO == null) {//DogDAO 객체가 없으면
				wishlistDAO = new WishlistDAO();
			}
			return wishlistDAO; //기존 DogDAO객체가 있으면 주소를 리턴
		}
		
		
		/**
		 * 1.DB연결(Service 통해) → 2.sql 실행 → 3. 결과처리 → 4. 연결해제
		 * 
		 */
		
		//DB연결(Service)
		public void setConnection (Connection con){
			this.con = con;
		}

		
		/*---- 관심상품 등록하기 -------------------------------------------------------------------------------------------*/
		public int insertWish(String car_id, String car_image1, int car_price, String user_id) {
			int insertResult = 0;
			
			String sql = "insert into tbl_wishlist values(?,?,?,?)";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, car_id);
				pstmt.setString(2, car_image1);
				pstmt.setInt(3, car_price);
				pstmt.setString(4, user_id);
				
				System.out.println("관심상품 등록sql : " + sql);
				
				insertResult = pstmt.executeUpdate();
				System.out.println("관심상품 등록성공");
			}catch(Exception e) {
				System.out.println("wishListDAO 클래스의 insertWish()에서 발생한 에러 : "+e);
			}finally {
				close(pstmt);
			}
			return insertResult;
		}
		
		/*---- 관심상품 삭제하기 -------------------------------------------------------------------------------------------*/
		public int deleteWish(String car_id, String user_id) {
			int deleteResult = 0;
			
			String sql = "delete from tbl_wishlist where car_id=? and user_id=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, car_id);
				pstmt.setString(2, user_id);
				
				deleteResult = pstmt.executeUpdate();

			}catch(Exception e) {
				System.out.println("wishListDAO 클래스의 deleteWish()에서 발생한 에러 : "+e);
			}finally {
				close(pstmt);
			}
			return deleteResult;
		}
		
		
		/*---- 로그인 시 관심상품 목록 가져오기 --------------------------------------------------------*/
		public ArrayList<Wishlist> getWishList(String user_id) {
			ArrayList<Wishlist> userWish = null;
			
			String sql = "select * from tbl_wishlist where user_id=?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user_id);

				rs = pstmt.executeQuery();
				
				/*
				rs.last();				
				System.out.println("rs count : " + rs.getRow());
				rs.beforeFirst();				
				*/
				
				if(rs.next()) {
					userWish = new ArrayList<Wishlist>();
					do {
						Wishlist wish = new Wishlist();

						wish.setCar_id(rs.getString("car_id"));
						wish.setCar_image(rs.getString("car_image"));
						wish.setCar_price(rs.getInt("car_price"));
						wish.setUser_id(rs.getString("user_id"));
						userWish.add(wish);
					}while(rs.next());
				}
			}catch(Exception e) {
				System.out.println("WishlistDAO 클래스의 getWishList()에서 발생한 에러 : "+e);
			}finally {
				close(rs);
				close(pstmt);
			}
			return userWish;
		}


		

		
}
