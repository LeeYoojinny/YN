package action.user;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.user.UserAllCarViewService;
import vo.ActionForward;
import vo.Car;

public class UserAllCarViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//먼저 DB에서 등록 된 Car 정보 받아오기
		UserAllCarViewService userAllCarViewService = new UserAllCarViewService();
		ArrayList<Car> allCarList = userAllCarViewService.getAllCarList();
		
		//받아온 Car 객체들을 속성에 담기
		request.setAttribute("allCarList", allCarList);
		
		//form에 뿌리기
		request.setAttribute("showPage", "user/allCarListForm.jsp");
		forward = new ActionForward("template.jsp", false);	

		return forward;
	}

}
