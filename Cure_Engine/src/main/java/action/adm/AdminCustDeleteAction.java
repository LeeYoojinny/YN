package action.adm;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.adm.AdminCustDeleteService;
import vo.ActionForward;

public class AdminCustDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String user_id = request.getParameter("user_id");
		
		AdminCustDeleteService custDeleteService = new AdminCustDeleteService();
		int deleteResult = custDeleteService.custDelete(user_id);
		
		if(deleteResult > 0) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('탈퇴처리 되었습니다.');");
			out.println("location.href='customerList.adm';"); //로그인폼보기 다시 요청
			out.println("</script>");
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('탈퇴처리 실패하였습니다.');");
			out.println("history.back();"); //로그인폼보기 다시 요청
			out.println("</script>");
		}
		
		
		return forward;
	}

}
