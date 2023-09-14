package action.adm;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.adm.AdminCustomerListService;
import vo.ActionForward;
import vo.PageInfo;
import vo.User;

public class AdminCustomerListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int page = 1;
		int limit = 10;
		int pageGroupSize = 10;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		AdminCustomerListService customerListService = new AdminCustomerListService();
		
		//페이징처리를 위해 리스트 개수 받아오기
		int listCount = customerListService.getCustListCount();
		
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
		
		//모든 고객 정보 받아오기
		ArrayList<User> customerList = customerListService.getAllCustomer(page,limit);
		
		request.setAttribute("customerList", customerList);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("showPage", "adm/customerList.jsp");
		forward = new ActionForward("template.jsp",false);
		
		return forward;
	}

}
