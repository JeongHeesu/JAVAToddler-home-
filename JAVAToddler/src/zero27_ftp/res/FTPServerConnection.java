package zero27_ftp.res;

import java.io.IOException;
import java.net.SocketException;
import org.apache.commons.net.ftp.FTPClient;

public class FTPServerConnection {
	
	public static FTPClient getServerConnection(String serverIP, String port, String id,
			String pwd, String encodingType){
		return getServerConnection(serverIP, Integer.parseInt(port), id, pwd, encodingType);
	}
	
	public static FTPClient  getServerConnection(String serverIP, int port, String id,
			String pwd, String encodingType){
		FTPClient client = new FTPClient();
		try {
			client.connect(serverIP, port);
			client.login(id, pwd);
			
			// 인코딩 설정
			client.setControlEncoding(encodingType);
			
			// 접속 수 FTP서버내 폴더 이동이 가능토록 설정
			client.enterLocalPassiveMode();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return client;
	}
	
	public static void logoutNDisConnection(FTPClient client){
		// 로그아웃
        try { client.logout(); } catch (IOException e) { e.printStackTrace(); }
        
        if(client.isConnected()){
        	// 접속 종료
        	try { client.disconnect(); } catch (IOException e) { e.printStackTrace(); }
        }
	}
}
