package action.adm;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.adm.AdminCustDetailViewService;
import vo.ActionForward;
import vo.Code;
import vo.QnABoard;
import vo.User;

public class AdminCustDetailViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String user_id = request.getParameter("user_id");
		
		AdminCustDetailViewService custDetailViewService = new AdminCustDetailViewService();
		User custInfo = custDetailViewService.getCustInfo(user_id);
		ArrayList<QnABoard> custQna = custDetailViewService.getCustQna(user_id);
		ArrayList<Code> allCode = custDetailViewService.getAllCode();
		
		//회원의 기본정보 받아오기
		request.setAttribute("custInfo", custInfo);
		
		//회원이 적은 게시글들 받아오기
		request.setAttribute("boardList", custQna);
		request.setAttribute("allCode", allCode);		
		
		//목록으로 돌아가는 버튼을 각각 만들기 위해 받는 파라미터값
		request.setAttribute("display_num", request.getParameter("display_num"));
		System.out.println("display_num : "+ request.getParameter("display_num"));//콘솔확인용
		
		request.setAttribute("showPage", "adm/custDetailView.jsp");
		forward = new ActionForward("template.jsp",false);
		
		return forward;
	}

}
