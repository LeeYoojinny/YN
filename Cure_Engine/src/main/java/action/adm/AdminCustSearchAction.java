package action.adm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.adm.AdminCustSearchService;
import vo.ActionForward;
import vo.User;

public class AdminCustSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String keyword = request.getParameter("keyword");
		
		AdminCustSearchService custSearchService = new AdminCustSearchService();
		User searchUser = custSearchService.getSearchUser(keyword);
		
		request.setAttribute("searchUser", searchUser);
		request.setAttribute("display_num", request.getParameter("display_num"));
		System.out.println("display_num : "+request.getParameter("display_num"));
		
		request.setAttribute("showPage", "adm/custSearchView.jsp");
		forward = new ActionForward("template.jsp",false);
		
		return forward;
	}

}
