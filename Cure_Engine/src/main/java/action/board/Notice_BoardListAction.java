package action.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.board.Notice_BoardListService;
import vo.ActionForward;
import vo.Code;
import vo.NoticeBoard;
import vo.PageInfo;

public class Notice_BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int page = 1;
		int limit = 10;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
	
		Notice_BoardListService notice_boardListService = new Notice_BoardListService();

		int listCount = notice_boardListService.getListCount();
		ArrayList<NoticeBoard> boardList = notice_boardListService.selectBoardList(page,limit);
		ArrayList<Code> allCode = notice_boardListService.getAllCode();				
		int maxPage = (int)((double)listCount/limit+0.95);
		
		int startPage = (((int)((double)page/10+ 0.9))-1)*10+1;
		
		int endPage = startPage+10-1;
		if(endPage > maxPage) endPage = maxPage;
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("boardList", boardList);
		request.setAttribute("allCode", allCode);
		
		request.setAttribute("showPage", "user/notice/notice_Board_List.jsp");
		forward = new ActionForward("template.jsp", false);	
		return forward;
		
	}

}
