package action.adm;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.adm.AdminDealerDetailViewService;
import vo.ActionForward;
import vo.Code;
import vo.User;

public class AdminDealerDetailViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String user_id = request.getParameter("user_id");
		
		AdminDealerDetailViewService dealerDetailViewService = new AdminDealerDetailViewService();
		User dealerInfo = dealerDetailViewService.getDealerInfo(user_id);
		ArrayList<Code> allCode = dealerDetailViewService.getAllCode();
		
		request.setAttribute("dealerInfo", dealerInfo);
		
		request.setAttribute("display_num", request.getParameter("display_num"));
		System.out.println("display_num : "+ request.getParameter("display_num"));
		
		request.setAttribute("showPage", "adm/dealerDetailView.jsp");
		forward = new ActionForward("template.jsp",false);
		
		return forward;
	}

}
