package action.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.board.QnA_BoardListService;
import svc.board.QnA_BoardWriteFormService;
import vo.ActionForward;
import vo.Car;
import vo.QnABoard;
import vo.User;
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
		ArrayList<QnABoard> boardList = boardListService.selectBoardList(page,limit);			
				
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
		
		//코드마스터에 등록된 제목을 불러오기 위해 모든 코드 가져오기
		ArrayList<Code> allCode = boardListService.getAllCode();	
		
		//차량문의와 딜러 매칭을 위해 등록된 자동차 리스트 가져오기
		ArrayList<Car> allCarList = boardListService.getAllCarList();
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("boardList", boardList);
		request.setAttribute("allCode", allCode);
		request.setAttribute("carList", allCarList);
		
		request.setAttribute("showPage", "user/qna/qna_Board_List.jsp");
		forward = new ActionForward("template.jsp", false);	
		return forward;
	}

}
