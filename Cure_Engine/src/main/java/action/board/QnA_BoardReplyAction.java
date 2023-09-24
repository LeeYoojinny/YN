package action.board;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import svc.board.QnA_BoardReplyService;
import vo.ActionForward;
import vo.QnABoard;

public class QnA_BoardReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		ServletContext context = request.getServletContext();
		String uploadPath = context.getRealPath("upload/qna_file");//컨텍스트 루트(/)의 절대경로를 기준
		System.out.println("서버상의 실제 경로(절대경로) : " + uploadPath);
		
		File dir = new File(uploadPath);
		if(!dir.exists()){
			dir.mkdirs();//upload 폴더가 존재하지 않으면 해당 위치에 만들어줌
		}
		
		int maxSize=5*1024*1024;
		
		MultipartRequest multi = new MultipartRequest(request, uploadPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
		
		QnABoard board = new QnABoard();
		board.setQna_viewNum(Integer.parseInt(multi.getParameter("qna_viewNum")));
		board.setUser_id(multi.getParameter("user_id"));
		board.setCar_id(multi.getParameter("car_id"));
		board.setQna_pw(multi.getParameter("qna_pw"));
		board.setQna_title(multi.getParameter("qna_title"));
		board.setQna_content(multi.getParameter("qna_content"));
		board.setQna_file(multi.getFilesystemName("qna_file"));
		board.setQna_file_origin(multi.getOriginalFileName("qna_file"));
		board.setSecret_YN(multi.getParameter("secret_YN"));
		board.setReply_YN("Y");
		
		//콘솔 확인용
		System.out.println("qna_viewNum : "+multi.getParameter("qna_viewNum"));
		System.out.println("car_id : "+multi.getParameter("car_id"));
		System.out.println("secret_YN : "+multi.getParameter("secret_YN"));
		System.out.println("qna_pw : "+multi.getParameter("qna_pw"));
		
		//원글에 대한 reply_YN을 Y로 바꿔주기 위한 메소드 추가
		String parent_qna_num = multi.getParameter("parentNum");
		
		QnA_BoardReplyService qna_BoardReplyService = new QnA_BoardReplyService();
		
		//qna_num 생성하기
		String qna_num = "";
		String max_reserNum = qna_BoardReplyService.createQna_num();
		if(max_reserNum == null) {
			qna_num = "QNA00001";
		}else {
			String numericPart = max_reserNum.substring(3); // "QNA" 부분을 제외한 숫자 부분 추출
			int nextNumber = Integer.parseInt(numericPart) + 1; // 다음 번호 계산
			qna_num = "QNA" + String.format("%05d", nextNumber);
		}		
		board.setQna_num(qna_num);
		
		boolean replySuccess = qna_BoardReplyService.insertReply(board,parent_qna_num);

		if(!replySuccess) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();			
			out.println("<script>");
			out.println("alert('답글 등록에 실패하였습니다.');");	
			out.println("history.back();");
			out.println("</script>");
		}else {
			forward = new ActionForward("qna_boardList.bo", false);	
			
		}
				
		return forward;
	}

}
