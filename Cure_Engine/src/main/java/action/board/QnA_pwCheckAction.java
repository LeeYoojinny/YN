package action.board;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.board.QnA_pwCheckService;
import vo.ActionForward;

public class QnA_pwCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String input_pw = request.getParameter("input_pw");
		String qna_num = request.getParameter("qna_num");
		
		QnA_pwCheckService qna_pwCheckService = new QnA_pwCheckService();
		int pwCheckOk = qna_pwCheckService.pwCheck(qna_num,input_pw);
		
		if(pwCheckOk > 0) {
			String display_num = request.getParameter("display_num");
			if(display_num.equals("1")) {
				forward = new ActionForward("qna_boardUpdateForm.bo", false);	
			}else if(display_num.equals("2")) {
				forward = new ActionForward("qna_boardDelete.bo?qna_num="+qna_num, false);	
			}else if(display_num.equals("3")) {
				forward = new ActionForward("qna_boardView.bo?qna_num="+qna_num, false);	
			}				
		}else {
			response.setContentType("text/html; charset=utf-8");			
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다.');");
			out.println("history.back();"); //로그인폼보기 다시 요청
			out.println("</script>");
		}
		
		return forward;
	}

}
