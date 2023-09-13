package action.adm;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.adm.AdminDealerRefuseService;
import vo.ActionForward;

public class AdminDealerRefuseAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String dealer_id = request.getParameter("user_id");
		
		AdminDealerRefuseService dealerRefuseService = new AdminDealerRefuseService();
		int refuseResult = dealerRefuseService.setDealerRefuse(dealer_id);
		
		if(refuseResult > 0 ) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('반려처리 되었습니다.');");
			out.println("location.href='dealerApproveList.adm';");
			out.println("</script>");
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('반려처리에 실패했습니다.');");
			out.println("history.back();");			
			out.println("</script>");
		}
		
		return forward;
	}

}
