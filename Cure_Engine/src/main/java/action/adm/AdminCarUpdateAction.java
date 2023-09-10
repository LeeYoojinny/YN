package action.adm;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import svc.adm.AdminCarUpdateService;
import vo.ActionForward;
import vo.Car;

public class AdminCarUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		ServletContext context = request.getServletContext();
		String uploadPath = context.getRealPath("/upload/carRegist_images");
		
		System.out.println("서버상의 실제 경로(절대경로) : "+uploadPath);
		
		File dir = new File(uploadPath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		int size = 1024*1024*5; //최대용량 5MB 제한 
		
		MultipartRequest multi = new MultipartRequest(request, uploadPath,size,"utf-8", new DefaultFileRenamePolicy());
		
		//선택사항이였던 체크박스 항목에 대해 null값 처리
		String car_content = "";
		String car_accident_detail = "";
		
		String valueCheck = multi.getParameter("car_content");
		if(valueCheck != null) {
			String[] multiContent = multi.getParameterValues("car_content");
			car_content = String.join(",", multiContent);
		}
		
		valueCheck = multi.getParameter("car_accident_detail");
		if(valueCheck != null) {
			String[] multiAccDetail = multi.getParameterValues("car_accident_detail");
			car_accident_detail = String.join(",", multiAccDetail);
		}
		
		//이미지파일 수정됐는지 확인
		String car_image1 = "";
		String car_image2 = "";
		String car_image3 = "";
		String car_image4 = "";
		String car_image5 = "";
		
		String update_img1 = multi.getParameter("update_img1");
		String update_img2 = multi.getParameter("update_img2");
		String update_img3 = multi.getParameter("update_img3");
		String update_img4 = multi.getParameter("update_img4");
		String update_img5 = multi.getParameter("update_img5");
		
		System.out.println("update_img1 값 : "+update_img1);
		System.out.println("update_img2 값 : "+update_img2);
		System.out.println("update_img3 값 : "+update_img3);
		System.out.println("update_img4 값 : "+update_img4);
		System.out.println("update_img5 값 : "+update_img5);
		
		if(update_img1.equals("noChange") && multi.getFilesystemName("car_image1")==null) {
			car_image1 = multi.getParameter("origin_img1");
		}else {
			car_image1 = multi.getFilesystemName("car_image1");
		}
		
		if(update_img2.equals("noChange") && multi.getFilesystemName("car_image2")==null) {
			car_image2 = multi.getParameter("origin_img2");
		}else {
			if(multi.getFilesystemName("car_image2")==null) {
				car_image2 = "X";
			}else {
				car_image2 = multi.getFilesystemName("car_image2");
			}
		}
		
		if(update_img3.equals("noChange") && multi.getFilesystemName("car_image3")==null) {
			car_image3 = multi.getParameter("origin_img3");
		}else {
			if(multi.getFilesystemName("car_image3")==null) {
				car_image3 = "X";
			}else {
				car_image3 = multi.getFilesystemName("car_image3");
			}
		}
		
		if(update_img4.equals("noChange") && multi.getFilesystemName("car_image4")==null) {
			car_image4 = multi.getParameter("origin_img4");
		}else {
			if(multi.getFilesystemName("car_image4")==null) {
				car_image4 = "X";
			}else {
				car_image4 = multi.getFilesystemName("car_image4");
			}
		}
		
		if(update_img5.equals("noChange") && multi.getFilesystemName("car_image5")==null) {
			car_image5 = multi.getParameter("origin_img5");
		}else {
			if(multi.getFilesystemName("car_image5")==null) {
				car_image5 = "X";
			}else {
				car_image5 = multi.getFilesystemName("car_image5");
			}
		}
		
		System.out.println("car_image1 값 : "+car_image1);
		System.out.println("car_image2 값 : "+car_image2);
		System.out.println("car_image3 값 : "+car_image3);
		System.out.println("car_image4 값 : "+car_image4);
		System.out.println("car_image5 값 : "+car_image5);
		
		Car car = new Car(
				multi.getParameter("dealer_id"),
				multi.getParameter("car_id"),
				multi.getParameter("car_brand"),
				multi.getParameter("car_name"),	
				multi.getParameter("car_color"),
				Integer.parseInt(multi.getParameter("car_price")),
				Integer.parseInt(multi.getParameter("car_capacity")),
				multi.getParameter("car_fuel"),
				multi.getParameter("car_transmission"),
				multi.getParameter("car_type"),
				multi.getParameter("car_accident"),
				Integer.parseInt(multi.getParameter("car_year")),
				Integer.parseInt(multi.getParameter("car_distance")),
				car_image1,
				car_image2,
				car_image3,
				car_image4,
				car_image5,
				car_content,
				car_accident_detail
			);
	
		System.out.println("Car 객체 생성 성공");
		
		AdminCarUpdateService adminCarUpdateService = new AdminCarUpdateService();
		boolean isUpdateSuccess = adminCarUpdateService.updateCar(car);
		
		if(isUpdateSuccess) {
			System.out.println("Car Update 성공");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('상품 수정이 완료되었습니다.');");
			out.println("location.href='mySaleCar.adm';"); //out.println("history.go(-1);");
			out.println("</script>");
			//forward = new ActionForward("allCarListView.usr", false);	
		}else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('상품 수정에 실패하였습니다.');");
			out.println("history.back();"); //out.println("history.go(-1);");
			out.println("</script>");
		}
		
		return forward;
	}

}
