javaMail 메일 송,수신
  * 개인용 윈도우 서버의 smtp 메일 송신 서버 구축을통한 메일 전송은 전송용 도메인이 정상적인 White Domain이 
      아니므로 스펨 메일로 분류되어 수신자에게 수신되지 않을수 있음.
    
1. 관련 라이브러리 다운로드
   http://www.oracle.com/technetwork/java/index-138643.html
     javamail1_4_5.zip
      
   api : https://javamail.java.net/nonav/docs/api/
   
    자바1.6 이상 버젼에서 JAF(JavaBeans Activation Framework) activation.jar 파일 요구됨. 
   http://www.oracle.com/technetwork/java/jaf11-139815.html
      jaf-1_1_1.zip 다운로드
         
2. 관련 용어
   2.1 SMTP(Simple Mail Transfer Protocol)
     2.1.1 메일 전송시 사용되는 표준 프로토콜
     2.1.2 전용 포트는 25
   
   2.2 POP3(Post Office Protocol)
     2.2.1 메일 수신측이 메일 서버로부터 메일 다운로드시 사용되는 프로토콜
     2.2.2 보통 110을 포트를 설정
     
3. 주요 예약 포트(TCP)
   80 : http
   443 : https(SSL)
   23 : telnet
   21 : ftp
   22 : ssh
   25 : smtp
   3389 : 윈도우 원격데스트탑
   110 : pop3
   445 or 139 : 파일 및 프린터공유
   53 : dns
   3306 : mySql
   1521 : oracle listener
   8080 : http alternative(default)
   ...
   
4. SMTP 서비스 사별 설정
   4.1 구글
	   Properties props = new Properties();
	   props.put("mail.transport.protocol", "smtp");
	   props.put("mail.smtp.host", "smtp.gmail.com");
	   props.put("mail.smtp.port", "465");
	   props.put("mail.smtp.starttls.enable", "true");
	   props.setProperty("mail.smtp.socketFactory.class",
	                   "javax.net.ssl.SSLSocketFactory");
	   props.put("mail.smtp.auth", "true");
	   
	   Authenticator auth = new SMTPAuthenticator();
	   Session mailSession = Session.getDefaultInstance(props, auth);
	   
   4.2 네이버
       Properties props = new Properties();
	   props.put("mail.transport.protocol", "smtp");
	   props.put("mail.smtp.host", "smtp.naver.com");
	   props.put("mail.smtp.port", "587");
	   props.put("mail.smtp.starttls.enable", "true");
	   props.setProperty("mail.smtp.ssl.trust", "smtp.naver.com");
	   props.put("mail.smtp.auth", "true");
	   
	   Authenticator auth = new SMTPAuthenticator();
	   Session mailSession = Session.getDefaultInstance(props, auth);

   4.3 네이트
       Properties props = new Properties();
	   props.put("mail.transport.protocol", "smtp");
	   props.put("mail.smtp.host", "smtp.nate.com");
	   props.put("mail.smtp.port", "465");
	   props.put("mail.smtp.ssl.enable", "true");
	   props.put("mail.smtp.auth", "true");
	   
	   Authenticator auth = new SMTPAuthenticator();
	   Session mailSession = Session.getDefaultInstance(props, auth);
 
   4.4 한메일
       Properties props = new Properties();
	   props.put("mail.transport.protocol", "smtp");
	   props.put("mail.smtp.host", "smtp.daum.com");
	   props.put("mail.smtp.port", "465");
	   props.put("mail.smtp.ssl.enable", "true");
	   props.put("mail.smtp.auth", "true");
	   
	   Authenticator auth = new SMTPAuthenticator();
	   Session mailSession = Session.getDefaultInstance(props, auth);
	   
5. 전송 에러
   에러 모음 : http://blog.daum.net/seee50/15859312 참조
   
         