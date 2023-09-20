package action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.board.Review_BoardUpdateFormService;
import vo.ActionForward;
import vo.ReviewBoard;

public class Review_BoardUpdateFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String review_num = request.getParameter("review_num");
		System.out.println("review_num : "+review_num);
		
		Review_BoardUpdateFormService review_BoardUpdateFormService = new Review_BoardUpdateFormService();
		ReviewBoard board = review_BoardUpdateFormService.getBoardInfo(review_num);
		//System.out.println("title : " + board.getReview_title());
		
		request.setAttribute("board", board);
		request.setAttribute("showPage", "user/review/review_updateForm.jsp");
		forward = new ActionForward("template.jsp", false);	
		
		return forward;
	}

}
