package action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.board.Notice_BoardViewService;
import vo.ActionForward;
import vo.NoticeBoard;

public class Notice_BoardViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String notice_num = request.getParameter("notice_num");
		
		Notice_BoardViewService notice_BoardViewService = new Notice_BoardViewService();
		NoticeBoard notice = notice_BoardViewService.getBoardView(notice_num);
		
		request.setAttribute("board", notice);
		request.setAttribute("list_num", request.getParameter("list_num"));
		
		request.setAttribute("showPage", "user/notice/notice_View.jsp");
		forward = new ActionForward("template.jsp", false);	

		
		return forward;
	}

}
