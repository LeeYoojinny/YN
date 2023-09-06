package action.cust;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.cust.CustomerRemoveWishService;
import vo.ActionForward;
import vo.Wishlist;

public class CustomerRemoveWishAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		String car_id = request.getParameter("car_ids");
		String[] car_ids = car_id.split(",");
		
		for(String id : car_ids) {
			System.out.println("파라미터 값으로 넘어온 car_ids : " + id);
		}
		
		CustomerRemoveWishService customerRemoveWishService = new CustomerRemoveWishService();
		int removeResult_wish = customerRemoveWishService.removeWish_wish(car_ids,user_id);
		int removeResult_car = customerRemoveWishService.removeLike_car(car_ids);
		
		if(removeResult_wish > 0 && removeResult_car >0) {
			ArrayList<Wishlist> userWish = customerRemoveWishService.getWishInfo(user_id);
			
			if(userWish != null) {
				session.setAttribute("wishlist", userWish);
				System.out.println("wishlist 세션 업데이트");
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 되었습니다.');");
				out.println("location.href='myWishlist.cust';");
				out.println("</script>");
			}
		}else {//서비스에서 메소드 실행 실패했다면
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 실패했습니다.');");
			out.println("history.back();");			
			out.println("</script>");
		}
		
		return forward;
	}

}
