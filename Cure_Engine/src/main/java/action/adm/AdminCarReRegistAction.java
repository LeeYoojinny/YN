package action.adm;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.adm.AdminCarReRegistService;
import vo.ActionForward;

public class AdminCarReRegistAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String car_id = request.getParameter("car_ids");
		System.out.println("재등록차량 아이디 : "+car_id);
		
		String[] car_ids;

		if (car_id != null && car_id.contains(",")) {
		    car_ids = car_id.split(",");
		} else {
			car_ids = new String[]{car_id};
		}
		
		for(String id : car_ids) {
			System.out.println("파라미터 값으로 넘어온 car_ids : " + id);
		}
		
		AdminCarReRegistService carReRegistService = new AdminCarReRegistService();
		int reregistResult = carReRegistService.carReregist(car_ids);
		
		if(reregistResult > 0 ) {
			int update_deleteN = carReRegistService.update_deleteN();
			
			if(update_deleteN >= 0) {							
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('재등록 되었습니다.');");
				out.println("location.href='removeCarList.adm';");
				out.println("</script>");				
			}else {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('재등록 실패했습니다.');");
				out.println("history.back();");			
				out.println("</script>");
			}
		}else {//서비스에서 메소드 실행 실패했다면
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('재등록 실패했습니다.');");
			out.println("history.back();");			
			out.println("</script>");
		}
		
		return forward;
	}

}
