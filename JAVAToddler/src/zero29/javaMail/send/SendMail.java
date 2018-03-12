package zero29.javaMail.send;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import zero29.javaMail.res.MailMessageBean;
import zero29.javaMail.res.MyAuthenticator;

public class SendMail {
	private final String HOST = "smtp.gmail.com";
	private final String CHARSET = "UTF-8";
	private Properties sendProps = null;
	
	public void mailSendingEnvironment(){
		sendProps = new Properties();
		// 프로퍼티 값 인스턴스 생성과 기본세션(SMTP 서버 호스트 지정)
		sendProps.put("mail.smtp.starttls.enable", "true");
		sendProps.put("mail.smtp.auth", "true");
		sendProps.put("mail.smtp.socketFactory.port", 465);  
		sendProps.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		sendProps.put("mail.smtp.socketFactory.fallback", "false");
		sendProps.put("mail.smtp.host", HOST);
		sendProps.put("mail.transport.protocol", "smtp");
	}
	
	public void mailSending(MailMessageBean msgBean){
		MyAuthenticator auth = new MyAuthenticator(msgBean.getSenderID(), 
				msgBean.getSenderPwd());

		Session mailSession = Session.getDefaultInstance(sendProps, auth);

		// 메일 송신자 설정
		Message msg = new MimeMessage(mailSession);
		// 메일 수신자 설정
		InternetAddress[] address = new InternetAddress[10];
		try {
			msg.setFrom(new InternetAddress(msgBean.getSenderEmail(), 
					msgBean.getSenderName(), CHARSET));
			
			address[0] = new InternetAddress(msgBean.getReceiverEmail());

			msg.setRecipient(Message.RecipientType.TO, address[0]); //받는 사람설정
			
			msg.setSubject(msgBean.getSubject());                 // 제목 설정
			msg.setSentDate(new java.util.Date());                // 보내는 날짜 설정
			
			// 내용 설정 (HTML 형식)
			msg.setContent(msgBean.getContent(), "text/html;charset=UTF-8");   
			
			Transport.send(msg); // 메일 보내기
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} 
	}
}
