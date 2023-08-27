package util;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Date;

//단방향 암호화 : 복구불가
public class SHA256 {
	/* 암호를 보호하는 가장 좋은 방법은 소금을 친 해싱을 사용하는 것.
	 * 소금이 고정이 아니면 로그인할 때 비밀번호의 소금도 변경되어 로그인 되지 않음
	 * 따라서 소금을 final로 고정시킴 
	 */
	private static final String salt = "";
	
	private SHA256() {}
	
	public static String encodeSHA256(String password){
		String result = "";
		
		try {
			byte[] saltByte = salt.getBytes();
			byte[] passwordByte = password.getBytes("utf-8");
			
			/*
			 * 기본값(0)으로 채워진 byte 객체에 바이트로 변경된 소금값과 비번으로 채움 
			 */
			byte[] saltPassword = new byte[saltByte.length + passwordByte.length];
			
			//소금값을 붙일 때 비번 앞, 뒤 상관 없음. (일반적으로 앞에 붙임)
			//앞:소금값, 뒤:비밀번호값
			System.arraycopy(saltByte, 0, saltPassword, 0, saltByte.length);
			System.arraycopy(passwordByte, 0, saltPassword, saltByte.length, passwordByte.length);
			
			//SHA-256 알고리즘 사용하여 단방향 암호화
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			//md.update() : 입력한 값을 해싱하는 함수, 매개값은 바이트 배열만 가능함. 
			md.update(saltPassword);
			
			//다이제스트를 이용해 비밀번호를 갱신함.
			byte[] saltPasswordDigest = md.digest(); 
			
			/*
			 * StringBuffer(멀티스레드) | StringBulider(싱글스레드) 
			 * 문자열 연산이 많을 때 사용하여 메모리의 효율을 높인다.
			 */
			
			StringBuffer sb = new StringBuffer();
			for(int i=0; i<saltPasswordDigest.length; i++) {	
				sb.append(Integer.toString((saltPasswordDigest[i]&0xFF)+256,16).substring(1));
										//((saltPasswordDigest[i]&0xFF)+0x100,16).substring(1));
			}
			result = sb.toString();
			/*
			 ※바이트 배열값 하나하나를 16진수로 만드는 방법
			 CPU의 연산장치는 정수 int기준으로 하기 때문에 음수값 처리를 위해 나머지값을 부호비트로 채우고
			 &0xFF를 이용해 부호비트가 1이면 0으로 바꾸어준다.
			 (예)
			 byte 10001010 (음수) ⇒ int 111111111111111111111111'10001010'	(음수값이라서 부호비트가 1로 채워짐)
			 						 & 000000000000000000000000'11111111'	(&0xFF를 이용해 부호비트를 0으로 바꿈)
			 						-------------------------------------
			 						   000000000000000000000000'10001010' 로 바뀜.
			
			 
			 10진수 100(0x100) 더하는 이유? 강제로 3자리의 String으로 변경하기 위함
			 1 → 101
			 2 → 102 
			 ... 
			 10 → 10a
			 11 → 10b
			 12 → 10c
			 ...
			 15 → 10f
			 16 → 110
			 
			 위 결과에서 subString을 이용해 제일 앞의 1을 제외시킨 나머지 2자리 ⇒ 16진수
			  
			 */
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("encodeSHA256 : " + result);
		return result;
	}
	
	//임시비밀번호 생성 : 8자리의 '영어대소문자+숫자'가 결합
	public static String getRandomPassword(int size) {//size가 8이면 8자리 임시 비밀번호가 생성
		
		//특수문자 제외
		char[] charSet = new char[] {
			'0','1','2','3','4','5','6','7','8','9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
		};
		
		StringBuffer sb = new StringBuffer();
		SecureRandom sr = new SecureRandom(); //강력한 난수를 발생시키는 클래스
		sr.setSeed(new Date().getTime());
		
		int len = charSet.length;
		int idx = 0;
		for(int i=0; i<size; i++) {
			//[방법-1]
			//idx = (int)(Math.random()*len) + 0;
			
			//[방법-2]
			//sr.nextInt(len);
			idx = sr.nextInt(charSet.length); //랜덤으로 인덱스 번호를 추출 (예)idx=11
			sb.append(charSet[idx]);//(예)charSet[11] = 'A' ⇒ charSet[0]='A'
		}
		return sb.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
