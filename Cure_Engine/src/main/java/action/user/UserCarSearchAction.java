package action.user;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.user.UserCarSearchService;
import vo.ActionForward;
import vo.Car;

public class UserCarSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//체크박스는 null이거나 다중값으로 올 수 있으므로 
		String car_brand = "X";
		String car_color = "X";
		String car_type = "X";
		
		String valueCheck = request.getParameter("car_brand");
		if(valueCheck != null) {
			String[] tpm_car_brand = request.getParameterValues("car_brand");
			car_brand = String.join(",", tpm_car_brand);
		}
		
		valueCheck = request.getParameter("car_color");
		if(valueCheck != null) {
			String[] tpm_car_color = request.getParameterValues("car_color");
			car_color = String.join(",", tpm_car_color);
		}
		
		valueCheck = request.getParameter("car_type");
		if(valueCheck != null) {
			String[] tpm_car_type = request.getParameterValues("car_type");
			car_type = String.join(",", tpm_car_type);
		}
		
		System.out.println("car_brand action에 넘어온 값 : " +car_brand);
		System.out.println("car_color action에 넘어온 값 : " +car_color);
		System.out.println("car_type action에 넘어온 값 : " +car_type);
		
		
		//주행거리 null값 처리
		String tpm_car_distance = request.getParameter("car_distance");
		int car_distance = 0;
		
		System.out.println("action에 넘어온 파라미터값 : " +tpm_car_distance);
		
		if (tpm_car_distance != null && !tpm_car_distance.isEmpty()) {
		    	car_distance = Integer.parseInt(tpm_car_distance); // 문자열을 정수로 변환
		}

		System.out.println("car_distance action에 넘어온 값 : " +car_distance);
		
		//주행거리 null값 처리
		String tpm_start_price = request.getParameter("start_price");
		String tpm_end_price = request.getParameter("end_price");
		int start_price = 0;
		int end_price = 0;
		
		System.out.println("action에 넘어온 파라미터값 : " +tpm_start_price);
		System.out.println("action에 넘어온 파라미터값 : " +tpm_end_price);
		
		if (tpm_start_price != null && !tpm_start_price.isEmpty()
				&& tpm_end_price != null && !tpm_end_price.isEmpty()) {
				//파라미터로 넘어온 문자열을 정수로 변환
		    	start_price = Integer.parseInt(tpm_start_price);
		    	end_price = Integer.parseInt(tpm_end_price);
		}

		System.out.println("start_price action에 넘어온 값 : " +start_price);
		System.out.println("end_price action에 넘어온 값 : " +end_price);
				
		UserCarSearchService userCarSearchService = new UserCarSearchService();
		ArrayList<Car> resultCar = userCarSearchService.searchResult
				(car_brand,car_color,car_type,car_distance,start_price,end_price);
		
		//받아온 Car 객체들을 속성에 담기
		request.setAttribute("resultCar", resultCar);
		
		//form에 뿌리기
		request.setAttribute("showPage", "user/searchResultForm.jsp");
		forward = new ActionForward("template.jsp", false);	
		
		
		return forward;
	}

}
