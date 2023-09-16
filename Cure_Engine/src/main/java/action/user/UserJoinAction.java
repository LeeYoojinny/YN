/**
 * 
 */
package action.user;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.user.UserJoinService;
import vo.ActionForward;
import vo.User;
import vo.Coupon;
import util.SHA256;

public class UserJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String user_category = request.getParameter("user_category");		
		String user_id = request.getParameter("user_id");		
		String user_pw = request.getParameter("user_pw");
		String user_name = request.getParameter("user_name");
		
		String user_birth_y = request.getParameter("user_birth_y");
		String user_birth_m = request.getParameter("user_birth_m");
		String user_birth_d = request.getParameter("user_birth_d");
		
		//넘어온 년,월,일을 '-'으로 연결해준 후 insert
		String user_birth = user_birth_y+"-"+user_birth_m+"-"+user_birth_d;
		
		String user_gender = request.getParameter("user_gender");
		String user_phone = request.getParameter("user_phone");
		String user_email = request.getParameter("user_email");
		int user_zipcode = Integer.parseInt(request.getParameter("user_zipcode"));
		String user_address1 = request.getParameter("user_address1");
		String user_address2 = request.getParameter("user_address2");
		
		
		User user = new User(user_category,user_id,user_pw,user_name,user_birth,user_gender,user_phone,
				user_email,user_zipcode,user_address1,user_address2);
		
		UserJoinService userJoinService = new UserJoinService();
		boolean isUserJoinSuccess = userJoinService.userJoin(user);
		
		if(isUserJoinSuccess == false) {
			//회원가입 실패 알림창 → 이전으로
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원가입에 실패하였습니다. 다시 시도해주세요.');");
			out.println("history.back();"); //로그인폼보기 다시 요청
			out.println("</script>");
		}else {//회원가입 성공 알림창 → '로그인 폼보기' 요청(리다이렉트 방식)
			//회원가입 시 자동으로 발급되는 쿠폰
			String couponNum = SHA256.getRandomPassword(10);
			Coupon joinCoupon = new Coupon();
			joinCoupon.setCoupon_id(couponNum);
			joinCoupon.setCoupon_name("Welcome Coupon");
			joinCoupon.setUser_id(user_id);
			joinCoupon.setDiscount_rate(50);
			
			boolean isCouponSuccess = userJoinService.userJoinCoupon(joinCoupon);
			if(isCouponSuccess == true) {				
				//로그인폼보기 요청 전 이전에 저장한 쿠키객체 삭체
				Cookie cookieU_id = new Cookie("u_id", "");
				cookieU_id.setMaxAge(0);
				response.addCookie(cookieU_id);
				
				Cookie cookieCheckbox = new Cookie("checkbox", "");
				cookieCheckbox.setMaxAge(0);
				response.addCookie(cookieCheckbox);
				
				//성공알림창 → 로그인폼보기
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('회원가입에 성공했습니다.');");
				out.println("location.href='userLogin.usr'"); //로그인폼보기 다시 요청
				out.println("</script>");
			}else {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('회원가입에 실패하였습니다. 다시 시도해주세요.');");
				out.println("history.back();"); //로그인폼보기 다시 요청
				out.println("</script>");
			}
		}
		
		return forward;
	}

}
