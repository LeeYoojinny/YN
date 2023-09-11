package action.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.board.QnA_BoardUpdateFormService;
import vo.ActionForward;
import vo.Board;
import vo.Code;

public class QnA_BoardUpdateFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String qna_num = request.getParameter("qna_num");
		
		QnA_BoardUpdateFormService qna_BoardUpdateFormService = new QnA_BoardUpdateFormService();
		Board board = qna_BoardUpdateFormService.getBoardInfo(qna_num);
		ArrayList<Code> allCode = qna_BoardUpdateFormService.getAllCode();
		
		request.setAttribute("board", board);
		request.setAttribute("allCode", allCode);
		
		request.setAttribute("showPage", "user/qna/qna_updateForm.jsp");
		forward = new ActionForward("template.jsp", false);	
		
		return forward;
	}

}
