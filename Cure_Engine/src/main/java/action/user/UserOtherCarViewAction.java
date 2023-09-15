package action.user;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.user.UserOtherCarViewService;
import vo.ActionForward;
import vo.Car;
import vo.Code;
import vo.PageInfo;

public class UserOtherCarViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String dealer_id = request.getParameter("dealer_id");
		String dealer_name = request.getParameter("dealer_name");
		
		UserOtherCarViewService userOtherCarViewService = new UserOtherCarViewService();
		
		/* 1. 페이징처리 --------------------------------------------------------------------------*/
		int page = 1;
		int limit = 5;
		int pageGroupSize = 10;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int listCount = userOtherCarViewService.getListCount(dealer_id);
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
		
		/*---------------------------------------------------------------------------------------*/		
		
		ArrayList<Car> otherCarList = userOtherCarViewService.getOtherCarList(dealer_id, page, limit);
		ArrayList<Code> allCode = userOtherCarViewService.getAllCode();
		
		request.setAttribute("otherCarList", otherCarList);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("allCode", allCode);
		request.setAttribute("dealer_name", dealer_name);
		request.setAttribute("dealer_id", dealer_id);
		
		request.setAttribute("showPage", "user/otherCarListForm.jsp");
		forward = new ActionForward("template.jsp", false);	
		
		
		return forward;
	}

}
