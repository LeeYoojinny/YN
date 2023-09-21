package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import vo.NoticeBoard;
import vo.PageInfo;
import vo.QnABoard;

public class NoticeBoardDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private NoticeBoardDAO () {}
	
	private static NoticeBoardDAO noticeBoardDAO;
	
	public static NoticeBoardDAO getInstance() {
		if (noticeBoardDAO == null) {
			noticeBoardDAO = new NoticeBoardDAO();
		}

		return noticeBoardDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	public int selectListCount() {
		int listCount = 0;
		
		String sql = "select count(*) from tbl_notice";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("NoticeBoardDAO의 selectListCount() 에러 : " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}

	public ArrayList<NoticeBoard> selectBoardList(int page, int limit) {
		ArrayList<NoticeBoard> boardList = null;
		
		String sql = "select * from tbl_notice order by notice_num desc limit ?,10";
		int startRow = (page-1)*10;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				boardList = new ArrayList<NoticeBoard>();
				
				do {
					NoticeBoard board = new NoticeBoard();
					
					board.setNotice_num(rs.getString("notice_num"));
					board.setUser_id(rs.getString("user_id"));
					board.setNotice_title(rs.getString("notice_title"));
					board.setNotice_content(rs.getString("notice_content"));
					board.setNotice_file(rs.getString("notice_file"));
					board.setNotice_file_origin(rs.getString("notice_file_origin"));
					board.setNotice_date(rs.getTimestamp("notice_date"));
					board.setNotice_hit(rs.getInt("notice_hit"));
					boardList.add(board);
					
				}while(rs.next());
			}
			
		}catch(Exception e) {
			System.out.println("NoticeBoardDAO의 selectBoardList() 에러 : " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return boardList;
	}
	
	/*-- 공지사항 등록하기 -------------------------------------------------*/
	public int insertBoard(NoticeBoard notice) {
		int insertCount = 0;
		
		String sql = "insert into tbl_notice(notice_num,user_id,notice_title,notice_content,notice_file,notice_file_origin)"
				+ " values(?,?,?,?,?,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, notice.getNotice_num());
			pstmt.setString(2, notice.getUser_id());
			pstmt.setString(3, notice.getNotice_title());
			pstmt.setString(4, notice.getNotice_content());
			pstmt.setString(5, notice.getNotice_file());
			pstmt.setString(6, notice.getNotice_file_origin());
						
			insertCount = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("NoticeBoardDAO의 insertBoard() 에러 : " + e);
		} finally {
			close(pstmt);
		}
		return insertCount;
	}
	
	/*-- 공지사항 내용 클릭 할 때 마다 조회수 up -------------------------------------------------*/
	public int updateHit(String notice_num) {
		int updateCount = 0;
		
		String sql = "update tbl_notice set notice_hit=notice_hit+1 where notice_num='"+notice_num+"'";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			updateCount = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("NoticeBoardDAO의 updateHit() 에러 : " + e);
		} finally {
			close(pstmt);
		}
		
		return updateCount;
	}
	
	/*-- 공지사항 리스트 출력위해 모든 정보 가져오기 -------------------------------------------------*/
	public NoticeBoard selectBoardView(String notice_num) {
		NoticeBoard notice = null;
		
		String sql = "select * from tbl_notice where notice_num='"+notice_num+"'";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				notice = new NoticeBoard(
									rs.getString("notice_num"),
									rs.getString("user_id"),
									rs.getString("notice_title"),
									rs.getString("notice_content"),
									rs.getString("notice_file"),
									rs.getString("notice_file_origin"),
									rs.getTimestamp("notice_date"),
									rs.getInt("notice_hit")
								  );
			}
		}catch(Exception e) {
			System.out.println("NoticeBoardDAO의 selectBoard() 에러 : " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return notice;
	}

	/*-- 공지사항 삭제하기 -------------------------------------------------*/
	public int deleteBoard(String notice_num) {
		int deleteCount = 0;
		
		String sql = "delete from tbl_notice where notice_num=?";
		
		try {	
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, notice_num);
			
			deleteCount = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("NoticeBoardDAO의 deleteBoard() 에러 : " + e);
		} finally {
			close(pstmt);
		}
		
		return deleteCount;
	}

	/*-- 공지사항 수정하기 -------------------------------------------------*/
	public int updateBoard(NoticeBoard notice, String notice_num) {
		int updateCount = 0;
		
		String sql = "update tbl_notice set notice_title=?, notice_content=?, "
				+ "notice_file=?, notice_file_origin=?, notice_date=? where notice_num=?";
		
		try {
			pstmt = con.prepareStatement(sql);			
			
			pstmt.setString(1, notice.getNotice_title());
			pstmt.setString(2, notice.getNotice_content());
			pstmt.setString(3, notice.getNotice_file());
			pstmt.setString(4, notice.getNotice_file_origin());
			pstmt.setTimestamp(5, notice.getNotice_date());
			pstmt.setString(6, notice_num);
			
			updateCount = pstmt.executeUpdate();
			System.out.println("update성공유무 : "+updateCount);
		}catch(Exception e) {
			System.out.println("NoticeBoardDAO의 updateBoard() 에러 : " + e);
		} finally {
			close(pstmt);
		}
		
		
		return updateCount;
	}
	
	/*-- notice_num 생성을 위해 제일 최근 번호 받아오기 -------------------------------------------------*/
	public String createNoticeNum() {
		String notice_num = "";
		
		String sql = "select max(notice_num) from tbl_notice";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				notice_num = rs.getString(1);				
			}

		} catch (Exception e) {
			System.out.println("NoticeBoardDAO 클래스의 createNoticeNum()에서 발생한 에러 : " + e);
		} finally {
			close(rs);
			close(pstmt);
		}	
		
		return notice_num;
	}
	
	
		
}

