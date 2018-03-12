package zero29.javaMail.res;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.FetchProfile;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.sun.mail.pop3.POP3SSLStore;
import com.sun.mail.util.BASE64DecoderStream;

public class GMailUtilities {
	// POP3 서버 연결 세션
	private Session session = null;
	// INBOX, OUTBOX 등 접근 세션
	private Store store = null;
	private String username, password;
	// INBOX 폴더, OUTBOX 폴더 등
	private Folder folder;
	// 저장위치
	private static String mailsSavePath = "d:\\temp\\";

	public void setUserPass(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public void connect() throws Exception {
		String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

		Properties pop3Props = new Properties();

		pop3Props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
		pop3Props.setProperty("mail.pop3.socketFactory.fallback", "false");
		pop3Props.setProperty("mail.pop3.port", "995");
		pop3Props.setProperty("mail.pop3.socketFactory.port", "995");

		URLName url = new URLName("pop3", "pop.gmail.com", 995, "", username,
				password);
		// pop 서비스 대상 서버 연결 session 생성.
		session = Session.getInstance(pop3Props, null);
		// 인증 처리를 통한 세션 연결 처리
		store = new POP3SSLStore(session, url);
		store.connect();
	}

	public void openFolder(String folderName) throws Exception {
		// Open the Folder(INBOX, OUTBOX, etc..)
		folder = store.getDefaultFolder();
		folder = folder.getFolder(folderName); // INBOX

		if (folder == null) {
			throw new Exception("Invalid folder");
		}
		// try to open read/write and if that fails try read-only
		try {
			folder.open(Folder.READ_WRITE);
		} catch (MessagingException ex) {
			folder.open(Folder.READ_ONLY);
		}
	}

	public void closeFolder() throws Exception {
		folder.close(false);
	}

	public int getMessageCount() throws Exception {
		return folder.getMessageCount();
	}

	public int getNewMessageCount() throws Exception {
		return folder.getNewMessageCount();
	}

	public void disconnect() throws Exception {
		store.close();
	}

	public void printMessage(int messageNo) throws Exception {
		System.out.println("Getting message number: " + messageNo);
		Message m = null;
		try {
			m = folder.getMessage(messageNo);
			dumpEnvelope(m);
		} catch (IndexOutOfBoundsException iex) {
			System.out.println("Message number out of range");
		}
	}

	public void printAllMessageEnvelopes() throws Exception {
		// Attributes & Flags for all messages ..
		Message[] msgs = folder.getMessages();

		// Use a suitable FetchProfile
		FetchProfile fp = new FetchProfile();
		fp.add(FetchProfile.Item.ENVELOPE);
		folder.fetch(msgs, fp);

		for (int i = 0; i < msgs.length; i++) {
			System.out.println("--------------------------");
			System.out.println("MESSAGE #" + (i + 1) + ":");
			dumpEnvelope(msgs[i]);
		}
	}

	public void printAllMessages() throws Exception {
		// Attributes & Flags for all messages ..
		Message[] msgs = folder.getMessages();

		// Use a suitable FetchProfile
		FetchProfile fp = new FetchProfile();
		fp.add(FetchProfile.Item.ENVELOPE);
		folder.fetch(msgs, fp);

		for (int i = 0; i < msgs.length; i++) {
			System.out.println("--------------------------");
			System.out.println("MESSAGE #" + (i + 1) + ":");
			dumpEnvelope(msgs[i]);
		}
	}

	public ReceiveMailMessageBean[] getAllMessages() throws Exception {
		ReceiveMailMessageBean[] dtos = null;
		// Attributes & Flags for all messages ..
		Message[] msgs = folder.getMessages();

		dtos = new ReceiveMailMessageBean[msgs.length];

		// Use a suitable FetchProfile
		// 시간이 오래 걸리는 작업.
		FetchProfile fp = new FetchProfile();
		// 취득 의도 : FetchProfile.Item.ENVELOPE - 봉투
		// FetchProfile.Item.CONTENT_INFO - 편지지
		fp.add(FetchProfile.Item.CONTENT_INFO);
		folder.fetch(msgs, fp);

		MimeMultipart multiPart = null;
		for (int i = 0; i < msgs.length; i++) {
			System.out.println("0 : " + msgs[i].getFrom()[0].toString());
			System.out.println("1 : " + msgs[i].getSubject());
			System.out.println("2 : " + msgs[i].getSentDate());
			System.out.println("3 : " + msgs[i].getContentType());

			dtos[i] = new ReceiveMailMessageBean();
			dtos[i].setFrom(msgs[i].getFrom()[0].toString());
			dtos[i].setSubject(msgs[i].getSubject());
			dtos[i].setSentDate(msgs[i].getSentDate());

			// 첨부파일 유 - MimeMultipart / 무 - MimeMessage
			Object contents = msgs[i].getContent();
			// 컨텐츠에 첨부파일이 있는 메일의 경우
			if (contents instanceof MimeMultipart) {
				multiPart = (MimeMultipart) contents;
				System.out.print("Body : ");
				// 0 : 수신된 메일의 일반 텍스트 부분
				// 1 : 수신된 메일의 첨부파일 부분(배열 형태)
				BodyPart bp = multiPart.getBodyPart(0);
				System.out.println("4 : " + bp.getContent().toString());
				dtos[i].setContent(bp.getContent().toString());
			} else {
				System.out.println("4 : " + msgs[i].getContent().toString());
				dtos[i].setContent(msgs[i].getContent().toString());
			}
		}
		return dtos;
	}

	public static void dumpEnvelope(Message m) throws Exception {
		pr(" ");
		Address[] a;
		// FROM
		if ((a = m.getFrom()) != null) {
			for (int j = 0; j < a.length; j++)
				pr("FROM: " + MimeUtility.decodeText(a[j].toString()));
		}

		// TO
		if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
			for (int j = 0; j < a.length; j++) {
				pr("TO: " + MimeUtility.decodeText(a[j].toString()));
			}
		}

		// SUBJECT
		pr("SUBJECT: " + m.getSubject());

		// DATE
		Date d = m.getSentDate();
		pr("SendDate: " + (d != null ? d.toString() : "UNKNOWN"));

		// CONTENT TYPE
		String contentType = m.getContentType();
		pr("CONTENT TYPE: " + contentType);

		// CONTENT
		/*
		 * 단순한 구조의 메일은 MimeMessage 안에 바로 텍스트(text/plain, text/html)가 포함되는 구조로
		 * 되어있지만, 대부분의 메일 내용은 JavaMail 개발자 입장에서 보았을 때 메일본문이 바로 발견되는 것이 아니라
		 * MimeMessage 내부의 Multipart 안에 몇개의 BodyPart로 구성되어 있고 각 BodyPart 안에는
		 * 첨부파일 이 있으며, 어떤 BodyPart 안에는 또 다시 Multipart가 포함되고 그 안에
		 * 일반텍스(text/plain)나 HTML(text/html)형식의 메일 본문이 포함되어 있는 경우도 많다.
		 */
		pr("Message.getContent()-------------------");
		pr("CONTENT: " + m.getContent());

		// 첨부파일
		if (m.isMimeType("multipart/*")) {
			System.out.println("첨부파일이 포함된 메일");
			handleMultipart(m);
			// pr(str);
		}
	}

