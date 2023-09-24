package action.board;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import svc.board.Review_BoardUpdateService;
import vo.ActionForward;
import vo.ReviewBoard;

public class Review_BoardUpdateAction implements Action {

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
		
		String review_num = multi.getParameter("review_num");
		String list_num = multi.getParameter("list_num");
		
		ReviewBoard review = new ReviewBoard();
		review.setCar_id(multi.getParameter("car_id"));
		review.setUser_id(multi.getParameter("user_id"));
		review.setOrdernum(multi.getParameter("ordernum"));
		review.setReview_title(multi.getParameter("review_title"));
		review.setReview_content(multi.getParameter("review_content"));
		
		//파일 수정처리
		//이미지파일 수정됐는지 확인		
		String update_img1 = multi.getParameter("update_img1");
		String update_img2 = multi.getParameter("update_img2");
		String update_img3 = multi.getParameter("update_img3");
		
		System.out.println("update_img1 값 : "+update_img1);
		System.out.println("update_img2 값 : "+update_img2);
		System.out.println("update_img3 값 : "+update_img3);

		
		if(update_img1.equals("noChange") && multi.getFilesystemName("review_file1")==null) {
			review.setReview_file1(multi.getParameter("origin_img_sys1"));
			review.setReview_file1_origin(multi.getParameter("origin_img_ori1"));
		}else {
			if(multi.getFilesystemName("review_file1")==null) {
				review.setReview_file1("");
				review.setReview_file1_origin("");
			}else {
				review.setReview_file1(multi.getFilesystemName("review_file1"));
				review.setReview_file1_origin(multi.getOriginalFileName("review_file1"));
			}
		}
		
		if(update_img2.equals("noChange") && multi.getFilesystemName("review_file2")==null) {
			review.setReview_file2(multi.getParameter("origin_img_sys2"));
			review.setReview_file2_origin(multi.getParameter("origin_img_ori2"));
		}else {
			if(multi.getFilesystemName("review_file2")==null) {
				review.setReview_file2("");
				review.setReview_file2_origin("");
			}else {
				review.setReview_file2(multi.getFilesystemName("review_file2"));
				review.setReview_file2_origin(multi.getOriginalFileName("review_file2"));
			}
		}
		
		if(update_img3.equals("noChange") && multi.getFilesystemName("review_file3")==null) {
			review.setReview_file3(multi.getParameter("origin_img_sys3"));
			review.setReview_file3_origin(multi.getParameter("origin_img_ori3"));
		}else {
			if(multi.getFilesystemName("review_file3")==null) {
				review.setReview_file3("");
				review.setReview_file3_origin("");
			}else {
				review.setReview_file3(multi.getFilesystemName("review_file3"));
				review.setReview_file3_origin(multi.getOriginalFileName("review_file3"));
			}
		}
		
		//업데이트 날짜로 바꾸기
		Timestamp updateTime = new Timestamp(System.currentTimeMillis());
		review.setReview_date(updateTime);
		
		Review_BoardUpdateService review_BoardUpdateService = new Review_BoardUpdateService();
		boolean isUpdateSuccess = review_BoardUpdateService.updateReview(review,review_num);
		
		if(!isUpdateSuccess) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('게시글 수정에 실패하였습니다.');");	
			out.println("history.back();");
			out.println("</script>");
		}else {
			forward = new ActionForward("review_boardView.bo?review_num="+review_num+"&list_num="+list_num, false);	
		}
		
		
		return forward;
	}

}
