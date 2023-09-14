package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.user.UserAllCarViewAction;
import action.user.UserCarBrandViewAction;
import action.user.UserCarDetailViewAction;
import action.user.UserCarSearchAction;
import action.user.UserFindIdAction;
import action.user.UserFindPwAction;
import action.user.UserJoinAction;
import action.user.UserJoinIdCheckAction;
import action.user.UserLogOutAction;
import action.user.UserLoginAction;
import action.user.UserOtherCarViewAction;
import action.user.UserPwChangeAction;
import vo.ActionForward;

/**
 * Servlet implementation class DogFrontController
 */

//확장자가 dog이면 무조건 DogFrontController로 이동하여 doProcess(request, response);메서드 실행함
@WebServlet("*.usr")
public class UserFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserFrontController() {
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
		
		System.out.println("[User]command : " + command); //어떤 요청인지 확인하기 위해 출력
		
		if(command.equals("/userMain.usr")) {//'index.jsp에서 userMain.jsp 뷰페이지 보기' 요청이면
			forward = new ActionForward("userMain.jsp",false);
		}
		
		
		/*------- '회원가입 폼 보기' → 처리 ---------------------------------------------------------*/
		else if(command.equals("/userJoinChoice.usr")) {//'index.jsp에서 userMain.jsp 뷰페이지 보기' 요청이면
			request.setAttribute("showPage", "user/joinChoiceForm.jsp");
			forward = new ActionForward("template.jsp",false); //반드시 디스패치 방식으로 포워딩
		}	
		
		else if(command.equals("/userJoin.usr")) {//'일반고객 가입' 요청이면
			request.setAttribute("showPage", "user/joinForm.jsp");
			forward = new ActionForward("template.jsp",false); //반드시 디스패치 방식으로 포워딩
		}
		else if(command.equals("/userJoinAction.usr")) {//'회원가입 처리'요청하면
			action = new UserJoinAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		}

		else if(command.equals("/user/userJoinIdCheck.usr")) {//'아이디 중복확인'요청하면
			action = new UserJoinIdCheckAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		}
		
		
		/*------- '로그인 폼 보기' → 처리 ---------------------------------------------------------*/
		else if(command.equals("/userLogin.usr")) {//'index.jsp에서 userMain.jsp 뷰페이지 보기' 요청이면
			request.setAttribute("showPage", "user/loginForm.jsp");
			forward = new ActionForward("template.jsp",false); //반드시 디스패치 방식으로 포워딩
		}
		

		else if(command.equals("/userLoginAction.usr")) {//'로그인 처리'요청하면
			action = new UserLoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		}
		
		
		/*------- '로그아웃' 요청 ---------------------------------------------------------*/
		else if(command.equals("/userLogout.usr")) {//'로그아웃' 요청이면
			action = new UserLogOutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		}
		
		/*------- '아이디찾기 폼' → 처리 ---------------------------------------------------------*/
		else if(command.equals("/userFindIdForm.usr")) {//'index.jsp에서 userMain.jsp 뷰페이지 보기' 요청이면
			request.setAttribute("showPage", "user/findIdForm.jsp");
			forward = new ActionForward("template.jsp",false); //반드시 디스패치 방식으로 포워딩
		}
		
		
		else if(command.equals("/userFindIdAction.usr")) {//'로그인 처리'요청하면
			action = new UserFindIdAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		}
		
		/*------- '비밀번호 찾기 폼' → 처리 ---------------------------------------------------------*/
		else if(command.equals("/userFindPwForm.usr")) {//'index.jsp에서 userMain.jsp 뷰페이지 보기' 요청이면
			request.setAttribute("showPage", "user/findPwForm.jsp");
			forward = new ActionForward("template.jsp",false); //반드시 디스패치 방식으로 포워딩
		}
		
		
		else if(command.equals("/userFindPwAction.usr")) {//'로그인 처리'요청하면
			action = new UserFindPwAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		}
		
		/*------- '비밀번호 수정 폼' → 처리 ---------------------------------------------------------*/
		else if(command.equals("/pwChangeForm.usr")) {//'index.jsp에서 userMain.jsp 뷰페이지 보기' 요청이면
			request.setAttribute("showPage", "user/pwChangeForm.jsp");
			forward = new ActionForward("template.jsp",false); //반드시 디스패치 방식으로 포워딩
		}

		else if(command.equals("/userPwChange.usr")) {//'비밀번호 수정'요청하면
			action = new UserPwChangeAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		}
		
		/*------- '인덱스에서 브랜드별 알아보기' → 처리 ---------------------------------------------------------*/
		else if(command.equals("/carBrandView.usr")) {//Car 메뉴나 index에서 '자동차 찾아보기' 요청이면
			action = new UserCarBrandViewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		}
		
		/*------- '상품 전체 리스트 보기 폼' → 처리 ---------------------------------------------------------*/
		else if(command.equals("/allCarListView.usr")) {//Car 메뉴나 index에서 '자동차 찾아보기' 요청이면
			action = new UserAllCarViewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		}
		
		/*------- '상품 검색 하면' → 처리 ---------------------------------------------------------*/
		else if(command.equals("/carSearch.usr")) {//'상품검색'요청하면
			action = new UserCarSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		}
		
		/*------- '상품 상세보기' → 처리 ---------------------------------------------------------*/
		else if(command.equals("/carDetailView.usr")) {//'차량상세보기'요청하면
			action = new UserCarDetailViewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		}
		
		/*------- '딜러의 다른 차량보기' → 처리 ---------------------------------------------------------*/
		else if(command.equals("/otherCarView.usr")) {//'딜러의 다른자동차보기'요청하면
			action = new UserOtherCarViewAction();
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












