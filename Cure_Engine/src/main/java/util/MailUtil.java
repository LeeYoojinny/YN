package util;


import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;

public class MailUtil {
	
	public void sendEmail(String from, String to, String subject, String content) {
		
		String host = "smtp.gmail.com";
		final String username = "yoojin266"; // 본인 구글 아이디
		final String password = "tsxtejccgkjatkwc"; // 시작 전 구글에서 생성한 앱 비밀번호 (각자 다름)

		// 포트번호
		final int port = 587; // 25:네이버, 4465:2차 보안인증으로 메일 전송이 되지 않음

		try {
			// 2. Property에 SMTP 서버정보를 설정(예:구글의 SMTP 서버 정보설정)-------------------------
			Properties properties = System.getProperties();

			// starttls Command를 사용할 수 있게(=enable) 설정
			// ※ start TLS : TLS 버전문제로 오류발생 - javax.net.ssl.SSLHandshakeException:No
			// appropriate protocol
			properties.put("mail.smtp.starttls.enable", "true"); // gmail은 무조건 true
			properties.put("mail.smtp.ssl.protocols", "TLSv1.2"); // 버전문제에 대한 해결

			// SMTP 서버주소로
			properties.put("mail.smtp.host", host); // "smtp.gmail.com"

			// auth Command를 사용하여 사용자 인증을 할 수 있게 설정
			properties.put("mail.smtp.auth", "true");// gmail은 무조건 true

			// 포트번호 설정
			properties.put("mail.smtp.port", port);// 587

			// 3. SMTP 서버 정보와 사용자 정보를 기반으로 Session 객체 생성-------------------------
			Session session = Session.getDefaultInstance(properties, // SMTP 서버 정보
														 new Authenticator() {// 사용자 인증정보 : Authenticator 추상클래스 생성자()정의와 생성을 동시에 함

														 // 추상클래스의 재정의 해야 할 메소드를 재정의
														 @Override
														 protected PasswordAuthentication getPasswordAuthentication() {
														 return new PasswordAuthentication(username, password); // 사용자인증정보 객체 리턴
														 }// 익명클래스 : 재정의 함과 동시에 객체 생성
														 });

			// 4. Message 클래스의 객체를 사용하여 수신자,제목,내용을 작성-------------------------
			Message message = new MimeMessage(session);

			// set the from and to address
			message.setFrom(new InternetAddress(from));// 보내는 사람 설정
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));// 받는 사람설정


			message.setSubject(subject); // 제목 설정
			message.setContent(content, "text/html; charset=utf-8");// 내용 설정
			message.setSentDate(new Date());// 보내는 날짜 설정

			Transport.send(message); // 메일 보내기
			System.out.println("메일이 정상적으로 전송되었습니다."); // 전송되었는지 확인을 위해 웹브라우저에 출력

		} catch (Exception e) {
			System.out.println("SMTP서버가 잘못 설정되었거나 서비스에 문제가 있습니다."); // 사용자에게 에러메세지를 웹브라우저에 출력
			e.printStackTrace(); // 콘솔에 출력하여 개발자가 에러를 찾기 쉽도록 하기 위함
		}

	}
	

}
			