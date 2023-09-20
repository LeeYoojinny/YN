package action.board;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.board.Review_BoardDeleteService;
import vo.ActionForward;

public class Review_BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String review_num = request.getParameter("review_num");
		
		Review_BoardDeleteService review_BoardDeleteService = new Review_BoardDeleteService();
		boolean isDeleteSuccess = review_BoardDeleteService.removeBoard(review_num);
		
		if(!isDeleteSuccess) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 실패하였습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();			
			out.println("<script>");
			out.println("alert('삭제되었습니다.');");
			out.println("location.href='review_boardList.bo';");
			out.println("</script>");
		}
		
		return forward;
	}

}
