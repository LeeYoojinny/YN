package action.board;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import vo.ActionForward;

import dao.QnABoardDAO;
import svc.board.QnA_BoardWriteService;
import vo.QnABoard;

public class QnA_BoardWriteAction implements Action {

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
		
		/*
		String fileName = "";
		Enumeration<String> names = multi.getFileNames();
		if(names.hasMoreElements()) {
			String name = names.nextElement();
			fileName = multi.getFilesystemName(name);
		}
		*/
		
		
		QnABoard board = new QnABoard();		
		board.setUser_id(multi.getParameter("user_id"));
		board.setCar_id(multi.getParameter("car_id"));
		board.setQna_pw(multi.getParameter("qna_pw"));
		board.setQna_title(multi.getParameter("qna_title"));
		board.setQna_content(multi.getParameter("qna_content"));
		board.setQna_file(multi.getFilesystemName("qna_file"));
		board.setQna_file_origin(multi.getOriginalFileName("qna_file"));
		board.setSecret_YN(multi.getParameter("secret_YN"));		
		
	
		QnA_BoardWriteService qna_BoardWriteService = new QnA_BoardWriteService();
		
		//qna_num 생성하기
		String qna_num = "";
		String max_reserNum = qna_BoardWriteService.createQna_num();
		if(max_reserNum == null) {
			qna_num = "QNA00001";
		}else {
			String numericPart = max_reserNum.substring(3); // "QNA" 부분을 제외한 숫자 부분 추출
			int nextNumber = Integer.parseInt(numericPart) + 1; // 다음 번호 계산
			qna_num = "QNA" + String.format("%05d", nextNumber);
		}		
		board.setQna_num(qna_num);	
		
		boolean isWriteSuccess = qna_BoardWriteService.writeQnABoard(board);
		if(!isWriteSuccess) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('문의글 등록에 실패하였습니다.');");	
			out.println("history.back();");
			out.println("</script>");
		}else {
			forward = new ActionForward("qna_boardList.bo", false);	
		}
		
		return forward;
	}

}
