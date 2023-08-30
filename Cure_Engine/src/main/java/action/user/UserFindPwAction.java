package action.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.user.UserIdFindService;
import svc.user.UserPwFindService;
import util.MailUtil;
import util.SHA256;
import vo.ActionForward;
import vo.User;

public class UserFindPwAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String user_id = request.getParameter("user_id");
		
		String user_birth_y = request.getParameter("user_birth_y");
		String user_birth_m = request.getParameter("user_birth_m");
		String user_birth_d = request.getParameter("user_birth_d");
		
		String user_birth = user_birth_y+"-"+user_birth_m+"-"+user_birth_d;
		
		String user_email = request.getParameter("user_email");
		
		User findPwInfo = new User();
		findPwInfo.setUser_id(user_id);
		findPwInfo.setUser_birth(user_birth);
		findPwInfo.setUser_email(user_email);
		
		UserPwFindService userPwFindService = new UserPwFindService();
		boolean userYN = userPwFindService.findPw(findPwInfo);
				
		if(userYN == false) { //검색했을 때 받아오는 User 객체가 없다면
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('입력하신 정보와 맞는 회원이 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}else {//User 객체를 찾았다면
			//10자리의 임시비밀번호 입력
			String random_password = SHA256.getRandomPassword(10);
			request.setAttribute("random_password", random_password);
			
			boolean istmpPwUpdateSuccess = userPwFindService.updateTmpPw(findPwInfo.getUser_id(),random_password);
			if(istmpPwUpdateSuccess == false) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('임시 비밀번호 발급에 실패 했습니다. 다시 시도해주세요.');");
				out.println("history.back();");
				out.println("</script>");
			}else {
				System.out.println("DB에 임시비밀번호로 update 성공");//콘솔확인용
				
				//메일로 임시비밀번호 메일 보내기
				request.setAttribute("input_email", findPwInfo.getUser_email());
				
				String from = "yoojin266@gmail.com";
				String to = findPwInfo.getUser_email();
				String subject = "Cure Engine 임시비밀번호 발급";
				String content = "임시 비밀번호는 "+random_password+ " 입니다.";
				
				MailUtil mail = new MailUtil();
				mail.sendEmail(from, to, subject, content);
				
				request.setAttribute("showPage","user/findPwComplete.jsp");
				forward = new ActionForward("template.jsp",false);
				
				System.out.println("action에서 메일보내기 성공");
			}
		}
		
		
		
		return forward;
	}

}
