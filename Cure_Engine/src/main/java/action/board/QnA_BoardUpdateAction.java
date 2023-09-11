package action.board;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import svc.board.QnA_BoardUpdateService;
import vo.ActionForward;
import vo.Board;

public class QnA_BoardUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		ServletContext context = request.getServletContext();
		String uploadPath = context.getRealPath("/upload/qna_file");//컨텍스트 루트(/)의 절대경로를 기준
		System.out.println("서버상의 실제 경로(절대경로) : " + uploadPath);
		
		File dir = new File(uploadPath);
		if(!dir.exists()){
			dir.mkdirs();//upload 폴더가 존재하지 않으면 해당 위치에 만들어줌
		}
		
		int maxSize=5*1024*1024;
		
		MultipartRequest multi = new MultipartRequest(request, uploadPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
		
		//파일 수정처리
		String qna_file = "";
		String qna_file_origin = "";
		String update_img = multi.getParameter("update_img");
		
		if(update_img.equals("noChange") && multi.getFilesystemName("qna_file") == null ) {
			qna_file = multi.getParameter("origin_img_sys");
			qna_file_origin = multi.getParameter("origin_img_ori");
		}else {
			qna_file = multi.getFilesystemName("qna_file");
			qna_file_origin = multi.getOriginalFileName("qna_file");
		}
		
		System.out.println("qna_file : "+qna_file);
		System.out.println("qna_file_origin : "+qna_file_origin);
		
		//패스워드 수정처리
		/*
		String qna_pw = "";
		if(multi.getParameter("secret_YN").equals("Y")) {
			qna_pw = null;
		}else {
			qna_pw = multi.getParameter("qna_pw");
		}
		*/
		
		String qna_num = multi.getParameter("qna_num");
		System.out.println("파라미터로 넘어온 qna_num 값 : " + qna_num);
		
		Board board = new Board();		
		board.setUser_id(multi.getParameter("user_id"));
		board.setCar_id(multi.getParameter("car_id"));
		board.setQna_pw(multi.getParameter("qna_pw"));
		board.setQna_title(multi.getParameter("qna_title"));
		board.setQna_content(multi.getParameter("qna_content"));
		board.setQna_file(qna_file);
		board.setQna_file_origin(qna_file_origin);
		board.setSecret_YN(multi.getParameter("secret_YN"));	
		
		QnA_BoardUpdateService qna_BoardUpdateService = new QnA_BoardUpdateService();
		boolean isUpdateSuccess = qna_BoardUpdateService.updateBoard(board,qna_num);
		
		if(!isUpdateSuccess) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('게시글 수정에 실패하였습니다.');");	
			out.println("history.back();");
			out.println("</script>");
		}else {
			forward = new ActionForward("qna_boardView.bo?qna_num="+qna_num, false);	
		}
		
		
		return forward;
	}

}
