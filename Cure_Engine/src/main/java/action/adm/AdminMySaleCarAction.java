package action.adm;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.adm.AdminMySaleCarService;
import vo.ActionForward;
import vo.Car;

public class AdminMySaleCarAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//세션에 저장된 딜러아이디 가져오기
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		//먼저 DB에서 등록 된 Car 정보 받아오기
		AdminMySaleCarService adminMySaleCarService = new AdminMySaleCarService();
		ArrayList<Car> mySaleCarList = adminMySaleCarService.getmySaleCarList(user_id);
		
		//받아온 Car 객체들을 속성에 담기
		request.setAttribute("mySaleCarList", mySaleCarList);
		
		//form에 뿌리기
		request.setAttribute("showPage", "adm/mySaleCarListForm.jsp");
		forward = new ActionForward("template.jsp", false);	
		
		return forward;
	}

}
