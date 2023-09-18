package action.adm;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.adm.AdminReservationCheckService;
import vo.ActionForward;

public class AdminReservationCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String resernum = request.getParameter("resernum");
		String check = request.getParameter("check");
		
		System.out.println("check?=" + check);
		
		System.out.println("resernum = " + resernum);
	
		
		AdminReservationCheckService reservationCheckService = new AdminReservationCheckService(); 
		
		boolean isRevCheckSuccess = reservationCheckService.revCheck(resernum,check);
		
		if(check.equals("Y")) {		
			if(!isRevCheckSuccess) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('예약 승인 실패하였습니다.');");
				out.println("hitory.back();");
				out.println("</script>");
			}else {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('예약 승인 성공하였습니다.');");
				out.println("location.href='reservationView.adm';");
				out.println("</script>");
			}
		}else {
			if(!isRevCheckSuccess) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('예약 거절 실패하였습니다.');");
				out.println("hitory.back();");
				out.println("</script>");
			}else {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('예약 거절 성공하였습니다.');");
				out.println("location.href='reservationView.adm';");
				out.println("</script>");
			}
		}
		return forward;
	}

}
