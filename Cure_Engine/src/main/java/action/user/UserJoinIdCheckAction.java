package action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.user.UserJoinIdCheckService;
import vo.ActionForward;

public class UserJoinIdCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String inputId = request.getParameter("inputId");
		System.out.println("아이디 중복확인을 위해 넘어온 id값 : "+inputId);
		
		UserJoinIdCheckService userJoinIdCheckService = new UserJoinIdCheckService();
		boolean idCheckResult = userJoinIdCheckService.idCheckResult(inputId);

		request.setAttribute("checkOkId", inputId);		
		request.setAttribute("idCheckResult", idCheckResult);
		
		System.out.println("request에 정보를 담음");
		
		forward = new ActionForward("joinIdCheck.jsp",false);
		
		return forward;
	}

}
