package action.adm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.adm.AdminDealerJoinFormService;
import vo.ActionForward;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminDealerJoinFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		AdminDealerJoinFormService adminDealerJoinFormService = new AdminDealerJoinFormService();
		String dealer_id = adminDealerJoinFormService.getDealerId();		
		
        //가져온 마지막 dealer_id 로 새로운 아이디 생성
        String newId="";
        
        //년도에 따라 아이디 바뀌므로 년도 가져오기
        Date currentDate = new Date();
        SimpleDateFormat yearFormat = new SimpleDateFormat("yy");
        String currentYear = yearFormat.format(currentDate);
        
        if(dealer_id != null) {
        	String latestYear = dealer_id.substring(1, 3);
        	
        	// 현재 년도와 비교하여 연도가 달라지면 "d24"로 시작
        	if (!currentYear.equals(latestYear)) {
                newId = "d" + currentYear + "0001";
            } else {
            	// 같은 년도라면 숫자 부분을 업데이트
            	int lastIdNumber = Integer.parseInt(dealer_id.substring(3));
                String newIdNumber = String.format("%04d", lastIdNumber + 1);
                newId = "d" + currentYear + newIdNumber;
            }
        }else {
        	newId = "d" + currentYear + "0001";
        }
        
        System.out.println("새로 생성된 ID: " + newId);
		
		request.setAttribute("dealer_id", newId);
		request.setAttribute("showPage", "adm/dealerJoinForm.jsp");
		forward = new ActionForward("template.jsp",false);
		
		return forward;
	}

}
