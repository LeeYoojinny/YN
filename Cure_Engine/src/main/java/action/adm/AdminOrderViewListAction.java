package action.adm;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.adm.AdminOrderViewListService;
import vo.ActionForward;
import vo.Car;
import vo.Order;
import vo.Code;
import vo.PageInfo;
import vo.Payment;

public class AdminOrderViewListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String dealer_id = (String)session.getAttribute("user_id");
		String user_category = (String)session.getAttribute("user_category");		
		
		AdminOrderViewListService orderViewListService = new AdminOrderViewListService();
		
		//페이징처리
		int page = 1;
		int limit = 5;
		int pageGroupSize = 10;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		//페이징처리를 위해 리스트 개수 받아오기
		int listCount = orderViewListService.getOrderListCount(dealer_id,user_category);
		
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
		
		ArrayList<Car> carList = orderViewListService.getOrderCarList(dealer_id,user_category,page,limit);
		ArrayList<Order> orderList = orderViewListService.getOrderList(dealer_id,user_category,page,limit);
		ArrayList<Payment> allpayList = orderViewListService.getAllPayList();
		ArrayList<Code> allCode = orderViewListService.getAllCode();
		
		request.setAttribute("carList", carList);
		request.setAttribute("orderList", orderList);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("allpayList", allpayList);
		request.setAttribute("allCode", allCode);
		
		request.setAttribute("showPage", "adm/myCarOrderList.jsp");
		forward = new ActionForward("template.jsp", false);	
		
		return forward;
	}

}
