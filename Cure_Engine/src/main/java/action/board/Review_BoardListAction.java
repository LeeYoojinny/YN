package action.board;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.board.Review_BoardListService;
import vo.ActionForward;
import vo.Car;
import vo.Order;
import vo.PageInfo;
import vo.QnABoard;
import vo.ReviewBoard;

public class Review_BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int page = 1;
		int limit = 5;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		Review_BoardListService review_BoardListService = new Review_BoardListService();
		
		//게시물 총 개수 받아오기
		int listCount = review_BoardListService.getListCount();
		
		int maxPage = (int) Math.ceil((double) listCount / limit);
		
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
				
		//게시물 내용 받아오기
		ArrayList<ReviewBoard> boardList = review_BoardListService.selectBoardList(page,limit);			
		
		//자동차 대표이미지 불러오기위해 car_id만 배열로 만들기
		List<String> car_id_List = new ArrayList<>();
        for (ReviewBoard board : boardList) {
        	car_id_List.add(board.getCar_id());
        }
		
        ArrayList<Car> carList = review_BoardListService.selectAllCarInfo(car_id_List);		
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("boardList", boardList);
		request.setAttribute("carList", carList);
		
		request.setAttribute("showPage", "user/review/review_Board_List.jsp");
		forward = new ActionForward("template.jsp", false);	
		
		return forward;
	}

}
