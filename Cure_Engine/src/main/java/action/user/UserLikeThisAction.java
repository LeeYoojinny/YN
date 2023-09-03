package action.user;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.user.UserLikeThisService;
import vo.ActionForward;
import vo.Wishlist;

public class UserLikeThisAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//세션에 저장된 사용자 아이디 가져오기
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		System.out.println("로그인한 user_id : "+user_id);
		
		//파라미터로 넘겨받은 값 받아오기
		String car_id = request.getParameter("car_id");
		int car_price = Integer.parseInt(request.getParameter("car_price"));
		String car_image1 = request.getParameter("car_image1");
		
		System.out.println("car_id 파라미터 값 : " + car_id);
		System.out.println("car_price 파라미터 값 : " + car_price);
		System.out.println("car_image1 파라미터 값 : " + car_image1);
		
		UserLikeThisService userLikeThisService = new UserLikeThisService();
		int carResult = userLikeThisService.carLikeThis(car_id);
		int wishResult = userLikeThisService.wishInsert(car_id,car_image1,car_price,user_id);
		
		System.out.println("action으로 돌아온 carResult 값 : " + carResult);
		System.out.println("action으로 돌아온 wishResult 값 : " + wishResult);
		
		if(carResult > 0 && wishResult > 0) {
			ArrayList<Wishlist> userWish = userLikeThisService.getWishInfo(user_id);
			
			if(userWish != null) {
				session.setAttribute("wishlist", userWish);
			}
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관심상품 등록 되었습니다.');");
			out.println("location.href='allCarListView.usr';");
			out.println("</script>");
			
			//forward = new ActionForward("allCarListView.usr", false);
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관심상품 등록 실패했습니다.');");
			out.println("</script>");
			
		}
		
		
		return forward;
	}

}
