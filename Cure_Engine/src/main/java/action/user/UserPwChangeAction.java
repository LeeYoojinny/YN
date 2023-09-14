package action.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.user.UserPwChangeService;
import util.SHA256;
import vo.ActionForward;

public class UserPwChangeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String user_id = request.getParameter("user_id");
		String user_pw = SHA256.encodeSHA256(request.getParameter("user_pw"));
		
		System.out.println("비밀번호 변경에서 받은 아이디, 비번 : " + user_id + "," + user_pw);
		
		UserPwChangeService pwChangeService = new UserPwChangeService();
		boolean checkOk = pwChangeService.checkUser(user_id,user_pw);
		
		if(checkOk) {
			System.out.println("비밀번호 일치");
			String change_pw = SHA256.encodeSHA256(request.getParameter("change_pw")); 
			boolean changeOk = pwChangeService.changePw(user_id,change_pw);
			
			if(changeOk) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('비밀번호 수정되었습니다.');");
				out.println("location.href='index.jsp';");			
				out.println("</script>");
			}else {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('비밀번호 수정에 실패했습니다.');");
				out.println("history.back();");			
				out.println("</script>");
			}
		}else {
			System.out.println("비밀번호 불일치");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 틀렸습니다.');");
			out.println("history.back();");			
			out.println("</script>");
		}
		
		return forward;
	}

}
