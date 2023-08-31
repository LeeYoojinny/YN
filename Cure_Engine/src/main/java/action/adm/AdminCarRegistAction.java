package action.adm;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import svc.adm.AdminCarRegistService;
import vo.ActionForward;
import vo.Car;

public class AdminCarRegistAction implements Action {

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
		
		//선택사항이였던 항목에 대해 null값 처리
		String car_image2 = "";
		String car_content = "";
		String car_accident_detail = "";

		String valueCheck = multi.getParameter("car_image2"); //car_image2로 넘어오는 값이
		if(valueCheck != null) {// null값이 아니면 (값이 1개 이상이면)
			String[] multiImage = multi.getParameterValues("car_image2"); //배열로 받아서
			car_image2 = String.join(",", multiImage); //,(콤마)를 이용해 하나의 문자열로 만들기
		}
		
		valueCheck = multi.getParameter("car_content");
		if(valueCheck != null) {
			String[] multiContent = multi.getParameterValues("car_content");
			car_content = String.join(",", multiContent);
		}
		
		valueCheck = multi.getParameter("car_accident_detail");
		if(valueCheck != null) {
			String[] multiAccDetail = multi.getParameterValues("car_accident_detail");
			car_accident_detail = String.join(",", multiAccDetail);
		}
			
		
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
					multi.getFilesystemName("car_image1"),
					car_image2,
					car_content,
					car_accident_detail
				);
		
		System.out.println("Car 객체 생성 성공");
		
		AdminCarRegistService adminCarRegistService = new AdminCarRegistService();
		boolean isRegistSuccess = adminCarRegistService.registCar(car);
		
		if(isRegistSuccess) {
			System.out.println("Car insert 성공");
			forward = new ActionForward("/allCarListView.usr",true);
		}else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('상품 등록에 실패하였습니다.');");
			out.println("history.back();"); //out.println("history.go(-1);");
			out.println("</script>");
		}
		
		
		return forward;
	}

}
