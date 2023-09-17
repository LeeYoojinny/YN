package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.cust.CustomerDeleteAction;
import action.cust.CustomerInfoViewAction;
import action.cust.CustomerLikeThisAction;
import action.cust.CustomerOrderFormAction;
import action.cust.CustomerRemoveWishAction;
import action.cust.CustomerReservationAction;
import action.cust.CustomerReservationListAction;
import action.cust.CustomerUnlikeThisAction;
import action.cust.CustomerUpdateAction;
import action.cust.CustomerWishlistAction;
import vo.ActionForward;

/**
 * Servlet implementation class DogFrontController
 */

//확장자가 dog이면 무조건 DogFrontController로 이동하여 doProcess(request, response);메서드 실행함
@WebServlet("*.cust")
public class CustomerFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerFrontController() {
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
		
		System.out.println("[Customer]command : " + command); //어떤 요청인지 확인하기 위해 출력
		
		
		
		
		/*------- '관심상품' → 처리 ---------------------------------------------------------*/
		if(command.equals("/myWishlist.cust")) {//'관심상품 페이지 보기' 요청이면
			action = new CustomerWishlistAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		}
		
		/*------- '상품에 하트누르면' → 관심아이템 넣기 ---------------------------------------------------------*/
		else if(command.equals("/userLikeThis.cust")) {//'로그인 처리'요청하면
			action = new CustomerLikeThisAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		}
		
		/*------- '상품에 하트누르면' → 관심아이템 빼기 ---------------------------------------------------------*/
		else if(command.equals("/userUnlikeThis.cust")) {//'로그인 처리'요청하면
			action = new CustomerUnlikeThisAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		}
		
		/*------- '위시리스트 여러개 삭제' → 관심아이템 빼기 ---------------------------------------------------------*/
		else if(command.equals("/userRemoveWish.cust")) {//'로그인 처리'요청하면
			action = new CustomerRemoveWishAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		}
		
		/*------- '예약보기' → 처리 ---------------------------------------------------------*/
		/*
		else if(command.equals("/myReservation.cust")) {//'예약페이지 보기' 요청이면
			action = new CustomerReservationListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		}
		*/
		
		
		
		/*------- '회원정보관리 폼 보기' → 처리 ---------------------------------------------------------*/
		
		else if(command.equals("/myInfoView.cust")) {//'회원정보가 셋팅된 회원정보관리 폼 보기' 요청이면
			action = new CustomerInfoViewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/myInfoUpdate.cust")) {//'회원정보수정 처리'요청하면
			//action:부모인터페이스 = UserLoginAction:구현한자식객체;
			action = new CustomerUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		}
		
		/*------- '회원탈퇴 폼 보기' → 처리 ---------------------------------------------------------*/
		else if(command.equals("/customerDeleteForm.cust")) {//'회원탈퇴 폼' 보기 요청이면
			request.setAttribute("showPage", "customer/custDeleteForm.jsp");
			forward = new ActionForward("template.jsp",false);
		}
		

		else if(command.equals("/customerDelete.cust")) {//'회원탈퇴' 처리
			action = new CustomerDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		}

		
		/*------- '주문 폼 보기' → 처리 ---------------------------------------------------------*/
		else if(command.equals("/orderForm.cust")) {//'주문 폼 보기' 요청이면
			action = new CustomerOrderFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		}
		
		/*------- '시승예약 폼 보기' → 처리 ---------------------------------------------------------*/
		else if(command.equals("/reservationForm.cust")) {//'시승예약 폼 보기' 요청이면
			request.setAttribute("showPage", "customer/reservationForm.jsp");
			forward = new ActionForward("template.jsp",false);
		}
		else if(command.equals("/reservation.cust")) {//예약 처리
			action = new CustomerReservationAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		}
		
		/*------- 나의예약내역 ---------------------------------------------------------*/
		else if(command.equals("/myReservation.cust")) {//'예약페이지 보기' 요청이면
			action = new CustomerReservationListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
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












