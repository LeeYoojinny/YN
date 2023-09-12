package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import vo.QnABoard;
import vo.PageInfo;

public class QnABoardDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private QnABoardDAO () {}
	
	private static QnABoardDAO boardDAO;
	
	public static QnABoardDAO getInstance() {
		if (boardDAO == null) {
			boardDAO = new QnABoardDAO();
		}

		return boardDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	/*-- QNA 게시판 글 개수 구하는 메서드 ----------------------------------------------------------------------*/
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
			System.out.println("BoardDAO의 selectQnaListCount() 에러 : " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}
	/*-- QNA 게시판 글 목록 가져오는 메서드 ----------------------------------------------------------------------*/
	public ArrayList<QnABoard> selectBoardList(int page, int limit){
		
		ArrayList<QnABoard> boardList = null;
		
		String sql = "select * from tbl_qna order by qna_viewNum desc,qna_replyNum asc  limit ?,10";
		int startRow = (page-1)*10;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				boardList = new ArrayList<QnABoard>();
				
				do {
					QnABoard board = new QnABoard();
					
					board.setQna_num(rs.getString("qna_num"));
					board.setQna_viewNum(rs.getInt("qna_viewNum"));
					board.setQna_replyNum(rs.getInt("qna_replyNum"));
					board.setUser_id(rs.getString("user_id"));
					board.setCar_id(rs.getString("car_id"));
					board.setQna_pw(rs.getString("qna_pw"));
					board.setQna_title(rs.getString("qna_title"));
					board.setQna_content(rs.getString("qna_content"));
					board.setQna_date(rs.getTimestamp("qna_date"));
					board.setSecret_YN(rs.getString("secret_YN"));
					board.setReply_YN(rs.getString("reply_YN"));
					board.setQna_hit(rs.getInt("qna_hit"));
					boardList.add(board);
					
				}while(rs.next());
			}
			
		}catch(Exception e) {
			System.out.println("BoardDAO의 selectBoardList() 에러 : " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return boardList;
			
	}
	
	/*-- QNA 게시판 글 등록 메서드 ------------------------------------------------------------------------*/
	public int insertBoard(QnABoard board) {
		int insertCount = 0;
		int num=0;
		
		String sql = "insert into tbl_qna(user_id,car_id,qna_pw,qna_title,qna_content,qna_file,"
				+ "qna_file_origin,secret_YN,qna_viewNum)"
				+ " values(?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement("select max(qna_viewNum) from tbl_qna");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num = rs.getInt(1)+1;
			}else {
				num = 1;
			}
						
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, board.getUser_id());
			pstmt.setString(2, board.getCar_id());
			pstmt.setString(3, board.getQna_pw());
			pstmt.setString(4, board.getQna_title());
			pstmt.setString(5, board.getQna_content());
			pstmt.setString(6, board.getQna_file());
			pstmt.setString(7, board.getQna_file_origin());
			pstmt.setString(8, board.getSecret_YN());
			pstmt.setInt(9, num);
						
			insertCount = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("BoardDAO의 insertBoard() 에러 : " + e);
		} finally {
			close(pstmt);
		}
		return insertCount;
	}
	
	/*-- QNA 게시판 조회수 증가 ------------------------------------------------------------------------*/
	public int updateHit(String qna_num) {
		int updateCount = 0;
		String sql = "update tbl_qna set qna_hit=qna_hit+1 where qna_num='"+qna_num+"'";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			updateCount = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("BoardDAO의 updateHit() 에러 : " + e);
		} finally {
			//close(rs);
			close(pstmt);
		}
		return updateCount;
	}
	
	/*-- QNA 특정 게시물 가져오기 ------------------------------------------------------------------------*/
	public QnABoard selectBoard(String qna_num) {
		QnABoard board = null;
		
		String sql = "select * from tbl_qna where qna_num='"+qna_num+"'";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new QnABoard(rs.getString("qna_num"),
								  rs.getInt("qna_viewNum"),
								  rs.getInt("qna_replyNum"),
								  rs.getString("user_id"),
								  rs.getString("car_id"),
								  rs.getString("qna_pw"),
								  rs.getString("qna_title"),
								  rs.getString("qna_content"),
								  rs.getString("qna_file"),
								  rs.getString("qna_file_origin"),
								  rs.getTimestamp("qna_date"),
								  rs.getString("secret_YN"),
								  rs.getString("reply_YN"),
								  rs.getInt("qna_hit")
								  );
			}
		}catch(Exception e) {
			System.out.println("BoardDAO의 selectBoard() 에러 : " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return board;
	}
	
	/*-- QNA 게시판 비밀번호 확인 ------------------------------------------------------------------------*/
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
			System.out.println("BoardDAO의 isBoardWriter() 에러 : " + e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return isWriter;
	}
	
	/*-- QNA 게시글 삭제기능 ------------------------------------------------------------------------------*/
	public int deleteBoard(String qna_num) {
		int deleteCount = 0;
		String sql = "delete from tbl_qna where qna_num=?";
		
		try {	
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qna_num);
			
			deleteCount = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("BoardDAO의 deleteBoard() 에러 : " + e);
		} finally {
			close(pstmt);
		}
		
		return deleteCount;
	}
	
	/*-- QNA 게시글 수정기능 ------------------------------------------------------------------------------*/
	public int updateBoard(QnABoard board, String qna_num) {
		int updateCount = 0;
		String sql = "update tbl_qna SET qna_pw=?, qna_title=?, qna_content=?,"
				+ " qna_file=?, qna_file_origin=?, secret_YN=?, qna_date=? where qna_num=?";
		
		System.out.println("qna_pw : " +board.getQna_pw());
		System.out.println("qna_title : " +board.getQna_title());
		System.out.println("qna_content : " +board.getQna_content());
		System.out.println("qna_file : " +board.getQna_file());
		System.out.println("qna_file_origin : " +board.getQna_file_origin());
		System.out.println("secret_YN : " +board.getSecret_YN());
		System.out.println("qna_num : " +board.getQna_num());
		
		try {
			pstmt = con.prepareStatement(sql);			
			
			pstmt.setString(1, board.getQna_pw());
			pstmt.setString(2, board.getQna_title());
			pstmt.setString(3, board.getQna_content());
			pstmt.setString(4, board.getQna_file());
			pstmt.setString(5, board.getQna_file_origin());
			pstmt.setString(6, board.getSecret_YN());
			pstmt.setTimestamp(7, board.getQna_date());
			pstmt.setString(8, qna_num);
			
			updateCount = pstmt.executeUpdate();
			System.out.println("update성공유무 : "+updateCount);
		}catch(Exception e) {
			System.out.println("BoardDAO의 updateBoard() 에러 : " + e);
		} finally {
			close(pstmt);
		}
		return updateCount;
	}
	
	/*-- QNA 게시글 조회 기능 ------------------------------------------------------------------------------*/
	public ArrayList<QnABoard> searchBoardList(String option, String keyword) {
		ArrayList<QnABoard> boardList = null;
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
				boardList = new ArrayList<QnABoard>();
				
				do {
					QnABoard board = new QnABoard();
					
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
			System.out.println("BoardDAO의 searchBoardList() 에러 : " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return boardList;
	}
	
	/*-- QNA 게시글 비밀번호 체크 ------------------------------------------------------------------------*/
	public int pwCheck(String qna_num, String input_pw) {
		int pwCheck = 0;
		
		String sql = "select count(*) from tbl_qna where qna_num=? and qna_pw=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qna_num);
			pstmt.setString(2, input_pw);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				pwCheck = rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("BoardDAO의 pwCheck() 에러 : " + e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return pwCheck;
	}

	public int insertReply(QnABoard board) {
		int replyCount = 0;
		int num=0;
		
		String sql = "insert into tbl_qna(user_id,car_id,qna_pw,qna_title,qna_content,qna_file,"
				+ "qna_file_origin,secret_YN,reply_YN,qna_viewNum,qna_replyNum)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement("select max(qna_replyNum) from tbl_qna where qna_viewNum=?");
			pstmt.setInt(1, board.getQna_viewNum());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num = rs.getInt(1)+1;
			}
						
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, board.getUser_id());
			pstmt.setString(2, board.getCar_id());
			pstmt.setString(3, board.getQna_pw());
			pstmt.setString(4, board.getQna_title());
			pstmt.setString(5, board.getQna_content());
			pstmt.setString(6, board.getQna_file());
			pstmt.setString(7, board.getQna_file_origin());
			pstmt.setString(8, board.getSecret_YN());
			pstmt.setString(9, board.getReply_YN());
			pstmt.setInt(10, board.getQna_viewNum());
			pstmt.setInt(11, num);
						
			replyCount = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("BoardDAO의 insertBoard() 에러 : " + e);
		} finally {
			close(pstmt);
		}
		
		return replyCount;
	}

	public int parentReplyYN(String parent_qna_num) {
		int parent_replyY = 0;
		
		String sql = "update tbl_qna set reply_YN='Y' where qna_num=?";
		
		try {
			pstmt = con.prepareStatement(sql);			
			
			pstmt.setString(1, parent_qna_num);			
			parent_replyY = pstmt.executeUpdate();

		}catch(Exception e) {
			System.out.println("BoardDAO의 updateBoard() 에러 : " + e);
		} finally {
			close(pstmt);
		}
		
		return parent_replyY;
	}


	
		
}

