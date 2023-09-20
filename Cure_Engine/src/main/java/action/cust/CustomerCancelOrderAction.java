package action.cust;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.cust.CustomerCancelOrderService;
import vo.ActionForward;

public class CustomerCancelOrderAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String car_id = request.getParameter("car_id");
		System.out.println("car_id : "+car_id);
		
		CustomerCancelOrderService cancelOrderService = new CustomerCancelOrderService();
		boolean cancelSuccess = cancelOrderService.cancelOrder(car_id);
		
		if(cancelSuccess) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('주문취소 처리 되었습니다.');");
			out.println("location.href='myOrderList.cust';");
			out.println("</script>");
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('주문취소에 실패하였습니다. 다시 시도해주세요.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		
		
		return forward;
	}

}
