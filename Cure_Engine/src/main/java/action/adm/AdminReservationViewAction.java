package action.adm;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.adm.AdminReservationViewService;
import vo.ActionForward;
import vo.PageInfo;
import vo.Reservation;


public class AdminReservationViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		String user_categoty = (String)session.getAttribute("user_category");
		
		AdminReservationViewService adminReservationViewService= new AdminReservationViewService();
			
		int page = 1;
		int limit = 10;
		int pageGroupSize = 10;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int listCount = adminReservationViewService.getRsvListCount();
		
		//총 페이지 수 계산
		int maxPage = (int) Math.ceil((double) listCount / limit);
		
		// 현재 페이지 그룹을 계산
		int currentPageGroup = (int) Math.ceil((double) page / pageGroupSize);

		// 시작 페이지와 끝 페이지 계산
		int startPage = (currentPageGroup - 1) * pageGroupSize + 1;
		int endPage = startPage + pageGroupSize - 1;
		if(endPage > maxPage) endPage = maxPage;
		
		//페이징처리를 위한 페이지정보
		PageInfo pageInfo = new PageInfo();
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);
		
		ArrayList<Reservation> reservation = null;
		
		//예약정보를 딜러와 관리자 분리해서 담아오기
		if(user_categoty.equals("dealer")) {
			reservation = adminReservationViewService.getReservation(user_id,page, limit);
		} else if(user_categoty.equals("admin")) {
			reservation = adminReservationViewService.getAllReservation(page, limit);
		}
		
		request.setAttribute("reservation", reservation);
		request.setAttribute("pageInfo", pageInfo);
		
		request.setAttribute("showPage", "adm/reservationView.jsp");
		forward = new ActionForward("template.jsp", false);
		
		return forward;
	}

}
