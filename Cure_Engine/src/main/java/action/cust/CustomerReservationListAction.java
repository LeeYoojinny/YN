package action.cust;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.cust.CustomerReservationListService;
import vo.ActionForward;
import vo.Code;
import vo.Reservation;

public class CustomerReservationListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		CustomerReservationListService customerReservationListService= new CustomerReservationListService();
		
		ArrayList<Reservation> myReservation = customerReservationListService.getMyReservation(user_id);
		ArrayList<Code> allCode = customerReservationListService.getAllCode();
		
		request.setAttribute("myReservation", myReservation);
		request.setAttribute("allCode", allCode);
		
		request.setAttribute("showPage", "customer/myReservation.jsp");
		forward = new ActionForward("template.jsp", false);
		
		return forward;
	}

}
