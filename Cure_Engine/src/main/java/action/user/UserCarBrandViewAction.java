package action.user;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.user.UserCarBrandViewService;
import vo.ActionForward;
import vo.Car;
import vo.Code;

public class UserCarBrandViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String car_brand = request.getParameter("car_brand");
		System.out.println("파라미터로 넘겨받은 car_id : " + car_brand);
		
		UserCarBrandViewService carBrandViewService = new UserCarBrandViewService();
		ArrayList<Car> carInfo = carBrandViewService.getCarBrandInfo(car_brand);
		ArrayList<Code> allCode = carBrandViewService.getAllCode();
		
		String brand_name = "";
		for (Code code : allCode) {
            if (car_brand.equals(code.getCode_name())) {
            	brand_name = code.getCode_value();
            }
        }
		
		request.setAttribute("allCarList", carInfo);
		request.setAttribute("allCode", allCode);
		request.setAttribute("brand_name", brand_name);
		
		//form에 뿌리기
		request.setAttribute("showPage", "user/carBrandForm.jsp");
		forward = new ActionForward("template.jsp", false);	

		
		return forward;
	}

}
