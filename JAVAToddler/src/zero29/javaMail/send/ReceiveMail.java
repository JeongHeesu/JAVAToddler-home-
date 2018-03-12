package zero29.javaMail.send;

import zero29.javaMail.res.GMailUtilities;
import zero29.javaMail.res.ReceiveMailMessageBean;

public class ReceiveMail {
	public Object getMail(String userID, String userPWD) {
		ReceiveMailMessageBean[] dtos = null;
		try {
			GMailUtilities gmail = new GMailUtilities();
			gmail.setUserPass(userID, userPWD); // Gmail 계정 메일주소, 암호
			gmail.connect();
			gmail.openFolder("INBOX");

			int totalMessages = gmail.getMessageCount();
			int newMessages = gmail.getNewMessageCount();

			System.out.println("Total messages = " + totalMessages);
			System.out.println("New messages = " + newMessages);
			System.out.println("-------------------------------");

			dtos = gmail.getAllMessages();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return dtos;
	}
}
