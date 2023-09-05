package action.user;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.user.UserCarDetailViewService;
import vo.ActionForward;
import vo.Car;
import vo.User;
import vo.Code;

public class UserCarDetailViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String car_id = request.getParameter("car_id");
		
		UserCarDetailViewService userCarDetailViewService = new UserCarDetailViewService();
		Car carDetail = userCarDetailViewService.getdetail(car_id);
		
		if(carDetail != null) {//상세정보를 볼 car 정보 가지고왔다면
			System.out.println("상세정보를 볼 car 정보 가지고옴");
			
			User dealer = userCarDetailViewService.getDealerInfo(carDetail.getDealer_id());
			ArrayList<Code> allCode = userCarDetailViewService.getAllCode();
			
			if(dealer != null && allCode != null) {//차량에 대한 딜러정보, 코드정보 가지고 옴
				System.out.println("딜러 정보 및 옵션정보를 위한 코드 가지고옴");
				
				request.setAttribute("carDetail", carDetail);
				request.setAttribute("dealerInfo", dealer);
				request.setAttribute("allCode", allCode);

				
				request.setAttribute("showPage", "user/carDetailViewForm.jsp");
				forward = new ActionForward("template.jsp", false);	
			}
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
