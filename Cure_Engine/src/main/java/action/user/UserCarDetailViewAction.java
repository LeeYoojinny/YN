package action.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.user.UserCarDetailViewService;
import vo.ActionForward;
import vo.Car;

public class UserCarDetailViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String car_id = request.getParameter("car_id");
		
		UserCarDetailViewService userCarDetailViewService = new UserCarDetailViewService();
		Car carDetail = userCarDetailViewService.getdetail(car_id);
		
		if(carDetail != null) {
			System.out.println("상세정보를 볼 car 정보 가지고옴");
			request.setAttribute("carDetail", carDetail);
			request.setAttribute("showPage", "user/carDetailViewForm.jsp");
			forward = new ActionForward("template.jsp", false);	
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('페이지 로드에 실패했습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}

}
