package action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;

public class UserLogOutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
ActionForward forward = null;
		
		/**
		 * session 영역의 속성들을 제거
		 */
		
		HttpSession session =  request.getSession();
		
		//방법1 : 세션의 모든 속성들 삭제
		//session.invalidate();

		//방법2 : 세션의 영역을 하나씩 삭제
		session.removeAttribute("user_category");
		session.removeAttribute("user_id");
		session.removeAttribute("user_name");
		session.removeAttribute("user_phone");
		session.removeAttribute("user_email");
		session.removeAttribute("wishlist");
		
		forward = new ActionForward("index.jsp", true);
		
		return forward;
	}

}
