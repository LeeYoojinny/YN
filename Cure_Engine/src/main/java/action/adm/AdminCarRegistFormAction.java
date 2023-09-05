package action.adm;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.adm.AdminCarRegistFormService;
import vo.ActionForward;
import vo.Code;

public class AdminCarRegistFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		AdminCarRegistFormService adminCarRegistFormService = new AdminCarRegistFormService();
		ArrayList<Code> allCode = adminCarRegistFormService.getAllCode();
		
		if(allCode != null) {
			System.out.println("모든 code 가져오기 성공");
			
			request.setAttribute("allCode", allCode);
			request.setAttribute("showPage", "adm/productRegistForm.jsp");
			forward = new ActionForward("template.jsp", false);	
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('페이지 로드에 실패했습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		
		return forward;
	}

}
