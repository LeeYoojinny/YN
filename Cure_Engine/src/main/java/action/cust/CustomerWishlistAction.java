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
import vo.Code;
import vo.PageInfo;

public class CustomerWishlistAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;	
		
		//세션에 저장된 user_id 불러오기
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		System.out.println("위시리스트를 얻기위한 user_id : "+user_id);
				
		CustomerWishlistService customerWishlistService = new CustomerWishlistService();
		
		/* 1. 페이징처리 --------------------------------------------------------------------------*/
		int page = 1;
		int limit = 5;
		int pageGroupSize = 10;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int listCount = customerWishlistService.getListCount(user_id);
		//총 페이지 수 계산
		int maxPage = (int) Math.ceil((double) listCount / limit);
		
		// 현재 페이지 그룹을 계산
		int currentPageGroup = (int) Math.ceil((double) page / pageGroupSize);

		// 시작 페이지와 끝 페이지 계산
		int startPage = (currentPageGroup - 1) * pageGroupSize + 1;
		int endPage = startPage + pageGroupSize - 1;
		if(endPage > maxPage) endPage = maxPage;
		
		//페이징처리를 위한 페이지정보
		PageInfo pageInfo = new PageInfo();
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);	
		
		/*---------------------------------------------------------------------------------------*/
		
		/* 2. user_id로 관심상품 리스트 가져오기 --------------------------------------------------------*/
		ArrayList<Wishlist> myWish = customerWishlistService.getMyWishlist(user_id);
				
		if(myWish == null) {
			myWish = new ArrayList<Wishlist>();
			System.out.println("myWish가 null임");
		}
		
		//wishlist에서 car_id만 가져오기
		String[] all_car_id = new String[myWish.size()];
		if (myWish != null) {
		    for (int i = 0; i < myWish.size(); i++) {
		    	all_car_id[i] = myWish.get(i).getCar_id();
		    }
		}
		
		//콘솔 확인
		for(String all:all_car_id) {
			System.out.println("배열로 저장된 car_id : "+all);
		}
		/*---------------------------------------------------------------------------------------*/
				
		/* 3. 관심상품 리스트로 자동차 정보 가저오기 --------------------------------------------------------*/
		ArrayList<Car> wishCar = customerWishlistService.getWishCar(all_car_id,page,limit);
		ArrayList<Code> allCode = customerWishlistService.getAllCode();
		
		System.out.println("action으로 돌아옴");
		
		request.setAttribute("myWishCar", wishCar);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("allCode", allCode);
		
		request.setAttribute("showPage", "customer/myWishlistForm.jsp");
		forward = new ActionForward("template.jsp", false);	
		
		return forward;
	}

}
