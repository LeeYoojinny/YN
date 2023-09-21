package action.adm;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.adm.AdminCustDetailViewService;
import vo.ActionForward;
import vo.Car;
import vo.Code;
import vo.Order;
import vo.PageInfo;
import vo.QnABoard;
import vo.User;
import vo.ReviewBoard;
import vo.Reservation;

public class AdminCustDetailViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String user_id = request.getParameter("user_id");
		
		AdminCustDetailViewService custDetailViewService = new AdminCustDetailViewService();
		User custInfo = custDetailViewService.getCustInfo(user_id);
		ArrayList<QnABoard> custQna = custDetailViewService.getCustQna(user_id);
		ArrayList<ReviewBoard> custReview = custDetailViewService.getcustReview(user_id);
		ArrayList<Reservation> myReservation = custDetailViewService.getMyRev(user_id);
		ArrayList<Order> orderList = custDetailViewService.getOrderList(user_id);
		ArrayList<Code> allCode = custDetailViewService.getAllCode();		
		

		//회원의 기본정보 받아오기
		request.setAttribute("custInfo", custInfo);
		
		//회원이 적은 게시글들 받아오기
		request.setAttribute("boardList", custQna);
		request.setAttribute("custReview", custReview);
		request.setAttribute("allCode", allCode);		
		
		//회원의 예약정보 받아오기
		request.setAttribute("myReservation", myReservation);
		
		//회원주문내역 받아오기
		request.setAttribute("orderList", orderList);
		
				
		//목록으로 돌아가는 버튼을 각각 만들기 위해 받는 파라미터값
		request.setAttribute("display_num", request.getParameter("display_num"));
		System.out.println("display_num : "+ request.getParameter("display_num"));//콘솔확인용
		
		request.setAttribute("showPage", "adm/custDetailView.jsp");
		forward = new ActionForward("template.jsp",false);
		
		return forward;
	}

}
