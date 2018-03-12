package zero30.apacheCommonMail;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.SimpleEmail;

import zero30.apacheCommonMail.res.MultipartMailSend;
import zero30.apacheCommonMail.res.ReceiveMsgBean;
import zero30.apacheCommonMail.res.SendMsgBean;
import zero30.apacheCommonMail.res.SimpleMailSend;

public class TestMain {

	public static void main(String[] args) {
		// simple 메일 송신 코드
		SendMsgBean sendMsgBean = new SendMsgBean();
		sendMsgBean.setSenderID("bagrant11@gmail.com");
		sendMsgBean.setSenderPwd("jon166111");
		sendMsgBean.setSenderName("전인호");
		sendMsgBean.setSenderEmail("bagrant11@gmail.com");
		sendMsgBean.setSubject("[제목]테스트 메일....");
		sendMsgBean.setContent("[내용]내용무");
		
		List<ReceiveMsgBean> receiveMsges = new ArrayList<ReceiveMsgBean>();
		ReceiveMsgBean receiveMsgBean = new ReceiveMsgBean();
		receiveMsgBean.setReceiverEmail("bagrantt@naver.com");
		receiveMsgBean.setReceiverName("전인호");
		
		receiveMsges.add(receiveMsgBean);
//		SimpleMailSend.mailSend(sendMsgBean, receiveMsges);
		
		// 첨부파일 메일 전송
		MultipartMailSend.mailSend(sendMsgBean, receiveMsges);
	}

}
