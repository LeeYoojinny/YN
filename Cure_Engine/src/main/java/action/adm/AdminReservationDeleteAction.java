package action.adm;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.adm.AdminMyCarRemoveService;
import svc.adm.AdminReservationRemoveService;
import vo.ActionForward;

public class AdminReservationDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		System.out.println("관리자 아이디 : "+user_id);
		
		String resernum = request.getParameter("resernums");
		System.out.println("삭제예약 번호 : "+resernum);
		
		String[] resernums;

		if (resernum != null && resernum.contains(",")) {
			resernums = resernum.split(",");
		} else {
			resernums = new String[]{resernum};
		}
		
		for(String id : resernums) {
			System.out.println("파라미터 값으로 넘어온 resernums : " + id);
		}
		
		AdminReservationRemoveService reservationRemoveService = new AdminReservationRemoveService();
		int removeResult = reservationRemoveService.removeRev(resernums,user_id);
				
		if(removeResult > 0 ) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 되었습니다.');");
			out.println("location.href='reservationView.adm';");
			out.println("</script>");
			
		}else {//서비스에서 메소드 실행 실패했다면
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 실패했습니다.');");
			out.println("history.back();");			
			out.println("</script>");
		}
				
		return forward;
	}

}
