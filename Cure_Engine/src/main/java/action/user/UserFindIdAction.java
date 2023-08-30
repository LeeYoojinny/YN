package action.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.user.UserIdFindService;
import vo.ActionForward;
import vo.User;

public class UserFindIdAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String user_name = request.getParameter("user_name");

		String user_birth_y = request.getParameter("user_birth_y");
		String user_birth_m = request.getParameter("user_birth_m");
		String user_birth_d = request.getParameter("user_birth_d");
		
		String user_birth = user_birth_y+"-"+user_birth_m+"-"+user_birth_d;
		
		String user_email = request.getParameter("user_email");
		
		User findIdInfo = new User();
		findIdInfo.setUser_name(user_name);
		findIdInfo.setUser_birth(user_birth);
		findIdInfo.setUser_email(user_email);
		
		UserIdFindService userIdFindService = new UserIdFindService();
		String getId = userIdFindService.findId(findIdInfo);
				
		if(getId == null) { //email로 검색했을 때 받아오는 Member 객체가 없다면
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('입력하신 정보와 맞는 회원ID가 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
			
		}else { 
			request.setAttribute("user_id", getId);
			
			System.out.println("받아온 id값 : " + getId);
			
			request.setAttribute("showPage","user/findIdComplete.jsp");
			forward = new ActionForward("template.jsp", false);
		}
		
		
		return forward;
	}

}
