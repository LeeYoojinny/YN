package svc.board;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.BoardDAO;
import vo.Board;

public class QnA_pwCheckService {

	public int pwCheck(String qna_num, String input_pw) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int pwOk  = boardDAO.pwCheck(qna_num,input_pw);

		close(con);
		return pwOk;

	}

}
