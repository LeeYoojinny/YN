package action.board;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import svc.board.Notice_BoardUpdateService;
import vo.ActionForward;
import vo.NoticeBoard;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Notice_BoardUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		ServletContext context = request.getServletContext();
		String uploadPath = context.getRealPath("/upload/notice_file");//컨텍스트 루트(/)의 절대경로를 기준
		System.out.println("서버상의 실제 경로(절대경로) : " + uploadPath);
		
		File dir = new File(uploadPath);
		if(!dir.exists()){
			dir.mkdirs();//upload 폴더가 존재하지 않으면 해당 위치에 만들어줌
		}
		
		int maxSize=5*1024*1024;
		
		MultipartRequest multi = new MultipartRequest(request, uploadPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
		
		//파일 수정처리
		String notice_file = "";
		String notice_file_origin = "";
		String update_img = multi.getParameter("update_img");
		
		System.out.println("noChange : "+update_img);
		
		if(update_img.equals("noChange") && multi.getFilesystemName("notice_file") == null ) {
			notice_file = multi.getParameter("origin_img_sys");
			notice_file_origin = multi.getParameter("origin_img_ori");
		}else {
			notice_file = multi.getFilesystemName("notice_file");
			notice_file_origin = multi.getOriginalFileName("notice_file");
		}
		
		System.out.println("qna_file : "+notice_file);
		System.out.println("qna_file_origin : "+notice_file_origin);
		
		//업데이트 날짜로 바꾸기
		Timestamp updateTime = new Timestamp(System.currentTimeMillis());
	
		String notice_num = multi.getParameter("notice_num");
		
		NoticeBoard notice = new NoticeBoard();
		notice.setUser_id(multi.getParameter("user_id"));
		notice.setNotice_title(multi.getParameter("notice_title"));
		notice.setNotice_content(multi.getParameter("notice_content"));
		notice.setNotice_file(notice_file);
		notice.setNotice_file_origin(notice_file_origin);
		notice.setNotice_date(updateTime);
		
		Notice_BoardUpdateService notice_BoardUpdateService = new Notice_BoardUpdateService();
		boolean isUpdateSuccess = notice_BoardUpdateService.updateNotice(notice,notice_num);
		
		if(!isUpdateSuccess) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();			
			out.println("<script>");
			out.println("alert('수정 실패하였습니다.');");	
			out.println("history.back();");
			out.println("</script>");
		}else {
			forward = new ActionForward("notice_boardView.bo?notice_num="+notice_num, false);	
		}
		
		return forward;
	}

}
