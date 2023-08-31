package action.adm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.adm.AdminCarIdCheckService;
import svc.user.UserJoinIdCheckService;
import vo.ActionForward;

public class AdminCarIdCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String inputCarId = request.getParameter("inputCarId");
		
		AdminCarIdCheckService adminCarIdCheckService = new AdminCarIdCheckService();
		boolean CarIdCheckResult = adminCarIdCheckService.idCheckResult(inputCarId);

		request.setAttribute("checkOkCarId", inputCarId);		
		request.setAttribute("CarIdCheckResult", CarIdCheckResult);
		
		System.out.println("request에 정보를 담음");
		
		forward = new ActionForward("carIdCheck.jsp",false);
		
		
		return forward;
	}

}
