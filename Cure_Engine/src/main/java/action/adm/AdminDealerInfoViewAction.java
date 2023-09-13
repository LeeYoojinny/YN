package action.adm;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.adm.AdminDealerInfoViewSevice;
import vo.ActionForward;
import vo.User;

public class AdminDealerInfoViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		AdminDealerInfoViewSevice dealerInfoViewSevice = new AdminDealerInfoViewSevice();
		User userInfo = dealerInfoViewSevice.getUserInfo(user_id);
		
		if(userInfo != null) {
			String birth = userInfo.getUser_birth();
			System.out.println(birth);
			
			String[] user_birth = birth.split("-");
			String user_birth_y = user_birth[0];
			String user_birth_m = user_birth[1];
			String user_birth_d = user_birth[2];
			
			System.out.println("gender? : "+userInfo.getUser_gender());
			
			request.setAttribute("user_birth_y", user_birth_y);
			request.setAttribute("user_birth_m", user_birth_m);
			request.setAttribute("user_birth_d", user_birth_d);
			
			request.setAttribute("userInfo", userInfo);
			
			request.setAttribute("showPage", "adm/dealerInfoView.jsp");
			forward = new ActionForward("template.jsp", false);	
			
		}else {
			response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('페이지 로드에 실패했습니다.');");
            out.println("history.back();"); 
            out.println("</script>");
		}
		
		return forward;
	}

}
