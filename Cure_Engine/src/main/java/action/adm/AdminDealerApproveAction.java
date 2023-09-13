package action.adm;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.adm.AdminDealerApproveService;
import vo.ActionForward;

public class AdminDealerApproveAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String dealer_id = request.getParameter("user_ids");
		String[] dealer_ids;
		if(dealer_id != null && dealer_id.contains(",")) {
			dealer_ids = dealer_id.split(",");
		}else {
			dealer_ids = new String[] {dealer_id};
		}
		
		//콘솔로 값 확인하기
		for(String id : dealer_ids) {
			System.out.println("파라미터 값으로 넘어온 dealer_ids : " + id);
		}
		
		AdminDealerApproveService dealerApproveService = new AdminDealerApproveService();
		int approveResult = dealerApproveService.joinApprove_Y(dealer_ids);
		
		if(approveResult > 0 ) {
			int codeInsert = dealerApproveService.codeUpdate(dealer_ids);
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('승인처리 되었습니다.');");
			out.println("location.href='dealerApproveList.adm';");
			out.println("</script>");
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('승인 실패했습니다.');");
			out.println("history.back();");			
			out.println("</script>");
		}
		
		return forward;
	}

}
