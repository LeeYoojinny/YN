/**
 * DB 작업 시 공통적으로 사용하는 메서드를 정의
 */
package db;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.sql.DataSource;

public class JdbcUtil {
	//'커넥션 풀'에서 Connection객체 얻어와 반환
	public static Connection getConnection(){
		Connection con = null;
		
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)envCtx.lookup("jdbc/cure_Engine");
			con = ds.getConnection();
			
			con.setAutoCommit(false);//★★Connection객체에 트랜잭션을 완료하지 못하도록 false로 적용
			
		}catch(Exception e) {
			//e.printStackTrace();
			System.out.println("JdbcUtil 클래스의 getConnection() 에러 = " + e);
		}
		
		return con;
	}
	
	/**
	 * Connection객체를 닫아주는 메서드
	 */	
	public static void close(Connection con){
		try {
			con.close();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	/**
	 * Statement객체를 닫아주는 메서드
	 */	
	public static void close(Statement stmt){
		try {
			stmt.close();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	/**
	 * PreparedStatement객체를 닫아주는 메서드
	 */	
	public static void close(PreparedStatement pstmt){
		try {
			pstmt.close();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	/**
	 * ResultSet객체를 닫아주는 메서드
	 */	
	public static void close(ResultSet rs){
		try {
			rs.close();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	/*-------------------------------------------------------------------------------------------------------*/
	/*
	 * 트랜잭션 중에 실행된 작업들을 '완료'시키는 메서드
	 * insert, delete, update 한 후 commit함
	 */
	public static void commit(Connection con) {
		try {
			con.commit();//DB영구저장
			System.out.println("commit success");
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	/* 
	 * 트랜잭션 중에 실행된 작업들을 '취소'시키는 메서드
	 */
	public static void rollback(Connection con) {
		try {
			con.rollback();
			System.out.println("rollback success");
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
}












