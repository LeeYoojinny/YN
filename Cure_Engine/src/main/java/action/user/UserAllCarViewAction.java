package action.user;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.user.UserAllCarViewService;
import vo.ActionForward;
import vo.Car;
import vo.Code;
import vo.PageInfo;

public class UserAllCarViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
ActionForward forward = null;
		
		//먼저 DB에서 등록 된 Car 정보 받아오기
		UserAllCarViewService userAllCarViewService = new UserAllCarViewService();
		
		
		/* 1. 페이징처리 --------------------------------------------------------------------------*/
		int page = 1;
		int limit = 12;
		int pageGroupSize = 10;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int listCount = userAllCarViewService.getListCount();
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
		ArrayList<Car> allCarList = userAllCarViewService.getAllCarList(page,limit);
		ArrayList<Code> allCode = userAllCarViewService.getAllCode();
		
		request.setAttribute("pageInfo", pageInfo);
		//받아온 Car 객체들을 속성에 담기
		request.setAttribute("allCarList", allCarList);
		request.setAttribute("allCode", allCode);
		
		//form에 뿌리기
		request.setAttribute("showPage", "user/allCarListForm.jsp");
		forward = new ActionForward("template.jsp", false);	

		return forward;
	}

}
