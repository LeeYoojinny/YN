package action.board;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import svc.board.Notice_BoardWriteService;
import vo.ActionForward;
import vo.NoticeBoard;

public class Notice_BoardWriteAction implements Action {

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
		
		NoticeBoard notice = new NoticeBoard();
		notice.setUser_id(multi.getParameter("user_id"));
		notice.setNotice_title(multi.getParameter("notice_title"));
		notice.setNotice_content(multi.getParameter("notice_content"));
		notice.setNotice_file(multi.getFilesystemName("notice_file"));
		notice.setNotice_file_origin(multi.getOriginalFileName("notice_file"));
		
		Notice_BoardWriteService notice_BoardWriteService = new Notice_BoardWriteService();
		boolean isWriteSuccess = notice_BoardWriteService.writeNoticeBoard(notice);
		
		if(!isWriteSuccess) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('등록에 실패하였습니다.');");	
			out.println("history.back();");
			out.println("</script>");
		}else {
			forward = new ActionForward("notice_boardList.bo", false);	
		}
		
		return forward;
	}

}
