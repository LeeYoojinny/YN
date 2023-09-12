package action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.board.Notice_BoardUpdateFormService;
import vo.ActionForward;
import vo.NoticeBoard;

public class Notice_BoardUpdateFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String notice_num = request.getParameter("notice_num");
		
		Notice_BoardUpdateFormService notice_BoardUpdateFormService = new Notice_BoardUpdateFormService();
		NoticeBoard notice = notice_BoardUpdateFormService.getBoardInfo(notice_num);
		
		request.setAttribute("board", notice);
		
		request.setAttribute("showPage", "user/notice/notice_updateForm.jsp");
		forward = new ActionForward("template.jsp", false);	
		
		return forward;
	}

}
