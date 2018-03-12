package zero30.apacheCommonMail.res;

import java.io.File;

public class SendMsgBean {
	private String senderID;
	private String senderPwd;
	private String senderName;
	private String senderEmail;
	private String subject;
	private String content;
	private File sendFile;
	public String getSenderID() {
		return senderID;
	}
	public void setSenderID(String senderID) {
		this.senderID = senderID;
	}
	public String getSenderPwd() {
		return senderPwd;
	}
	public void setSenderPwd(String senderPwd) {
		this.senderPwd = senderPwd;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getSenderEmail() {
		return senderEmail;
	}
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public File getSendFile() {
		return sendFile;
	}
	public void setSendFile(File sendFile) {
		this.sendFile = sendFile;
	}
}
