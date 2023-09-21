package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vo.ReviewBoard;

public class ReviewBoardDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private ReviewBoardDAO () {}
	
	private static ReviewBoardDAO revieweBoardDAO;
	
	public static ReviewBoardDAO getInstance() {
		if (revieweBoardDAO == null) {
			revieweBoardDAO = new ReviewBoardDAO();
		}

		return revieweBoardDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	public int insertBoard(ReviewBoard review) {
		int insertCount = 0;
		
		String sql = "insert into tbl_review(car_id,user_id,ordernum,review_title,review_content,"
				+ "review_file1,review_file1_origin,review_file2,review_file2_origin,review_file3,"
				+ "review_file3_origin,review_num) values(?,?,?,?,?,?,?,?,?,?,?,"
				+ "CONCAT('REV', LPAD(MAX(CAST(SUBSTRING(review_num, 4) AS UNSIGNED)) + 1, 5, '0')))";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, review.getCar_id());
			pstmt.setString(2, review.getUser_id());
			pstmt.setString(3, review.getOrdernum());
			pstmt.setString(4, review.getReview_title());
			pstmt.setString(5, review.getReview_content());			
			pstmt.setString(6, review.getReview_file1());
			pstmt.setString(7, review.getReview_file1_origin());
			pstmt.setString(8, review.getReview_file2());
			pstmt.setString(9, review.getReview_file2_origin());
			pstmt.setString(10, review.getReview_file3());
			pstmt.setString(11, review.getReview_file3_origin());
						
			insertCount = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("ReviewBoardDAO의 insertBoard() 에러 : " + e);
		} finally {
			close(pstmt);
		}
		
		return insertCount;
	}

	public int selectListCount() {
		int listCount = 0;
		
		String sql = "select count(*) from tbl_review";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			
			System.out.println("리뷰게시판 글 개수 : "+listCount);
		}catch(Exception e) {
			System.out.println("ReviewBoardDAO의 selectListCount() 에러 : " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}
	
	
	public ArrayList<ReviewBoard> selectBoardList(int page, int limit){
		ArrayList<ReviewBoard> boardList = null;
		
		String sql = "select * from tbl_review order by ordernum desc limit ?,5";
		int startRow = (page-1)*5;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				boardList = new ArrayList<ReviewBoard>();
				
				do {
					ReviewBoard board = new ReviewBoard();
					
					board.setReview_num(rs.getString("review_num"));
					board.setCar_id(rs.getString("car_id"));
					board.setUser_id(rs.getString("user_id"));
					board.setOrdernum(rs.getString("ordernum"));
					board.setReview_title(rs.getString("review_title"));
					board.setReview_content(rs.getString("review_content"));
					board.setReview_file1(rs.getString("review_file1"));
					board.setReview_file1_origin(rs.getString("review_file1_origin"));
					board.setReview_file2(rs.getString("review_file2"));
					board.setReview_file2_origin(rs.getString("review_file2_origin"));
					board.setReview_file3(rs.getString("review_file3"));
					board.setReview_file3_origin(rs.getString("review_file3_origin"));
					board.setReview_date(rs.getTimestamp("review_date"));
					board.setReview_hit(rs.getInt("review_hit"));
					
					boardList.add(board);
					
				}while(rs.next());
			}
			
		}catch(Exception e) {
			System.out.println("ReviewBoardDAO의 selectBoardList() 에러 : " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return boardList;
	}

	public ReviewBoard selectReview(String review_num) {
		ReviewBoard board = null;
		System.out.println("review_num : "+ review_num);
		String sql = "select * from tbl_review where review_num=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, review_num);
			System.out.println("변수 대입");
			
			rs = pstmt.executeQuery();
			System.out.println("resultSet 실행");
			
			if(rs.next()) {
				System.out.println("결과값이 있다");
				board = new ReviewBoard();
				System.out.println("board 객체 생성");
				board.setReview_num(rs.getString("review_num"));
				board.setCar_id(rs.getString("car_id"));
				board.setUser_id(rs.getString("user_id"));
				board.setOrdernum(rs.getString("ordernum"));
				board.setReview_title(rs.getString("review_title"));
				board.setReview_content(rs.getString("review_content"));
				board.setReview_file1(rs.getString("review_file1"));
				board.setReview_file1_origin(rs.getString("review_file1_origin"));
				board.setReview_file2(rs.getString("review_file2"));
				board.setReview_file2_origin(rs.getString("review_file2_origin"));
				board.setReview_file3(rs.getString("review_file3"));
				board.setReview_file3_origin(rs.getString("review_file3_origin"));
				board.setReview_date(rs.getTimestamp("review_date"));
				board.setReview_hit(rs.getInt("review_hit"));				
			}
			
			System.out.println("title : "+board.getReview_title());
			
		}catch(Exception e) {
			System.out.println("ReviewBoardDAO의 selectReview() 에러 : " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return board;
	}

	public int updateHit(String review_num) {
		int updateCount = 0;
		
		String sql = "update tbl_review set review_hit=review_hit+1 where review_num=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, review_num);
			
			updateCount = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("ReviewBoardDAO의 updateHit() 에러 : " + e);
		} finally {
			close(pstmt);
		}
		
		return updateCount;
	}

	public int deleteBoard(String review_num) {
		int deleteCount = 0;
		
		String sql = "delete from tbl_review where review_num=?";
		
		try {	
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, review_num);
			
			deleteCount = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("NoticeBoardDAO의 deleteBoard() 에러 : " + e);
		} finally {
			close(pstmt);
		}
		
		return deleteCount;
	}

	public List<String> getOrdernum(String user_id) {
		List<String> ordernum = null;
		
		String sql = "select ordernum from tbl_review where user_id=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				ordernum = new ArrayList<>();
				do {
					ordernum.add(rs.getString(1));
				}while(rs.next());
			}
			
			System.out.println("리뷰게시판 글 개수 : "+ordernum.size());
		}catch(Exception e) {
			System.out.println("ReviewBoardDAO의 selectListCount() 에러 : " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return ordernum;
	}

	public ArrayList<ReviewBoard> getcustReview(String user_id) {
		ArrayList<ReviewBoard> review = null;
		
		String sql = "select * from tbl_review where user_id=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				review = new ArrayList<ReviewBoard>();
				
				do {
					ReviewBoard board = new ReviewBoard();
					
					board.setReview_num(rs.getString("review_num"));
					board.setCar_id(rs.getString("car_id"));
					board.setUser_id(rs.getString("user_id"));
					board.setOrdernum(rs.getString("ordernum"));
					board.setReview_title(rs.getString("review_title"));
					board.setReview_content(rs.getString("review_content"));
					board.setReview_file1(rs.getString("review_file1"));
					board.setReview_file1_origin(rs.getString("review_file1_origin"));
					board.setReview_file2(rs.getString("review_file2"));
					board.setReview_file2_origin(rs.getString("review_file2_origin"));
					board.setReview_file3(rs.getString("review_file3"));
					board.setReview_file3_origin(rs.getString("review_file3_origin"));
					board.setReview_date(rs.getTimestamp("review_date"));
					board.setReview_hit(rs.getInt("review_hit"));
					
					review.add(board);
					
				}while(rs.next());
			}
			
		}catch(Exception e) {
			System.out.println("ReviewBoardDAO의 getcustReview() 에러 : " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return review;
	}
}

