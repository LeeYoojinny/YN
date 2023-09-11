package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.board.BoardFileDownAction;
import action.board.QnA_BoardListAction;
import action.board.QnA_BoardViewAction;
import action.board.QnA_BoardWriteAction;
import action.board.QnA_BoardWriteForm;
import vo.ActionForward;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("*.bo")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		
		String command = requestURI.substring(contextPath.length());
		
		Action action = null;
		ActionForward forward = null;
		System.out.println("[Board]command : " + command);
		
		/*-- 글 목록보기 --*/
		if(command.equals("/qna_boardList.bo")){
			action = new QnA_BoardListAction();
			System.out.println("boardList 실행됨");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*
		else if(command.equals("/qna_BoardSearchAction.bo")) {
			action = new BoardSearchAction();
			System.out.println("BoardSearchAction 실행됨");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		*/
		
		/*-- 글 등록하기 --*/
		else if(command.equals("/qna_boardWrite.bo")){ 
			action = new QnA_BoardWriteForm();
			System.out.println("QnA_BoardWriteForm 실행됨");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("boardWrite 실행됨");
		}
		
		else if(command.equals("/qna_BoardWriteAction.bo")){			
			action = new QnA_BoardWriteAction();
			System.out.println("BoardWriteAction 실행됨");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*-- 글 내용보기 --*/
		else if(command.equals("/qna_boardView.bo")) {
			action = new QnA_BoardViewAction();
			System.out.println("qna_boardView 실행됨");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*-- 파일 다운로드 --*/
		else if(command.equals("/fileDown.bo")) {
			action = new BoardFileDownAction();
			System.out.println("fileDown 실행됨");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*-- 글 수정하기 --*/
		else if(command.equals("/qna_pwCheck.bo")) {
			request.setAttribute("showPage", "user/qna/qna_pwCheck.jsp");
			forward = new ActionForward("template.jsp", false);	
			System.out.println("boardUpdate 실행됨");	
		}
		else if(command.equals("/qna_boardUpdate.bo")) {
			forward = new ActionForward("boardUpdate.jsp", false);
			System.out.println("boardUpdate 실행됨");	
		}
		/*
		else if(command.equals("/BoardUpdateAction.bo")) {
			action = new BoardUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		*/
		
		/*-- 글 삭제하기 --*/
		/*
		else if(command.equals("/boardDelete.bo")) {
			forward = new ActionForward("boardDelete.jsp", false);
			System.out.println("boardDelete 실행됨");	
		}
		else if(command.equals("/BoardDeleteAction.bo")) {
			action = new BoardDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		*/
		
		if(forward != null) {
			if(forward.isRedirect() == true) {
				response.sendRedirect(forward.getPath());
			}else {
				request.getRequestDispatcher(forward.getPath()).forward(request, response);
			}
		}
	}

}
