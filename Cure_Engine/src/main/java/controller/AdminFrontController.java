package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.adm.AdminAllSaleCarAction;
import action.adm.AdminCarIdCheckAction;
import action.adm.AdminCarReRegistAction;
import action.adm.AdminCarRegistAction;
import action.adm.AdminCarRegistFormAction;
import action.adm.AdminCarUpdateAction;
import action.adm.AdminCarUpdateFormAction;
import action.adm.AdminCustDeleteAction;
import action.adm.AdminCustDetailViewAction;
import action.adm.AdminCustExpireListAction;
import action.adm.AdminCustRejoinAction;
import action.adm.AdminCustSearchAction;
import action.adm.AdminCustomerListAction;
import action.adm.AdminDealerApproveAction;
import action.adm.AdminDealerApproveListAction;
import action.adm.AdminDealerDeleteAction;
import action.adm.AdminDealerDetailViewAction;
import action.adm.AdminDealerExpireAction;
import action.adm.AdminDealerExpireListAction;
import action.adm.AdminDealerInfoUpdateAction;
import action.adm.AdminDealerInfoViewAction;
import action.adm.AdminDealerJoinAction;
import action.adm.AdminDealerJoinFormAction;
import action.adm.AdminDealerListAction;
import action.adm.AdminDealerRefuseAction;
import action.adm.AdminDealerRejoinAction;
import action.adm.AdminMyCarRemoveAction;
import action.adm.AdminMySaleCarAction;
import action.adm.AdminOrderDecisionAction;
import action.adm.AdminOrderDetailAction;
import action.adm.AdminOrderViewListAction;
import action.adm.AdminRemoveCarListAction;
import action.adm.AdminReservationCheckAction;
import action.adm.AdminReservationDeleteAction;
import action.adm.AdminReservationViewAction;
import vo.ActionForward;

/**
 * Servlet implementation class DogFrontController
 */

