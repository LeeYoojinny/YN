package action.cust;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.cust.CustomerOrderService;
import vo.ActionForward;
import vo.Order;
import vo.Payment;

public class CustomerOrderAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//주문처리 : 주문정보 DB저장 (Order, Payment),  Car 테이블의 sale_YN 값 W(대기)로 update
		
		
		//Order 객체 생성
		String car_id = request.getParameter("car_id");
		String user_id = request.getParameter("user_id");
		
		//null 값이 올 수 있는 쿠폰관련 값은 미리 처리
		String coupon_id = "";
		int discount_price = 0;
		
		String valueCheck = request.getParameter("coupon_id");		
		if(valueCheck != null && !valueCheck.isEmpty()) {
			coupon_id = valueCheck;
		}		
		valueCheck = request.getParameter("discount_price");
		if(valueCheck != null && !valueCheck.isEmpty()) {
			discount_price = Integer.parseInt(valueCheck);
		}

		int car_price = Integer.parseInt(request.getParameter("car_price"));
		int car_tax = Integer.parseInt(request.getParameter("car_tax"));
		int sale_expense = Integer.parseInt(request.getParameter("sale_expense"));
		String region = request.getParameter("region");
		int deliveryfee = Integer.parseInt(request.getParameter("deliveryfee"));
		int user_zipcode = Integer.parseInt(request.getParameter("user_zipcode"));
		String user_address1 = request.getParameter("user_address1");
		String user_address2 = request.getParameter("user_address2");
		String user_phone = request.getParameter("user_phone");
		int pay_by = Integer.parseInt(request.getParameter("pay_by"));
		int pay_price = Integer.parseInt(request.getParameter("total_price"));

		Order order = new Order();
		order.setCar_id(car_id);
		order.setUser_id(user_id);
		order.setCoupon_id(coupon_id);
		order.setDiscount_price(discount_price);
		order.setCar_price(car_price);
		order.setCar_tax(car_tax);
		order.setSale_expense(sale_expense);
		order.setRegion(region);
		order.setDeliveryfee(deliveryfee);
		order.setUser_zipcode(user_zipcode);
		order.setUser_address1(user_address1);
		order.setUser_address2(user_address2);
		order.setUser_phone(user_phone);
		order.setPayment(pay_by);
		
		
		//Payment 객체 생성
		Payment payment = new Payment();
		payment.setPay_by(pay_by);
		payment.setPay_price(pay_price);
		
		//null 값이 올 수 있는 결제방법에 대해 미리 처리하기
		String pay_depositor_name = "";
		String pay_creditcard_name = "";
		int pay_creditcard_num = 0;
		String pay_creditcard_date = "";		
		int pay_creditcard_cvc = 0;
		
		if(pay_by == 1) {
			pay_depositor_name = request.getParameter("pay_depositor_name");
			
		}else if(pay_by == 2) {
			pay_creditcard_name = request.getParameter("pay_creditcard_name");			
			pay_creditcard_num = Integer.parseInt(request.getParameter("pay_creditcard_num"));
			pay_creditcard_date = request.getParameter("pay_creditcard_date");
			pay_creditcard_cvc = Integer.parseInt(request.getParameter("pay_creditcard_cvc"));
		}
		
		payment.setPay_depositor_name(pay_depositor_name);
		payment.setPay_creditcard_name(pay_creditcard_name);
		payment.setPay_creditcard_num(pay_creditcard_num);
		payment.setPay_creditcard_date(pay_creditcard_date);
		payment.setPay_creditcard_cvc(pay_creditcard_cvc);
		
		CustomerOrderService orderService = new CustomerOrderService();
		int orderSuccess = orderService.insertOrder(order,payment);
		
		if(orderSuccess > 0) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();			
			out.println("<script>");
			out.println("location.href='orderFormStep3.cust';");
			out.println("</script>");
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();			
			out.println("<script>");
			out.println("alert('주문 접수에 실패하였습니다. 다시 시도해주세요.');");	
			out.println("location.href='carDetailView.usr?car_id=" + car_id + "';");
			out.println("</script>");
		}

		
		return forward;
	}

}
