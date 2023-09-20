package action.cust;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.cust.CustomerOrderListService;
import vo.ActionForward;
import vo.Car;
import vo.Code;
import vo.Order;
import vo.PageInfo;
import vo.Payment;

public class CustomerOrderListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		CustomerOrderListService orderListService = new CustomerOrderListService();
		
		//페이징처리
		int page = 1;
		int limit = 5;
		int pageGroupSize = 10;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		//페이징처리를 위해 리스트 개수 받아오기
		int listCount = orderListService.getOrderListCount(user_id);
		
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
		
		ArrayList<Car> carList = orderListService.getOrderCarList(user_id,page,limit);
		ArrayList<Order> orderList = orderListService.getOrderList(user_id,page,limit);
		
		// Payment db를 가져오기 위해 ordernum 필드 값을 String 배열로 가져오기
		List<String> ordernumList = null;
		ArrayList<Payment> allpayList = null;
        if(orderList != null) {
        	ordernumList = new ArrayList<>();
            for (Order order : orderList) {
                ordernumList.add(order.getOrdernum());
            }
            allpayList = orderListService.getAllPayList(ordernumList);
        }
        
        //리뷰작성 유무를 확인하기 위해 리뷰테이블 db 가져오기
        List<String> reviewOrdernum = orderListService.getOrdernum(user_id);
        

		ArrayList<Code> allCode = orderListService.getAllCode();
		
		request.setAttribute("carList", carList);
		request.setAttribute("orderList", orderList);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("allpayList", allpayList);
		request.setAttribute("allCode", allCode);
		request.setAttribute("reviewOrdernum", reviewOrdernum);
		
		request.setAttribute("showPage", "customer/myOrderList.jsp");
		forward = new ActionForward("template.jsp", false);	
		
		return forward;
	}

}
