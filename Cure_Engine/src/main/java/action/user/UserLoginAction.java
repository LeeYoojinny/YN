
package action.user;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.user.UserLoginService;
import util.SHA256;
import vo.ActionForward;
import vo.User;
import vo.Wishlist;

public class UserLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String user_id = request.getParameter("user_id");		
		String user_pw = request.getParameter("user_pw");
		
		//String checkbox = request.getParameter("checkbox");//아이디 저장 체크 여부
		
		//DTO=VO 
		User user = new User(); //기본값으로 채워진 생성자
		user.setUser_id(user_id);
		user.setUser_pw(SHA256.encodeSHA256(user_pw));//[암호화 방법-1]암호화된 상태로 set 
		
		System.out.println("입력한 아이디 : "+user_id);
		System.out.println("암호화된 패스워드 : "+ user.getUser_pw());
		
		UserLoginService userLoginService= new UserLoginService();
		String use_YN = userLoginService.login(user);
		
		if(use_YN == null || use_YN.equals("N")) { //로그인 실패하면
			response.setContentType("text/html; charset=utf-8");
			
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디나 비밀번호가 일치하지 않습니다.');");
			out.println("location.href='userLogin.usr'"); //로그인폼보기 다시 요청
			out.println("</script>");
			
		}else { //로그인에 성공했다면
			Cookie cookieU_id = new Cookie("user_id", user_id);
			System.out.println("user_id Cookie 객체 생성");
			
			Cookie cookieCheckbox = new Cookie("checkbox", "checked");
			
			/*
			if(checkbox != null) {//id저장에 체크 했다면
				response.addCookie(cookieU_id); //반드시 response에 담아서 클라이언트 측으로 보내야 함
				response.addCookie(cookieCheckbox);
			}else {
				cookieU_id.setMaxAge(0); //쿠키 즉시 삭제
				cookieCheckbox.setMaxAge(0);
				
				response.addCookie(cookieU_id); //반드시 response에 담아서 클라이언트 측으로 보내야 함
				response.addCookie(cookieCheckbox);
			}
			*/

			//입력한 id로 회원정보 가져오기(이유? session에 저장 해 공유하기 위해)
			User userInfo = userLoginService.getUserInfo(user_id);
			ArrayList<Wishlist> userWish = userLoginService.getWishInfo(user_id);
			
			System.out.println("user의 카테고리 : " + userInfo.getUser_category());
			
			/* 로그인에 성공하면
			 * session영역에 속성으로 저장하여 공유
			 * u_id, u_password, u_grade, u_name, u_email 속성으로 저장
			 */
			HttpSession session = request.getSession();
			session.setAttribute("user_category", userInfo.getUser_category()); //session.setAttribute("u_id", userInfo.getId());
			session.setAttribute("user_id", user_id);
			session.setAttribute("user_name", userInfo.getUser_name()); //구매할 때 등급으로 세일비율 얻어와 사용
			session.setAttribute("user_email", userInfo.getUser_email());
			session.setAttribute("user_phone", userInfo.getUser_phone());
			
			
			if(userWish != null) {
				session.setAttribute("wishlist", userWish);
				System.out.println("wishlist 세션 저장");
			}
			
			session.setMaxInactiveInterval(12*60*60); //session 유지시간을 12시간으로 설정
			
			forward = new ActionForward("index.jsp",true);
		}
		return forward;
	}

}
