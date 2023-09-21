package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.board.BoardFileDownAction;
import action.board.Notice_BoardDeleteAction;
import action.board.Notice_BoardListAction;
import action.board.Notice_BoardUpdateAction;
import action.board.Notice_BoardUpdateFormAction;
import action.board.Notice_BoardViewAction;
import action.board.Notice_BoardWriteAction;
import action.board.QnA_BoardDeleteAction;
import action.board.QnA_BoardUpdateFormAction;
import action.board.QnA_BoardListAction;
import action.board.QnA_BoardReplyAction;
import action.board.QnA_BoardReplyFormAction;
import action.board.QnA_BoardUpdateAction;
import action.board.QnA_BoardViewAction;
import action.board.QnA_BoardWriteAction;
import action.board.QnA_BoardWriteForm;
import action.board.QnA_SearchAction;
import action.board.QnA_pwCheckAction;
import action.board.Review_BoardDeleteAction;
import action.board.Review_BoardListAction;
import action.board.Review_BoardUpdateAction;
import action.board.Review_BoardUpdateFormAction;
import action.board.Review_BoardViewAction;
import action.board.Review_BoardWriteAction;
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
		
		/*-- 글 목록보기 ---------------------------------------------------------------------------*/
		if(command.equals("/qna_boardList.bo")){
			action = new QnA_BoardListAction();
			System.out.println("qna_boardList 실행됨");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(command.equals("/notice_boardList.bo")){
			action = new Notice_BoardListAction();
			System.out.println("notice_boardList 실행됨");
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
		
		/*-- 글 등록하기 ---------------------------------------------------------------------------*/
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
			System.out.println("qna_BoardWriteAction 실행됨");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/notice_boardWrite.bo")){ 
			request.setAttribute("showPage", "user/notice/notice_Board_Write.jsp");
			forward = new ActionForward("template.jsp",false);
			System.out.println("notice_boardWrite 실행됨");
		}
		
		else if(command.equals("/notice_BoardWriteAction.bo")){			
			action = new Notice_BoardWriteAction();
			System.out.println("notice_BoardWriteAction 실행됨");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/review_boardWrite.bo")){ 
			request.setAttribute("showPage", "user/review/review_Board_Write.jsp");
			forward = new ActionForward("template.jsp",false);
			System.out.println("review_boardWrite 실행됨");
		}
		
		else if(command.equals("/review_BoardWriteAction.bo")){			
			action = new Review_BoardWriteAction();
			System.out.println("review_BoardWriteAction 실행됨");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/review_boardList.bo")){			
			action = new Review_BoardListAction();
			System.out.println("review_BoardListAction 실행됨");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

		/*-- 글 내용보기 ---------------------------------------------------------------------------*/
		else if(command.equals("/qna_boardView.bo")) {
			action = new QnA_BoardViewAction();
			System.out.println("qna_boardView 실행됨");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/notice_boardView.bo")) {
			action = new Notice_BoardViewAction();
			System.out.println("qna_boardView 실행됨");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/review_boardView.bo")) {
			action = new Review_BoardViewAction();
			System.out.println("review_boardView 실행됨");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		/*-- 파일 다운로드 ---------------------------------------------------------------------------*/
		else if(command.equals("/fileDown.bo")) {
			action = new BoardFileDownAction();
			System.out.println("fileDown 실행됨");
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*-- 글 수정하기 ---------------------------------------------------------------------------*/
		else if(command.equals("/qna_pwCheckForm.bo")) { //비밀번호가 있는 게시물이라면
			request.setAttribute("showPage", "user/qna/qna_pwCheck.jsp");
			forward = new ActionForward("template.jsp", false);	
			System.out.println("qna_pwCheck 실행됨");	
		}
		
		else if(command.equals("/qna_pwCheck.bo")) {//비밀번호 확인
			action = new QnA_pwCheckAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/qna_boardUpdateForm.bo")) { //수정하기 폼 보기
			action = new QnA_BoardUpdateFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if(command.equals("/qna_boardUpdateAction.bo")) { //수정요청 처리
			action = new QnA_BoardUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/notice_boardUpdateForm.bo")) { //수정하기 폼 보기
			action = new Notice_BoardUpdateFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/notice_boardUpdateAction.bo")) { //수정요청 처리
			action = new Notice_BoardUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/review_boardUpdateForm.bo")) { //수정하기 폼 보기
			action = new Review_BoardUpdateFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/review_boardUpdateAction.bo")) { //수정요청 처리
			action = new Review_BoardUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/*-- 글 삭제하기 ---------------------------------------------------------------------------*/
		else if(command.equals("/qna_boardDelete.bo")) {
			action = new QnA_BoardDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/notice_boardDelete.bo")) {
			action = new Notice_BoardDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/review_boardDelete.bo")) {
			action = new Review_BoardDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*-- 답글 적기 -----------------------------------------------------------------------------*/
		else if(command.equals("/qna_boardReplyForm.bo")) { //답글 폼 보기 요청
			action = new QnA_BoardReplyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/qna_boardReplyAction.bo")) {//답글 저장 요청
			action = new QnA_BoardReplyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*-- 검색하기 -----------------------------------------------------------------------------*/
		else if(command.equals("/qna_searchAction.bo")) {//답글 저장 요청
			action = new QnA_SearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*---------------------------------------------------------------------------------------*/
		if(forward != null) {
			if(forward.isRedirect() == true) {
				response.sendRedirect(forward.getPath());
			}else {
				request.getRequestDispatcher(forward.getPath()).forward(request, response);
			}
		}
	}

}
