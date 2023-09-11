package action.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.board.QnA_BoardListService;
import svc.board.QnA_BoardWriteFormService;
import vo.ActionForward;
import vo.Board;
import vo.Code;
import vo.PageInfo;

public class QnA_BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int page = 1;
		int limit = 10;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		QnA_BoardListService boardListService = new QnA_BoardListService();
		int listCount = boardListService.getListCount();
		ArrayList<Board> boardList = boardListService.selectBoardList(page,limit);
		ArrayList<Code> allCode = boardListService.getAllCode();
				
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
		
		request.setAttribute("showPage", "user/qna/qna_Board_List.jsp");
		forward = new ActionForward("template.jsp", false);	
		return forward;
	}

}
