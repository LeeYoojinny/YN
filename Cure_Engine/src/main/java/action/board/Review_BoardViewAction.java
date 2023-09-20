package action.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.board.QnA_BoardViewService;
import svc.board.Review_BoardViewService;
import vo.ActionForward;
import vo.Code;
import vo.QnABoard;
import vo.ReviewBoard;

public class Review_BoardViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		Review_BoardViewService review_BoardViewService = new Review_BoardViewService();
		String review_num = request.getParameter("review_num");
		ReviewBoard board = review_BoardViewService.getBoardView(review_num);
		
		System.out.println("file1 : "+board.getReview_file1());
		System.out.println("file2 : "+board.getReview_file2());
		System.out.println("file3 : "+board.getReview_file3());
		
		//업로드된 파일 개수 가져오기
		int fileCount = 0;

		if (board.getReview_file1() != null && !board.getReview_file1().isEmpty()) {
			fileCount++;
		}

		if (board.getReview_file2() != null && !board.getReview_file2().isEmpty()) {
			fileCount++;
		}

		if (board.getReview_file3() != null && !board.getReview_file3().isEmpty()) {
			fileCount++;
		}
		
		System.out.println("fileCount : " + fileCount);
		
		request.setAttribute("board", board);
		request.setAttribute("fileCount", fileCount);
		
		request.setAttribute("showPage", "user/review/review_View.jsp");
		forward = new ActionForward("template.jsp", false);	

		return forward;
	}

}
