package action.adm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.adm.AdminOrderDetailService;
import vo.ActionForward;
import vo.Order;
import vo.Car;
import vo.Payment;

public class AdminOrderDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String ordernum = request.getParameter("ordernum");
		System.out.println("ordernum ; " + ordernum);
		String car_id = request.getParameter("car_id");
		System.out.println("car_id ; " + car_id);
		
		AdminOrderDetailService orderDetailService = new AdminOrderDetailService();
		Order orderInfo = orderDetailService.getOrderDetail(ordernum);
		Car carInfo = orderDetailService.getCarDetail(car_id);
		Payment payInfo = orderDetailService.getPayDetail(ordernum);
		
		request.setAttribute("orderInfo", orderInfo);
		request.setAttribute("carInfo", carInfo);
		request.setAttribute("payInfo", payInfo);
		
		request.setAttribute("showPage", "adm/orderDetail.jsp");
		forward = new ActionForward("template.jsp", false);	
		
		return forward;
	}

}
