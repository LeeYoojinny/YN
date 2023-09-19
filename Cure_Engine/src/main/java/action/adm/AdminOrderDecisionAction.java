package action.adm;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.adm.AdminOrderDecisionService;
import vo.ActionForward;

public class AdminOrderDecisionAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String decision = request.getParameter("decision");
		System.out.println("넘어온 dicision : "+decision);
		
		String car_id = request.getParameter("car_ids");
		System.out.println("넘어온 아이디 : "+car_id);
		
		String[] car_ids;

		if (car_id != null && car_id.contains(",")) {
		    car_ids = car_id.split(",");
		} else {
			car_ids = new String[]{car_id};
		}
		
		for(String id : car_ids) {
			System.out.println("파라미터 값으로 넘어온 car_ids : " + id);
		}
		
		AdminOrderDecisionService orderDecisionService =  new AdminOrderDecisionService();
		boolean result = false;
		if(decision.equals("Y")) {
			result = orderDecisionService.updateOrderY(car_ids);
		}else if(decision.equals("N")) {
			result = orderDecisionService.updateOrderN(car_ids);
		}		
		
		if(result) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('처리 되었습니다.');");
			out.println("location.href='orderListView.adm';");
			out.println("</script>");
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('처리 실패하였습니다. 다시 시도해주세요.');");
			out.println("history.back();");
			out.println("</script>");
		}	
		
		return forward;
	}

}
