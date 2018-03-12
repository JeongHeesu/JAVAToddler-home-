package zero30.apacheCommonMail.res;

import java.util.List;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class SimpleMailSend {
	
	public static void mailSend(SendMsgBean sendMsg, List<ReceiveMsgBean> receiveMsges){
		Email email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setAuthentication(sendMsg.getSenderID(), sendMsg.getSenderPwd());
		
		try {
			email.setSmtpPort(465);
			email.setSSLOnConnect(true);
			email.setSubject(sendMsg.getSubject());
			email.setMsg(sendMsg.getContent());
			email.setFrom(sendMsg.getSenderEmail(), sendMsg.getSenderName());
			
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
