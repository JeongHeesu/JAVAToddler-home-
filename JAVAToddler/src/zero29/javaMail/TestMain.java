package zero29.javaMail;

import zero29.javaMail.res.MailMessageBean;
import zero29.javaMail.send.ReceiveMail;
import zero29.javaMail.send.SendMail;

public class TestMain {

	public static void main(String[] args) {
		SendMail sendMail = new SendMail();
		sendMail.mailSendingEnvironment();
		
		// 개인 gmail 계정 ID와 패스워드로 테스트
		MailMessageBean msgBean = new MailMessageBean();
		msgBean.setSenderID("bagrant11@gmail.com");
		msgBean.setSenderPwd("jon166111");
		msgBean.setSenderName("전인호");
		msgBean.setSubject("테스트 메일");
		msgBean.setContent("테스트 메일입니다.");
		msgBean.setReceiverEmail("bagrantt@naver.com");
		sendMail.mailSending(msgBean);
		
//		ReceiveMail receiveMail = new ReceiveMail();
//		receiveMail.getMail("gmail id", "gmail password");
	}

}
