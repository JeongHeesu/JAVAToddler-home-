package zero27_ftp.res;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPServerReplyCheck {

	public void connectionCtrl(FTPClient client, int replyCode) {
		boolean isDisconnect = false;

		if (110 == replyCode) {
			System.out.println("Restart marker reply.");
		} else if (120 == replyCode) {
			System.out.println("Service ready in nnn minutes.");
		} else if (125 == replyCode) {
			System.out.println("데이터 접속이 이미 열림; 전송시작");
		} else if (150 == replyCode) {
			System.out.println("File status okay; about to open data connection.");
		} else if (200 == replyCode) {
			System.out.println("명령 OK");
		} else if (202 == replyCode) {
			System.out.println("Command not implemented, superfluous at this site.");
		} else if (211 == replyCode) {
			System.out.println("System status, or system help reply.");
		} else if (212 == replyCode) {
			System.out.println("Directory status.");
		} else if (213 == replyCode) {
			System.out.println("File status.");
		} else if (214 == replyCode) {
			System.out.println("Help message.도움 메시지 (사용자를 위한 것)");
		} else if (215 == replyCode) {
			System.out.println("NAME system type.");
		} else if (220 == replyCode) {
			System.out.println("Service ready for new user.");
		} else if (221 == replyCode) {
			System.out.println("Service closing control connection.");
		} else if (225 == replyCode) {
			System.out.println("Data connection open; no transfer in progress.");
		} else if (226 == replyCode) {
			System.out.println("Closing data connection.");
		} else if (227 == replyCode) {
			System.out.println("Entering Passive Mode <h1,h2,h3,h4,p1,p2>.");
		} else if (228 == replyCode) {
			System.out.println("Entering Long Passive Mode.");
		} else if (229 == replyCode) {
			System.out.println("Extended Passive Mode Entered.");
		} else if (230 == replyCode) {
			System.out.println("User logged in, proceed.");
		} else if (250 == replyCode) {
			System.out.println("Requested file action okay, completed.");
		} else if (257 == replyCode) {
			System.out.println("PATHNAME created.");
		} else if (331 == replyCode) {
			System.out.println("사용자명 OK, 패스워드가 요구됨");
		} else if (332 == replyCode) {
			System.out.println("Need account for login.");
		} else if (350 == replyCode) {
			System.out.println("Requested file action pending further information.");
		} else if (421 == replyCode) {
			System.out.println("Service not available, closing control connection.");
			isDisconnect = true;
		} else if (425 == replyCode) {
			System.out.println("데이터 접속을 열 수 없음");
			isDisconnect = true;
		} else if (426 == replyCode) {
			System.out.println("Connection closed; transfer aborted.");
			isDisconnect = true;
		} else if (450 == replyCode) {
			System.out.println("Requested file action not taken.");
			isDisconnect = true;
		} else if (451 == replyCode) {
			System.out.println("Requested action aborted. Local error in processing.");
			isDisconnect = true;
		} else if (452 == replyCode) {
			System.out.println("에러를 기록한 파일");
		} else if (500 == replyCode) {
			System.out.println("문법 에러 (인식되지 않는 명령)");
		} else if (501 == replyCode) {
			System.out.println("문법에러 (유효하지 않는 인수)");
		} else if (502 == replyCode) {
			System.out.println("구현되지 않은 MODE 형태");
		} else if (503 == replyCode) {
			System.out.println("Bad sequence of commands.");
		} else if (504 == replyCode) {
			System.out.println("Command not implemented for that parameter.");
		} else if (521 == replyCode) {
			System.out.println("Supported address families are <af1, .., afn>");
		} else if (522 == replyCode) {
			System.out.println("Protocol not supported.");
		} else if (530 == replyCode) {
			System.out.println("Not logged in.");
		} else if (532 == replyCode) {
			System.out.println("Need account for storing files.");
		} else if (550 == replyCode) {
			System.out.println("Requested action not taken.");
		} else if (551 == replyCode) {
			System.out.println("Requested action aborted. Page type unknown.");
		} else if (552 == replyCode) {
			System.out.println("Requested file action aborted.");
		} else if (553 == replyCode) {
			System.out.println("Requested action not taken.");
		} else if (554 == replyCode) {
			System.out.println("Requested action not taken: invalid REST parameter.");
		} else if (555 == replyCode) {
			System.out.println("Requested action not taken: type or stru mismatch.");
		}

		// 정상 접속 처리에따른 코드 취득
		if (!FTPReply.isPositiveCompletion(replyCode)) {
			if(isDisconnect){
				// 접속 종료
				try {
					client.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("접속이 원만하지 않습니다.");
				System.exit(1);
			}
		}
		
	}

}
