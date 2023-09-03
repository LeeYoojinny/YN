package action.cust;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.cust.CustomerUnlikeThisService;
import vo.ActionForward;
import vo.Wishlist;

public class CustomerUnlikeThisAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//세션에 저장된 사용자 아이디 가져오기
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		//파라미터로 넘겨받은 값 가져오기
		String car_id = request.getParameter("car_id");
		
		CustomerUnlikeThisService customerUnlikeThisService = new CustomerUnlikeThisService();
		int carResult = customerUnlikeThisService.carUnlikeThis(car_id);
		int wishResult = customerUnlikeThisService.wishDelete(car_id,user_id);
		
		//서비스에서 메소드 실행 성공 했다면
		if(carResult > 0 && wishResult > 0) {
			//현재 wishlist 가져오기
			ArrayList<Wishlist> userWish = customerUnlikeThisService.getWishInfo(user_id);
			
			//세션에 업데이트
			if(userWish != null) {
				session.setAttribute("wishlist", userWish);
				System.out.println("wishlist 세션 업데이트");
			}
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관심상품에서 제거 되었습니다.');");
			out.println("location.href='allCarListView.usr';");
			out.println("</script>");
		}else {//서비스에서 메소드 실행 실패했다면
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관심상품 제거에 실패했습니다.');");
			out.println("</script>");
		}
		
		return forward;
	}

}
