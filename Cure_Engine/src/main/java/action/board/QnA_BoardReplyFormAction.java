package action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.board.QnA_BoardReplyFormService;
import vo.ActionForward;
import vo.QnABoard;

public class QnA_BoardReplyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String qna_num = request.getParameter("qna_num");
		
		QnA_BoardReplyFormService qna_BoardReplyFormService = new QnA_BoardReplyFormService();
		QnABoard board = qna_BoardReplyFormService.getboardInfo(qna_num);
		
		request.setAttribute("board", board);
		
		request.setAttribute("showPage", "user/qna/qna_ReplyForm.jsp");
		forward = new ActionForward("template.jsp", false);	
		
		return forward;
	}

}
