package action.cust;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.cust.CustomerOrderFormService;
import vo.ActionForward;
import vo.Car;
import vo.Code;
import vo.User;

public class CustomerOrderFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		String car_id = request.getParameter("car_id");
		
		CustomerOrderFormService orderFormService = new CustomerOrderFormService();
		User userInfo = orderFormService.getUserInfo(user_id);
		Car carInfo = orderFormService.getCarInfo(car_id);
				
		ArrayList<Code> allCode = orderFormService.getAllCode();
		
		request.setAttribute("userInfo", userInfo);
		request.setAttribute("carInfo", carInfo);
		request.setAttribute("allCode", allCode);
		
		request.setAttribute("showPage", "adm/dealerApproveList.jsp");
		forward = new ActionForward("template.jsp",false);
		
		
		return forward;
	}

}
