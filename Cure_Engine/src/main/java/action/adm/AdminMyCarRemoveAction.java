package action.adm;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.adm.AdminMyCarRemoveService;
import vo.ActionForward;

public class AdminMyCarRemoveAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;		
		
		String display_num = request.getParameter("display_num");
		System.out.println("display_num : "+display_num);
		
		String user_id = request.getParameter("dealer_id");
		System.out.println("딜러아이디 : "+user_id);
		
		String car_id = request.getParameter("car_ids");
		System.out.println("삭제차량 아이디 : "+car_id);
		
		String[] car_ids;

		if (car_id != null && car_id.contains(",")) {
		    car_ids = car_id.split(",");
		} else {
			car_ids = new String[]{car_id};
		}
		
		for(String id : car_ids) {
			System.out.println("파라미터 값으로 넘어온 car_ids : " + id);
		}
		
		AdminMyCarRemoveService adminMyCarRemoveService = new AdminMyCarRemoveService();
		int removeResult = 0;
		
		if(display_num.equals("1")) {
			removeResult = adminMyCarRemoveService.removeMyCar(car_ids,user_id);
		}else if(display_num.equals("2")) {
			removeResult = adminMyCarRemoveService.removeCarByAdmin(car_ids);
		}
			
				
		if(removeResult > 0 ) {
			int removeResult_wish = adminMyCarRemoveService.removeMyCar_wish();
			
			if(removeResult_wish >= 0) {
				if(display_num.equals("1")) {				
					response.setContentType("text/html; charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('삭제 되었습니다.');");
					out.println("location.href='mySaleCar.adm';");
					out.println("</script>");
				}else if(display_num.equals("2")) {								
					response.setContentType("text/html; charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('삭제 되었습니다.');");
					out.println("location.href='allSaleCar.adm';");
					out.println("</script>");
				}
			}else {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 실패했습니다.');");
				out.println("history.back();");			
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