	/*
	 * 첨부파일을 다루는 메소드, 첨부파일을 로컬시스템에 다운로드한다 첨부파일은 Base64 인코딩된 경우와 그렇지 않은 경우로 구분하여
	 * 스트림을 사용한다 MimeMultipart 안에는 다수개의 MimeBodypart(파트)가 포함될 수 있고 각 파트 안에는
	 * 텍스트(text/plain, text/html), 첨부파일, 또 다른 MimeMultipart 등이 로 존재한다. 한개의 파트 안에
	 * 또 다른 멀티파트가 포함되어 있을 경우에는 그 멀티파트에 포함되어 있는 것은 텍스트나 HTML 이었다.
	 */
	public static String handleMultipart(Message msg) {
		String content = null;
		try {
			String disposition;
			BodyPart part;
			Multipart mp = (Multipart) msg.getContent(); // 멀티파트 구함

			int mpCount = mp.getCount();
			pr("멀티파트 안의 아이템 수:" + mpCount);
			for (int m = 0; m < mpCount; m++) {
				part = mp.getBodyPart(m); // 멀티파트내의 한개 파트 구함
				String partContentType = part.getContentType();
				disposition = part.getDisposition(); // 파트의 성격
				Object partContent = part.getContent(); // 한개 파트의 내용을 구함
				pr(m + 1 + "번 파트-----disposition:" + disposition);
				/* 멀티파트 내의 한개 파트가 메일 본문인 경우 */
				if (partContentType.startsWith("text/") && disposition == null) {
					String txtContent = part.getContent().toString();
					pr("멀티파트 안의 본문:" + txtContent);

				}/* 멀티파트 내의 한개 파트가 첨부파일이고 파일명이 한글인 경우 */
				else if (partContent instanceof BASE64DecoderStream) {
					BASE64DecoderStream ds = (BASE64DecoderStream) partContent;
					String filename = part.getFileName();
					// RFC 2047으로 인코딩된 문자열을 다시 디코딩한다.
					filename = MimeUtility.decodeText(filename);
					pr("한글 파일명:" + filename);
					byte[] buf = new byte[ds.available()];
					ds.read(buf);
					ds.close();

					// 첨부파일을 로컬시스템에 다운로드한다
					saveLocal(filename, buf);
				} else if (disposition != null
						&& (disposition.equals(Part.INLINE) || disposition
								.equals(Part.ATTACHMENT)))/* 첨부파일명이 영문인 경우 */
				{
					String filename = part.getFileName();
					filename = MimeUtility.decodeText(filename);
					pr("영문 파일명:" + filename);
					InputStream is = part.getInputStream();
					byte[] buf = new byte[is.available()];
					is.read(buf);
					is.close();
					saveLocal(filename, buf);
				} else {/* 한개의 파트 안에 다시 멀티 파트가 포함된 경우, 대부분 텍스트나 HTML */
					if (partContent instanceof MimeMultipart) {
						MimeMultipart multipart = (MimeMultipart) partContent;
						int cnt = multipart.getCount();
						for (int i = 0; i < cnt; i++) {
							BodyPart bp = multipart.getBodyPart(i);
							pr("파트 안의 멀티파트 정보:" + bp.getContentType());
							pr("파트 안의 멀티파트 내용:" + bp.getContent());
						}
					}
				}
			}// end of for()
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return content;
	}

	static String indentStr = "                                               ";
	static int level = 0;

	/**
	 * Print a, possibly indented, string.
	 */
	public static void pr(String s) {
		System.out.print(indentStr.substring(0, level * 2));
		System.out.println(s);
	}

	private static void saveLocal(String filename, byte[] buf) {
		FileOutputStream fout = null;
		try {
			fout = new FileOutputStream(mailsSavePath + filename);
			fout.write(buf);
			fout.close();
		} catch (Exception fne) {
			fne.printStackTrace();
		}
	}
}
