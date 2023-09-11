package action.board;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.board.QnA_BoardDeleteService;
import vo.ActionForward;

public class QnA_BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String qna_num = request.getParameter("qna_num");
		
		QnA_BoardDeleteService qna_BoardDeleteService = new QnA_BoardDeleteService();
		boolean isDeleteSuccess = qna_BoardDeleteService.removeBoard(qna_num);
		
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
			out.println("location.href='qna_boardList.bo';");
			out.println("</script>");
		}
		
		
		return forward;
	}

}
