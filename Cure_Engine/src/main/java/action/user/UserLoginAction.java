
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
		ArrayList<String> loginCheck = userLoginService.login(user);
		String user_category = "";
		String useYN = "";
		
		if(loginCheck != null) {
			user_category = loginCheck.get(0);
			useYN = loginCheck.get(1);
		}
			
		if(loginCheck == null || (user_category.equals("customer") && useYN.equals("N"))) { //고객이 로그인 실패하면
			response.setContentType("text/html; charset=utf-8");
			
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디나 비밀번호가 일치하지 않습니다.');");
			out.println("location.href='userLogin.usr'"); //로그인폼보기 다시 요청
			out.println("</script>");
			
		}else if(user_category.equals("dealer") && useYN.equals("N")) {//딜러 등록 승인이 안됐다면
			response.setContentType("text/html; charset=utf-8");
			
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관리자 승인 후 이용 가능합니다.');");
			out.println("location.href='userLogin.usr'"); //로그인폼보기 다시 요청
			out.println("</script>");
		}else if(user_category.equals("refuse_dealer")) {//딜러 등록 승인이 안됐다면
				response.setContentType("text/html; charset=utf-8");				
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('관리자 승인 반려되었습니다. 재등록 후 이용 가능합니다.');");
				out.println("location.href='userLogin.usr'"); //로그인폼보기 다시 요청
				out.println("</script>");
						
		}else { //로그인에 성공했다면
			Cookie cookieU_id = new Cookie("user_id", user_id);
			System.out.println("user_id Cookie 객체 생성");
			
			Cookie cookieCheckbox = new Cookie("checkbox", "checked");
			
			User userInfo = userLoginService.getUserInfo(user_id);
			ArrayList<Wishlist> userWish = userLoginService.getWishInfo(user_id);
			
			System.out.println("user의 카테고리 : " + userInfo.getUser_category());
						
			HttpSession session = request.getSession();

			session.setAttribute("user_category", userInfo.getUser_category());
			session.setAttribute("user_id", user_id);
			session.setAttribute("user_name", userInfo.getUser_name());
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
