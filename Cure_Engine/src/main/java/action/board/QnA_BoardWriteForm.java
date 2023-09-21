package action.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.board.QnA_BoardWriteFormService;
import vo.ActionForward;
import vo.Code;

public class QnA_BoardWriteForm implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//제목 통일을 위해 코드 가져오기
		QnA_BoardWriteFormService qna_BoardWriteFormService = new QnA_BoardWriteFormService();		
		ArrayList<Code> allCode = qna_BoardWriteFormService.getAllCode();
		
		request.setAttribute("allCode", allCode);
		
		request.setAttribute("showPage", "user/qna/qna_Board_Write.jsp");
		forward = new ActionForward("template.jsp",false);
		
		return forward;
	}

}
