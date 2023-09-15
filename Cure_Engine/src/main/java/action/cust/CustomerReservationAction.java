package action.cust;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.cust.CustomerReservationService;
import vo.ActionForward;
import vo.Reservation;

public class CustomerReservationAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		Reservation reservation = new Reservation();
		
		reservation.setCar_id(request.getParameter("car_id"));
		reservation.setUser_id(request.getParameter("user_id"));
		reservation.setDealer_id(request.getParameter("dealer_id"));
		
		//날짜 처리
		String revDateString = request.getParameter("rev_date");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date rev_date = dateFormat.parse(revDateString);
		reservation.setRev_date(rev_date);
		
		//시간 처리
		String ampm = request.getParameter("ampm");
		String hour = request.getParameter("hour");
		String minute = request.getParameter("minute");

		// 시간 문자열 생성
		String revTime = ampm+" "+hour + ":" + minute;

		// reservation 객체에 설정
		reservation.setRev_time(revTime);
		
		
		CustomerReservationService reservationFormService = new CustomerReservationService();
		boolean isReserveSuccess = reservationFormService.reserve(reservation);
		
		if(!isReserveSuccess) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('시승예약에 실패하였습니다. 다시 시도해주세요.');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('시승예약 되었습니다.');");
			out.println("location.href='allCarListView.usr';");
			out.println("</script>");
		}
		
		return forward;
	}

}
