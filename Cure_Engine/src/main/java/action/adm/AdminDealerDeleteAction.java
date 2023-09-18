package action.adm;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.adm.AdminDealerDeleteService;
import util.SHA256;
import vo.ActionForward;

public class AdminDealerDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session =  request.getSession();
		 
		String user_id = request.getParameter("user_id");
		String user_pw = SHA256.encodeSHA256(request.getParameter("user_pw"));
		System.out.println("파라미터로 전달 된 id: "+user_id);
		
		AdminDealerDeleteService dealerDeleteService = new AdminDealerDeleteService();
		int checkOk = dealerDeleteService.checkPw(user_id,user_pw);
		
		if(checkOk > 0) {
        	System.out.println("pw 확인완료");
        	int deleteSuccess = dealerDeleteService.deleteDealer(user_id);
        	if(deleteSuccess > 0) {
        		session.removeAttribute("user_category");
        		session.removeAttribute("user_id");
        		session.removeAttribute("user_name");
        		session.removeAttribute("user_phone");
        		session.removeAttribute("user_email");
        		session.removeAttribute("wishlist");
        		
        		response.setContentType("text/html; charset=utf-8");			
    			PrintWriter out = response.getWriter();
    			out.println("<script>");
    			out.println("alert('탈퇴처리 되었습니다.');");
    			out.println("location.href='index.jsp'"); //탈퇴폼 다시 요청
    			out.println("</script>");

        	}else {
        		response.setContentType("text/html; charset=utf-8");			
    			PrintWriter out = response.getWriter();
    			out.println("<script>");
    			out.println("alert('탈퇴에 실패했습니다.다시 시도해주세요.');");
    			out.println("location.href='dealerDeleteForm.adm'"); //탈퇴폼 다시 요청
    			out.println("</script>");

        	}
        }else {
        	response.setContentType("text/html; charset=utf-8");			
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다.');");
			out.println("location.href='dealerDeleteForm.adm'"); //탈퇴폼 다시 요청
			out.println("</script>");
        }
		
		
		return forward;
	}

}
