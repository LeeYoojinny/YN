package action.cust;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.cust.CustomerUpdateService;
import vo.ActionForward;
import vo.User;

public class CustomerUpdateAction implements Action {

	@Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = null;

        HttpSession session = request.getSession();

        String user_id = (String)session.getAttribute("user_id");
        String user_name = request.getParameter("user_name");
		
		String user_birth_y = request.getParameter("user_birth_y");
		String user_birth_m = request.getParameter("user_birth_m");
		String user_birth_d = request.getParameter("user_birth_d");
		
		//넘어온 년,월,일을 '-'으로 연결해준 후 insert
		String user_birth = user_birth_y+"-"+user_birth_m+"-"+user_birth_d;
		
		String user_gender = request.getParameter("user_gender");
		String user_phone = request.getParameter("user_phone");
		String user_email = request.getParameter("user_email");
		int user_zipcode = Integer.parseInt(request.getParameter("user_zipcode"));
		String user_address1 = request.getParameter("user_address1");
		String user_address2 = request.getParameter("user_address2");
        
		
		User user = new User(user_id, user_name, user_birth, user_gender, user_phone,
				user_email, user_zipcode, user_address1, user_address2); 
		
      
        CustomerUpdateService userUpdateService = new CustomerUpdateService();
        boolean isUserUpdateSuccess = userUpdateService.userUpdate(user);
		
        if (isUserUpdateSuccess) {
			session.setAttribute("user_name", user_name);
			session.setAttribute("user_email", user_email);
			session.setAttribute("user_phone", user_phone);
			
			response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('수정 되었습니다.');");
            out.println("location.href='myInfoView.cust';"); 
            out.println("</script>");

        } else {
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('수정에 실패하였습니다.');");
            out.println("history.back();"); 
            out.println("</script>");
        }
        
        return forward;
	}
}