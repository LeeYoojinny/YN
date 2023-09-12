package action.board;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;

public class BoardFileDownAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String filename = request.getParameter("file_name");
		String display_num = request.getParameter("display_num");
		String savePath = "";
		
		if(display_num.equals("1")) {
			savePath = "upload/qna_file";
		}else if(display_num.equals("2")) {
			savePath = "upload/notice_file";
		}
		ServletContext context = request.getServletContext();
		String sDownloadPath = context.getRealPath(savePath);
		
		String fullFilePath = sDownloadPath +File.separator+ filename;
		
		File file = new File(fullFilePath);
		FileInputStream in = new FileInputStream(file);
		
		String sMimeType = request.getServletContext().getMimeType(fullFilePath);
		System.out.println("sMimeType(MIME 타입 유형) = "+sMimeType);
		
		if(sMimeType == null){//다운로드 할 파일의 MIME 타입이 제대로 반환되지 않으면 null
			sMimeType = "application/octet-stream";//기본 MIME 타입으로 지정
		}
		
		response.setContentType(sMimeType);
		String agent = request.getHeader("User-Agent");
		boolean ieBrowser = (agent.contains("MSIE") || agent.contains("Trident"));
		
		String fileNameEncoding;
		
		if(ieBrowser){//MS의 IE이거나 Trident이면							역슬래시 2개 넣어줘야 "+" 인식함, 16진수 유니코드값(%20)=빈 공백문자로 변경
			fileNameEncoding = URLEncoder.encode(filename,"utf-8").replaceAll("\\+","%20");
		}else{//Chrome이면
			fileNameEncoding = new String(filename.getBytes("utf-8"),"ISO-8859-1");//파일명을 UTF-8로 가져와서 크롬에서 사용하는 "ISO-8859-1"로 변경
		}
		
		response.setHeader("Content-Disposition", "attachment; filename="+fileNameEncoding);
		
		ServletOutputStream out2 = response.getOutputStream(); //out 변수는 내장객체 이름이므로 out2로 변수 선언

		byte[] b = new byte[4*1024*1024]; //4MB

		int numRead; //b바이트 배열 객체 단위로 다운로드할 파일정보를 읽는 객체
		while((numRead = in.read(b, 0, b.length)) != -1){ //읽어들일 객체가 없을때까지
			//웹브라우저에 출력
			out2.write(b, 0, numRead);	
		}


		//12. 자원해제
		//out2.flush(); //buffer 안에 있는것 모두 비우기, close 메소드가 있으면 생략가능
		out2.close(); //close를 하면 flush는 자동으로 처리
		in.close();
		
		return forward;
	}

}
