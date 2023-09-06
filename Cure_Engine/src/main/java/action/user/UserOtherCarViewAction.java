package action.user;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.user.UserOtherCarViewService;
import vo.ActionForward;
import vo.Car;
import vo.Code;

public class UserOtherCarViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String dealer_id = request.getParameter("dealer_id");
		String dealer_name = request.getParameter("dealer_name");
		
		UserOtherCarViewService userOtherCarViewService = new UserOtherCarViewService();
		ArrayList<Car> otherCarList = userOtherCarViewService.getOtherCarList(dealer_id);
		ArrayList<Code> allCode = userOtherCarViewService.getAllCode();
		
		request.setAttribute("otherCarList", otherCarList);
		request.setAttribute("allCode", allCode);
		request.setAttribute("dealer_name", dealer_name);
		request.setAttribute("dealer_id", dealer_id);
		
		request.setAttribute("showPage", "user/otherCarListForm.jsp");
		forward = new ActionForward("template.jsp", false);	
		
		
		return forward;
	}

}
