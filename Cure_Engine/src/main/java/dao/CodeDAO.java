package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Code;

public class CodeDAO {
		private Connection con = null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;
		
		private CodeDAO() {}
		
		private static CodeDAO codeDAO;
		
		public static CodeDAO getInstance() {
			if(codeDAO == null) {//DogDAO 객체가 없으면
				codeDAO = new CodeDAO();
			}
			return codeDAO; //기존 DogDAO객체가 있으면 주소를 리턴
		}
		
		
		/**
		 * 1.DB연결(Service 통해) → 2.sql 실행 → 3. 결과처리 → 4. 연결해제
		 * 
		 */
		
		//DB연결(Service)
		public void setConnection (Connection con){
			this.con = con;
		}

		
		/*---- 차량옵션 코드만 가져오기 -------------------------------------------------------------------------------------------*/
		public ArrayList<Code> selectAllOptionCode() {
			ArrayList<Code> allOptionCode = null;
			
			String sql = "select * from tbl_code_master where code_category like '%option%'";
			
			try {
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();

				if(rs.next()) {
					allOptionCode = new ArrayList<Code>();
					do {
						allOptionCode.add(new Code(
								rs.getString("code_category"),
								rs.getString("code_name"),
								rs.getString("code_value")								
								));
						
					}while(rs.next());
				}				
			}catch(Exception e) {
				System.out.println("CodeDAO 클래스의 selectAllCode()에서 발생한 에러 : "+e);
			}finally {
				close(rs);
				close(pstmt);
			}
			return allOptionCode;
		}
		
		/*---- 사고관련 코드만 가져오기 -------------------------------------------------------------------------------------------*/
		public ArrayList<Code> selectAllAccidentCode() {
			ArrayList<Code> allAccidentCode = null;
			
			String sql = "select * from tbl_code_master where code_category like '%accident%'";
			
			try {
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();

				if(rs.next()) {
					allAccidentCode = new ArrayList<Code>();
					do {
						allAccidentCode.add(new Code(
								rs.getString("code_category"),
								rs.getString("code_name"),
								rs.getString("code_value")								
								));
						
					}while(rs.next());
				}				
			}catch(Exception e) {
				System.out.println("CodeDAO 클래스의 selectAllAccidentCode()에서 발생한 에러 : "+e);
			}finally {
				close(rs);
				close(pstmt);
			}
			return allAccidentCode;
		}

		
		/*---- 모든 코드만 가져오기 -------------------------------------------------------------------------------------------*/
		public ArrayList<Code> selectAllCode() {
			ArrayList<Code> allCode = null;
			
			String sql = "select * from tbl_code_master";
			
			try {
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();

				if(rs.next()) {
					allCode = new ArrayList<Code>();
					do {
						allCode.add(new Code(
								rs.getString("code_category"),
								rs.getString("code_name"),
								rs.getString("code_value")								
								));
						
					}while(rs.next());
				}				
			}catch(Exception e) {
				System.out.println("CodeDAO 클래스의 selectAllAccidentCode()에서 발생한 에러 : "+e);
			}finally {
				close(rs);
				close(pstmt);
			}
			return allCode;
		}


		public int insertDealer(String[] dealer_ids) {
			// TODO 자동 생성된 메소드 스텁
			return 0;
		}
		

		
}
