/**
 * 포워딩 정보를 저장
 * - 컨트롤러에서 각 요청을 받아서 처리한 후 
 * 	 최종적으로 뷰페이지(.jsp)로 포워딩 처리 시 
 * 	 이동할 뷰 페이지의 url과 포워딩 방식(디스패치-기존요청, 리다이렉트-새요청)을
 * 	 편리하게 다루기위해 클래스로 설계 
 */

package vo;

public class ActionForward {
	private String path = null; //*.dog(요청), 이동할 뷰 페이지의 url(.jsp로 끝나는 페이지), 
	
	//기본값 false = dispatcher, true = redirect
	private boolean isRedirect = false;
	
	//매개변수가 없는 생성자 (★반드시 존재해야함)
	public ActionForward() {}
	
	//매개변수가 있는 생성자
	public ActionForward(String path, boolean isRedirect) {
		super();
		this.path = path;
		this.isRedirect = isRedirect;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	
}
