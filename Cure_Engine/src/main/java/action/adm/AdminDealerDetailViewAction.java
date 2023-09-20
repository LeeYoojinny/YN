package action.adm;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.adm.AdminDealerDetailViewService;
import vo.ActionForward;
import vo.Car;
import vo.Code;
import vo.User;
import vo.PageInfo;
import vo.Reservation;

public class AdminDealerDetailViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String user_id = request.getParameter("user_id");
		
		AdminDealerDetailViewService dealerDetailViewService = new AdminDealerDetailViewService();
		
		/* 1. 페이징처리 --------------------------------------------------------------------------*/
		int page = 1;
		int limit = 5;
		int pageGroupSize = 10;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int listCount = dealerDetailViewService.getListCount(user_id);
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
		
		User dealerInfo = dealerDetailViewService.getDealerInfo(user_id);
		ArrayList<Car> mySaleCarList = dealerDetailViewService.getMySaleCarList(user_id,page,limit);
		ArrayList<Reservation> reservation = dealerDetailViewService.getReservation(user_id,page, limit);
		ArrayList<Code> allCode = dealerDetailViewService.getAllCode();
		
		request.setAttribute("dealerInfo", dealerInfo);
		request.setAttribute("reservation", reservation);
		request.setAttribute("mySaleCarList", mySaleCarList);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("allCode", allCode);
		
		request.setAttribute("display_num", request.getParameter("display_num"));
		System.out.println("display_num : "+ request.getParameter("display_num"));
		
		request.setAttribute("showPage", "adm/dealerDetailView.jsp");
		forward = new ActionForward("template.jsp",false);
		
		return forward;
	}

}
