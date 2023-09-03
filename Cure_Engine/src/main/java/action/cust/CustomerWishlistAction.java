package action.cust;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.cust.CustomerWishlistService;
import vo.ActionForward;
import vo.Wishlist;
import vo.Car;

public class CustomerWishlistAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//세션에 저장된 wishlist 불러오기
		HttpSession session = request.getSession();
		ArrayList<Wishlist> myWishlist = (ArrayList<Wishlist>)session.getAttribute("wishlist");
		
		if(myWishlist == null) {
			myWishlist = new ArrayList<Wishlist>();
		}
		
		//wishlist에서 car_id만 가져오기
		String[] all_car_id = new String[myWishlist.size()];
		if (myWishlist != null) {
		    for (int i = 0; i < myWishlist.size(); i++) {
		    	all_car_id[i] = myWishlist.get(i).getCar_id();
		    }
		}
		
		for(String all:all_car_id) {
			System.out.println("배열로 저장된 car_id : "+all);
		}
		
		CustomerWishlistService customerWishlistService = new CustomerWishlistService();
		ArrayList<Car> wishCar = customerWishlistService.getWishCar(all_car_id);
		
		System.out.println("action으로 돌아옴");
		
		request.setAttribute("myWishCar", wishCar);
		
		request.setAttribute("showPage", "customer/myWishlistForm.jsp");
		forward = new ActionForward("template.jsp", false);	
		
		return forward;
	}

}
