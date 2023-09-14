package action.adm;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.adm.AdminCustDetailViewService;
import vo.ActionForward;
import vo.Code;
import vo.QnABoard;
import vo.User;

public class AdminCustDetailViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String user_id = request.getParameter("user_id");
		
		AdminCustDetailViewService custDetailViewService = new AdminCustDetailViewService();
		User custInfo = custDetailViewService.getCustInfo(user_id);
		ArrayList<QnABoard> custQna = custDetailViewService.getCustQna(user_id);
		ArrayList<Code> allCode = custDetailViewService.getAllCode();
		
		
		request.setAttribute("custInfo", custInfo);
		request.setAttribute("boardList", custQna);
		request.setAttribute("allCode", allCode);
		request.setAttribute("showPage", "adm/custDetailView.jsp");
		forward = new ActionForward("template.jsp",false);
		
		return forward;
	}

}
