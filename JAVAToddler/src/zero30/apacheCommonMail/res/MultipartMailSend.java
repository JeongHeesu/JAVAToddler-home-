package zero30.apacheCommonMail.res;

import java.util.List;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public class MultipartMailSend {
	public static void mailSend(SendMsgBean sendMsg, List<ReceiveMsgBean> receiveMsges){
		// 첨부파일 설정
		EmailAttachment attachFile = new EmailAttachment();
		// 파일 시스템
		attachFile.setPath("C:\\Users\\Administrator\\Pictures\\ugc.jpg");
		// url
		// attachment.setURL(new URL("http://sstatic.naver.net/search/img3/h1_naver2.png"));
		attachFile.setDisposition(EmailAttachment.ATTACHMENT);
		attachFile.setDescription("첨부이미지1");
		attachFile.setName("ugc.jpg");
		
		// 첨부파일용 이메일 생성
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName("smtp.gmail.com");
		email.setAuthentication(sendMsg.getSenderID(), sendMsg.getSenderPwd());
		
		try {
			email.setSmtpPort(465);
			email.setSSLOnConnect(true);
			email.setSubject(sendMsg.getSubject());
			email.setMsg(sendMsg.getContent());
			email.setFrom(sendMsg.getSenderEmail(), sendMsg.getSenderName());
			// 첨부파일 추가
			email.attach(attachFile);
			for(ReceiveMsgBean msgBean : receiveMsges){
				email.addTo(msgBean.getReceiverEmail(), msgBean.getReceiverName());
			}
		} catch (EmailException e) {
			e.printStackTrace();
		}
		
		try {
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
}
