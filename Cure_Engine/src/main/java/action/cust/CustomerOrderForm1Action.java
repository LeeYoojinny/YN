package action.cust;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.cust.CustomerOrderForm1Service;
import vo.ActionForward;
import vo.Car;
import vo.Code;
import vo.Coupon;
import vo.Deliveryfee;
import vo.User;

public class CustomerOrderForm1Action implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		String car_id = request.getParameter("car_id");
		
		CustomerOrderForm1Service orderFormService = new CustomerOrderForm1Service();
		//회원가입에 필요한 고객정보, 자동차정보, 쿠폰정보 가져오기
		User userInfo = orderFormService.getUserInfo(user_id);
		Car carInfo = orderFormService.getCarInfo(car_id);
		Coupon myCoupon = orderFormService.getMyCoupon(user_id);
		
		ArrayList<Deliveryfee> allFee = orderFormService.getAllFee();				
		ArrayList<Code> allCode = orderFormService.getAllCode();
		
		request.setAttribute("userInfo", userInfo);
		request.setAttribute("carInfo", carInfo);
		request.setAttribute("myCoupon", myCoupon);
		request.setAttribute("allFee", allFee);
		request.setAttribute("allCode", allCode);
		
		request.setAttribute("showPage", "customer/orderFormStep1.jsp");
		forward = new ActionForward("template.jsp",false);
		
		
		return forward;
	}

}
