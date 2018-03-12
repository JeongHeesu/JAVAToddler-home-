package zero27_ftp;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import zero27_ftp.res.FTPServerConnection;
import zero27_ftp.res.FTPServerFileTransfer;
import zero27_ftp.res.FTPServerReplyCheck;
import zero27_ftp.res.PropertiesCtrl;

public class TestMain {

	public static void main(String[] args) {
		// 서버 접속정보 프로퍼티스 파일 작성
		PropertiesCtrl.makeServerConnectionProperties();
		
		// 접속정보 확인
		Properties serverInfo = PropertiesCtrl.getServerConnectionProperties();
		System.out.println("server IP : " + serverInfo.get("serverIP"));
		System.out.println("server Port" + serverInfo.get("port"));
		System.out.println("ID : " + serverInfo.get("id"));
		System.out.println("PWD : " + serverInfo.get("pwd"));

		// 서버 접속 처리
        FTPClient client = FTPServerConnection.getServerConnection(
        		String.valueOf(serverInfo.get("serverIP")),
        		String.valueOf(serverInfo.get("port")), 
        		String.valueOf(serverInfo.get("id")), 
        		String.valueOf(serverInfo.get("pwd")),
        		"EUC-KR");	
		
        // 서버 접속 처리에따른 응답코드 취득
        // FTP Reply Code표 참조 : http://mayple.tistory.com/123
        int replyCode = client.getReplyCode();
        
        // FTP Server Reply Code 처리
        FTPServerReplyCheck replyCheck = new FTPServerReplyCheck();
        replyCheck.connectionCtrl(client, replyCode);
        
        // 서버 정보 취득
        try{
        	System.out.println("FTP 서버 타입 : " + client.getSystemType() + " / " +
	        	"서버측에 접속을위한 대기시간 : " + client.getConnectTimeout() + " / " +
		        "파일전송시의 인코딩(UTF-8) 타임 감지 여부 : " + client.getAutodetectUTF8() + " / " +
		        "송,수신시 파일 인코딩 설정 : " + client.getControlEncoding() + " / " +
		        "접속시 무한 대기를 방지하기위해 Keep Alive 설정." + " / " + 
		        "접속시 대기상태 : " + client.getKeepAlive() + " / " +  
		        "Keep Alive 시간 : " + client.getControlKeepAliveTimeout() + " / " + 
		        "서버측 default 접속포트 : " + client.getDefaultPort() + " / " +
		        "서버측 접속소켓 오픈 지연시간 설정 : " + client.getDefaultTimeout() + " / " +
		        "서버측 IP : " + client.getRemoteAddress() + " / " +
		        "서버측 Port : " + client.getRemotePort() + " / " +
		        "클라이언트 IP : " + client.getLocalAddress() + " / " + 
		        "클라이언트 Port : " + client.getLocalPort() + " / " +
		        "클라이언트의 서버측 Path 접근 요청 내용 : " + client.getStatus() + " / " +
		        "클라이언트의 서버 접속 경로 : " + client.printWorkingDirectory());
        }catch (Exception e) {
        	e.printStackTrace();
		}

        if(client.isConnected()){
        	
        	// 서버측 디렉토리 전체 별 전체 파일 정보 저장 처리 
        	Map<String, HashMap<String, FTPFile>> totalFilesInfo = 
        			FTPServerFileTransfer.getFileSystemInfo(client);
        	
        	// 파일 업로드
        	FTPServerFileTransfer.fileUpload(client, "test", 
        			new File("D:\\temp\\weather.xml"));
        	
        	// 파일 다운로드
        	FTPServerFileTransfer.fileDownload(client, "test", "weather.xml");
        }
        
        // 로그아웃 및 접속 종료처리
        FTPServerConnection.logoutNDisConnection(client);
	}
}