//확장자가 dog이면 무조건 DogFrontController로 이동하여 doProcess(request, response);메서드 실행함
@WebServlet("*.adm")
public class AdminFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");//★반드시 첫줄에 있어야 함★ : post방식 처리하기위해
		
		/*********************************************************************
		 * 1. 전송된 요청 파악
		 * URL = "http://localhost:8090/project/dogList.dog"
		 * URI = "/project/dogList.dog"
		 * contextPath = "/project"
		 * 
		 * ⇒URI - contextPath = "/dogList.dog"
		 *********************************************************************/
		
		String requestURI = request.getRequestURI();// "/project/dogList.dog"
		String contextPath = request.getContextPath();// "/project"
		
		String command = requestURI.substring(contextPath.length());// "/dogList.dog"
		
		/*********************************************************************
		 * 2. 각 요청별로 비즈니스 로직을 호출하여 처리
		 *********************************************************************/
		
		Action action = null;
		ActionForward forward = null;
		
		System.out.println("[Admin]command : " + command); //어떤 요청인지 확인하기 위해 출력
		
		
		
		
		/*------- '상품등록' 클릭 → 상품등록 폼 → 상품등록 처리 ------------------------------------*/

		if(command.equals("/carRegistForm.adm")) {//'관심상품 페이지 보기' 요청이면
			action = new AdminCarRegistFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/adm/carIdCheck.adm")) {//'차량 등록 중복확인' 요청이면
			action = new AdminCarIdCheckAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		else if(command.equals("/carRegist.adm")) {//'상품등록' 요청이면
			action = new AdminCarRegistAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*------- 딜러등록 폼보기 요청 ---------------------------------------------------------------*/
		else if(command.equals("/dealerJoin.adm")) {//'딜러등록' 폼보기 요청이면
			action = new AdminDealerJoinFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/dealerJoinAction.adm")) {//'딜러등록' 요청이면
			action = new AdminDealerJoinAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*------- 딜러등록 요청 리스트 보기  -----------------------------------------------------------*/
		else if(command.equals("/dealerApproveList.adm")) {//'딜러등록' 요청이면
			action = new AdminDealerApproveListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*------- 딜러등록 승인하기 처리  -----------------------------------------------------------*/
		else if(command.equals("/dealerApprove.adm")) {//'딜러등록' 승인하기
			action = new AdminDealerApproveAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*------- 딜러등록 반려 처리  -----------------------------------------------------------*/
		else if(command.equals("/dealerRefuse.adm")) {//'딜러등록' 승인하기
			action = new AdminDealerRefuseAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*------- '나의판매차량 보기' → 처리 ---------------------------------------------------------*/
		else if(command.equals("/mySaleCar.adm")) {//'나의판매차량 보기' 요청이면
			action = new AdminMySaleCarAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/*------- '나의판매차량 삭제하기' → 처리 (보류) ---------------------------------------------------------*/
		else if(command.equals("/myCarRemove.adm")) {//'나의판매차량 삭제' 요청이면
			action = new AdminMyCarRemoveAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/*------- '차량정보수정' → 처리 ---------------------------------------------------------*/
		else if(command.equals("/carUpdateForm.adm")) {//'차량정보수정' 요청이면
			action = new AdminCarUpdateFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/carUpdate.adm")) {//'차량정보수정' 처리
			action = new AdminCarUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*------- '딜러정보수정 폼보기' → 처리 ---------------------------------------------------------*/
		else if(command.equals("/dealerInfoView.adm")) {//'딜러정보수정 폼보기' 요청이면
			action = new AdminDealerInfoViewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/dealerInfoUpdate.adm")) {//'딜러정보수정' 요청이면
			action = new AdminDealerInfoUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/dealerDeleteForm.adm")) {//'딜러탈퇴 폼보기' 요청이면
			request.setAttribute("showPage", "adm/dealerDeleteForm.jsp");
			forward = new ActionForward("template.jsp",false);
		}
		else if(command.equals("/dealerDelete.adm")) {//'딜러탈퇴 폼보기' 요청이면
			action = new AdminDealerDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*------- '회원관리 폼보기' → 처리 ---------------------------------------------------------*/
		else if(command.equals("/customerList.adm")) {//'회원관리 폼보기' 요청이면
			action = new AdminCustomerListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/custDetailView.adm")) {//'회원 자세히 보기' 요청이면
			action = new AdminCustDetailViewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/custDelete.adm")) {//'회원 탈퇴처리' 요청이면
			action = new AdminCustDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/custExpireList.adm")) {//'탈퇴회원보기' 요청이면
			action = new AdminCustExpireListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/custRejoin.adm")) {//'탈퇴회원 재가입 처리' 요청이면
			action = new AdminCustRejoinAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/custSearchAction.adm")) {//'회원검색' 요청이면
			action = new AdminCustSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*------- 예약현황보기 ---------------------------------------------------------*/
		else if(command.equals("/reservationView.adm")) {//'예약현황보기' 요청이면
			action = new AdminReservationViewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/reservationCheck.adm")) {//'승인, 거절' 요청이면
			action = new AdminReservationCheckAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/deleteReservation.adm")) {//'예약 삭제' 요청이면
			action = new AdminReservationDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*------- 주문현황보기 ---------------------------------------------------------*/
		else if(command.equals("/orderListView.adm")) {//'주문현황리스트' 요청이면
			action = new AdminOrderViewListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/orderDetail.adm")) {//'주문자세히보기' 요청이면
			action = new AdminOrderDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/orderDecision.adm")) {//'주문 승인/거절' 요청이면
			action = new AdminOrderDecisionAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*------- 관리자 - 모든상품보기 ---------------------------------------------------------*/
		
		else if(command.equals("/allSaleCar.adm")) {//'삭제 안된 모든 차량보기' 요청이면
			action = new AdminAllSaleCarAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/removeCarList.adm")) {//'삭제된 차량보기' 요청이면
			action = new AdminRemoveCarListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/carReRegist.adm")) {//'삭제된 차량보기 재등록' 요청이면
			action = new AdminCarReRegistAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/*------- 관리자 - 딜러관리 ---------------------------------------------------------*/
		else if(command.equals("/dealerList.adm")) {//'딜러관리보기' 요청이면
			action = new AdminDealerListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/dealerDetailView.adm")) {//딜러 정보 자세히 보기
			action = new AdminDealerDetailViewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/dealerExpireList.adm")) { //탈퇴딜러 리스트보기
			action = new AdminDealerExpireListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/adminDealerDelete.adm")) {//관리자가 딜러 탈퇴시키기
			action = new AdminDealerExpireAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/dealerRejoin.adm")) {//관리자가 딜러 재가입처리하기
			action = new AdminDealerRejoinAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*********************************************************************
		 * 3. 포워딩(화면에 뿌리는 작업)
		 *********************************************************************/
		if(forward != null) {
			if(forward.isRedirect() == true) {//isRedirect=false=디스패치방식=기존요청, true=리다이렉트방식=새요청
				response.sendRedirect(forward.getPath()); //"dogList.jsp"
			}else {//디스패치방식 : "dogList.jsp"에서 기존 request 공유받아 사용가능
				request.getRequestDispatcher(forward.getPath()).forward(request, response);
			}
		}
		
	}

}












