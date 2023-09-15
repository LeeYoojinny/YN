package action.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.board.QnA_SearchService;
import vo.ActionForward;
import vo.Code;
import vo.PageInfo;
import vo.QnABoard;

public class QnA_SearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int page = 1;
		int limit = 10;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		String search_option = request.getParameter("option");
		String keyword = request.getParameter("keyword");
		int searchMethod = 0;
		
		if(search_option.equals("titleCotent")) {
			searchMethod = 1;
		}else if(search_option.equals("user_id")){
			searchMethod = 2;
		}
		
		QnA_SearchService qna_SearchService = new QnA_SearchService();
		ArrayList<QnABoard> result = null;
		
		int listCount = qna_SearchService.getListCount(keyword,searchMethod);	
				
		int maxPage = (int)((double)listCount/limit+0.95);
		
		int startPage = (((int)((double)page/10+ 0.9))-1)*10+1;
		
		int endPage = startPage+10-1;
		if(endPage > maxPage) endPage = maxPage;
		
		//페이징처리를 위한 페이지정보
		PageInfo pageInfo = new PageInfo();
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);
		
		if(search_option.equals("titleCotent")) {
			result = qna_SearchService.getSearchResult_1(keyword);
		}else if(search_option.equals("user_id")){
			result = qna_SearchService.getSearchResult_2(keyword);
		}
		
		ArrayList<Code> allCode = qna_SearchService.getAllCode();
		
		request.setAttribute("result", result);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("allCode", allCode);
		
		request.setAttribute("showPage", "user/qna/qna_SearchList.jsp");
		forward = new ActionForward("template.jsp", false);	
		
		return forward;
	}

}
