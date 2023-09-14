package action.adm;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.adm.AdminCustExpireListService;
import vo.ActionForward;
import vo.User;

public class AdminCustExpireListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		AdminCustExpireListService custExpireListService = new AdminCustExpireListService();
		ArrayList<User> expireCust = custExpireListService.getExpireList();
		
		return forward;
	}

}
