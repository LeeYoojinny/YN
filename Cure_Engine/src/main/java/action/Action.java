/**interface Action : 클래스들의 규격을 정의 **/

package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public interface Action {
	//추상메소드 : 반드시 자식클래스에서 재정의해야 함
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
		
}
