package action.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.board.QnA_BoardViewService;
import vo.ActionForward;
import vo.Board;
import vo.Code;

public class QnA_BoardViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		QnA_BoardViewService qna_BoardViewService = new QnA_BoardViewService();
		String qna_num = request.getParameter("qna_num");
		Board board = qna_BoardViewService.getBoardView(qna_num);
		ArrayList<Code> allCode = qna_BoardViewService.getAllCode();
		
		request.setAttribute("board", board);
		request.setAttribute("allCode", allCode);
		
		request.setAttribute("showPage", "user/qna/qna_View.jsp");
		forward = new ActionForward("template.jsp", false);	

		return forward;
	}

}
