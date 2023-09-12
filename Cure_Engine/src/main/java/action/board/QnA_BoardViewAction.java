package action.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.board.QnA_BoardViewService;
import vo.ActionForward;
import vo.QnABoard;
import vo.Code;

public class QnA_BoardViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		QnA_BoardViewService qna_BoardViewService = new QnA_BoardViewService();
		String qna_num = request.getParameter("qna_num");
		QnABoard board = qna_BoardViewService.getBoardView(qna_num);
		ArrayList<Code> allCode = qna_BoardViewService.getAllCode();
		
		//차량에 맞는 딜러가 답변을 해주기위해 딜러아이디 가져오기
		String dealer_id="";		
		if(board.getCar_id() != null) {
			dealer_id = qna_BoardViewService.getDealer_id(board.getCar_id());
			System.out.println("dealer_id : " + dealer_id);
		}
		
		request.setAttribute("list_num", request.getParameter("list_num"));
		request.setAttribute("board", board);
		request.setAttribute("allCode", allCode);
		request.setAttribute("dealer_id", dealer_id);
		
		request.setAttribute("showPage", "user/qna/qna_View.jsp");
		forward = new ActionForward("template.jsp", false);	

		return forward;
	}

}
