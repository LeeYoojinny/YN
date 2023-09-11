package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import vo.Board;
import vo.PageInfo;

public class BoardDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private BoardDAO () {}
	
	private static BoardDAO boardDAO;
	
	public static BoardDAO getInstance() {
		if (boardDAO == null) {
			boardDAO = new BoardDAO();
		}

		return boardDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	/*-- 글 개수 구하는 메서드 --*/
	public int selectListCount() {
		int listCount = 0;
		
		String sql = "select count(*) from tbl_qna";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("selectListCount() 에러 : " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}
	/*-- 글 목록 가져오는 메서드 --*/
	public ArrayList<Board> selectBoardList(int page, int limit){
		
		ArrayList<Board> boardList = null;
		
		String sql = "select * from tbl_qna order by qna_num desc limit ?,10";
		int startRow = (page-1)*10;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				boardList = new ArrayList<Board>();
				
				do {
					Board board = new Board();
					
					board.setQna_num(rs.getString("qna_num"));
					board.setUser_id(rs.getString("user_id"));
					board.setCar_id(rs.getString("car_id"));
					board.setQna_pw(rs.getString("qna_pw"));
					board.setQna_title(rs.getString("qna_title"));
					board.setQna_content(rs.getString("qna_content"));
					board.setQna_date(rs.getTimestamp("qna_date"));
					board.setSecret_YN(rs.getString("secret_YN"));
					board.setQna_hit(rs.getInt("qna_hit"));
					boardList.add(board);
					
				}while(rs.next());
			}
			
		}catch(Exception e) {
			System.out.println("selectBoardList() 에러 : " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return boardList;
			
	}
	/*-- 글 등록 메서드 --*/
	public int insertBoard(Board board) {
		int insertCount = 0;
		
		String sql = "insert into tbl_qna(user_id,car_id,qna_pw,qna_title,qna_content,qna_file,qna_file_origin,secret_YN)"
				+ " values(?,?,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, board.getUser_id());
			pstmt.setString(2, board.getCar_id());
			pstmt.setString(3, board.getQna_pw());
			pstmt.setString(4, board.getQna_title());
			pstmt.setString(5, board.getQna_content());
			pstmt.setString(6, board.getQna_file());
			pstmt.setString(6, board.getQna_file_origin());
			pstmt.setString(7, board.getSecret_YN());
						
			insertCount = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("insertBoard() 에러 : " + e);
		} finally {
			close(pstmt);
		}
		return insertCount;
	}
	
	public int updateHit(String qna_num) {
		int updateCount = 0;
		String sql = "update tbl_qna set qna_hit=qna_hit+1 where qna_num='"+qna_num+"'";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			updateCount = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("updateHit() 에러 : " + e);
		} finally {
			//close(rs);
			close(pstmt);
		}
		return updateCount;
	}

	public Board selectBoard(String qna_num) {
		Board board = null;
		
		String sql = "select * from tbl_qna where qna_num='"+qna_num+"'";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new Board(rs.getString("qna_num"),
								  rs.getString("user_id"),
								  rs.getString("car_id"),
								  rs.getString("qna_pw"),
								  rs.getString("qna_title"),
								  rs.getString("qna_content"),
								  rs.getString("qna_file"),
								  rs.getString("qna_file_origin"),
								  rs.getTimestamp("qna_date"),
								  rs.getString("secret_YN"),
								  rs.getInt("qna_hit")
								  );
			}
		}catch(Exception e) {
			System.out.println("selectBoard() 에러 : " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return board;
	}

	public boolean isBoardWriter(String qna_num, String qna_pw) {
		String sql = "select * from tbl_qna where qna_num=?";
		boolean isWriter = false;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qna_num);
			rs = pstmt.executeQuery();
			rs.next();
			
			if(qna_pw.equals(rs.getString("qna_pw"))) {
				isWriter = true;
			}
		}catch(Exception e) {
			System.out.println("isBoardWriter() 에러 : " + e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return isWriter;
	}

	public int deleteBoard(String qna_num) {
		int deleteCount = 0;
		String sql = "delete from tbl_qna where qna_num=?";
		
		try {	
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qna_num);
			
			deleteCount = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("deleteBoard() 에러 : " + e);
		} finally {
			//close(rs);
			close(pstmt);
		}
		
		return deleteCount;
	}

	public int updateBoard(Board board) {
		int updateCount = 0;
		String sql = "update tbl_qna SET qna_title=?, qna_content=?, secret_YN=? where qna_num=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, board.getQna_title());
			pstmt.setString(2, board.getQna_content());
			pstmt.setString(3, board.getSecret_YN());
			pstmt.setString(4, board.getQna_num());
			
			updateCount = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("updateBoard() 에러 : " + e);
		} finally {
			//close(rs);
			close(pstmt);
		}
		return updateCount;
	}

	public ArrayList<Board> searchBoardList(String option, String keyword) {
		ArrayList<Board> boardList = null;
		String sql = "";
		if(option.equals("qna_title")) {
			sql = "select * from tbl_qna where qna_title like ?";
		}else if(option.equals("user_id")){
			sql = "select * from tbl_qna where user_id like ?";
		}
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,"%"+keyword+"%");
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardList = new ArrayList<Board>();
				
				do {
					Board board = new Board();
					
					board.setQna_num(rs.getString("qna_num"));
					board.setUser_id(rs.getString("user_id"));
					board.setCar_id(rs.getString("car_id"));
					board.setQna_pw(rs.getString("qna_pw"));
					board.setQna_title(rs.getString("qna_title"));
					board.setQna_content(rs.getString("qna_content"));
					board.setQna_date(rs.getTimestamp("qna_date"));
					board.setSecret_YN(rs.getString("secret_YN"));
					board.setQna_hit(rs.getInt("qna_hit"));
					boardList.add(board);
					
				}while(rs.next());
			}
		}catch(Exception e) {
			System.out.println("searchBoardList() 에러 : " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return boardList;
	}
	

	
	

	
		
}

