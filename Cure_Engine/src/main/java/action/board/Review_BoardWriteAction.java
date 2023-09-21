package action.board;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import svc.board.Review_BoardWriteService;
import vo.ActionForward;
import vo.ReviewBoard;

public class Review_BoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		ServletContext context = request.getServletContext();
		String uploadPath = context.getRealPath("upload/review_file");//컨텍스트 루트(/)의 절대경로를 기준
		System.out.println("서버상의 실제 경로(절대경로) : " + uploadPath);
		
		File dir = new File(uploadPath);
		if(!dir.exists()){
			dir.mkdirs();//upload 폴더가 존재하지 않으면 해당 위치에 만들어줌
		}
		
		int maxSize=5*1024*1024;
		
		MultipartRequest multi = new MultipartRequest(request, uploadPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
				
		ReviewBoard review = new ReviewBoard();
		review.setCar_id(multi.getParameter("car_id"));
		review.setUser_id(multi.getParameter("user_id"));
		review.setOrdernum(multi.getParameter("ordernum"));
		review.setReview_title(multi.getParameter("review_title"));
		review.setReview_content(multi.getParameter("review_content"));
		
		if(multi.getFilesystemName("review_file1") != null) {
			review.setReview_file1(multi.getFilesystemName("review_file1"));
			review.setReview_file1_origin(multi.getOriginalFileName("review_file1"));
		}else {
			review.setReview_file1("");
			review.setReview_file1_origin("");
		}
		
		if(multi.getFilesystemName("review_file2") != null) {
			review.setReview_file2(multi.getFilesystemName("review_file2"));
			review.setReview_file2_origin(multi.getOriginalFileName("review_file2"));
		}else {
			review.setReview_file2("");
			review.setReview_file2_origin("");
		}
		
		if(multi.getFilesystemName("review_file3") != null) {
			review.setReview_file3(multi.getFilesystemName("review_file3"));
			review.setReview_file3_origin(multi.getOriginalFileName("review_file3"));
		}else {
			review.setReview_file3("");
			review.setReview_file3_origin("");
		}
		
		Review_BoardWriteService review_BoardWriteService = new Review_BoardWriteService();
		
		//review_num 생성
		String review_num = "";
		String max_reserNum = review_BoardWriteService.createNotice_num();
		if(max_reserNum == null) {
			review_num = "REV00001"; //첫 생성이면 해당 넘버로 지정
		}else {
			String numericPart = max_reserNum.substring(3);
			int nextNumber = Integer.parseInt(numericPart) + 1;
			review_num = "REV" + String.format("%05d", nextNumber);
		}		
		review.setReview_num(review_num);
				
		boolean isWriteSuccess = review_BoardWriteService.writeReviewBoard(review);
		
		if(!isWriteSuccess) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('등록에 실패하였습니다.');");	
			out.println("history.back();");
			out.println("</script>");
		}else {
			forward = new ActionForward("review_boardList.bo", false);	
		}
		
		return forward;
	}

}
