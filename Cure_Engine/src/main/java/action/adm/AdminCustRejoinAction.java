package action.adm;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.adm.AdminCustRejoinService;
import vo.ActionForward;

public class AdminCustRejoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String user_id = request.getParameter("user_id");
		
		AdminCustRejoinService custRejoinService = new AdminCustRejoinService();
		int rejoinResult = custRejoinService.custRejoin(user_id);
		
		if(rejoinResult > 0) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('재가입 처리 되었습니다.');");
			out.println("location.href='customerList.adm';"); //로그인폼보기 다시 요청
			out.println("</script>");
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('재가입 처리 실패하였습니다.');");
			out.println("history.back();"); //로그인폼보기 다시 요청
			out.println("</script>");
		}
		
		return forward;
	}

}
