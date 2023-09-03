package action.cust;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.cust.CustomerLikeThisService;
import vo.ActionForward;
import vo.Wishlist;

public class CustomerLikeThisAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//세션에 저장된 사용자 아이디 가져오기
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		//파라미터로 넘겨받은 값 받아오기
		String car_id = request.getParameter("car_id");
		int car_price = Integer.parseInt(request.getParameter("car_price"));
		String car_image1 = request.getParameter("car_image1");
		
		//콘솔로 값 체크
		System.out.println("로그인한 user_id : "+user_id);
		System.out.println("car_id 파라미터 값 : " + car_id);
		System.out.println("car_price 파라미터 값 : " + car_price);
		System.out.println("car_image1 파라미터 값 : " + car_image1);
		
		//서비스에서 실행될 메소드 : Car 테이블에 car_like+1, Wishlist 테이블에 db추가
		CustomerLikeThisService customerLikeThisService = new CustomerLikeThisService();
		int carResult = customerLikeThisService.carLikeThis(car_id);
		int wishResult = customerLikeThisService.wishInsert(car_id,car_image1,car_price,user_id);
		
		//서비스에서 메소드 실행 성공 했다면
		if(carResult > 0 && wishResult > 0) {
			//현재 wishlist 가져오기
			ArrayList<Wishlist> userWish = customerLikeThisService.getWishInfo(user_id);
			
			//세션에 업데이트
			if(userWish != null) {
				session.setAttribute("wishlist", userWish);
				System.out.println("wishlist 세션 업데이트");
			}
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관심상품 등록 되었습니다.');");
			out.println("location.href='allCarListView.usr';");
			out.println("</script>");
		}else {//서비스에서 메소드 실행 실패했다면
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관심상품 등록 실패했습니다.');");
			out.println("</script>");
		}
		
		return forward;
	}

}
