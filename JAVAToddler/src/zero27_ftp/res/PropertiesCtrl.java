package zero27_ftp.res;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesCtrl {
	// FTPServer 정보(ip, port, 서버메세지, 제약사항(읽기만 허용....) 등)
	private static final String FILESYSTEMBASE_FILENAME = "D:\\workspace(java)\\JAVAToddler\\conf\\" +
			"ftpServerConntionInfo.properties";
	private static final String CLASSPATHMBASE_FILENAME = "ftpServerConntionInfo.properties";
	
	public static void makeServerConnectionProperties(){
		Properties connectionInfo = new Properties();
		FileOutputStream fileOS = null;
		
		// 클래스 패스 루트를 기준 팩키지 및 프로퍼티스 파일명 선언.
		try {
			fileOS = new FileOutputStream("conf/ftpServerConntionInfo.properties");
			
			// FTPServer IP Address
			//           Port
			//           ID
			//           PWD
			connectionInfo.setProperty("serverIP", "192.168.8.89");
			connectionInfo.setProperty("port", "21");
			connectionInfo.setProperty("id", "sem");
			connectionInfo.setProperty("pwd", "java");
			
			connectionInfo.store(fileOS, "서버정보작성");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(fileOS != null)	try { fileOS.close(); } catch (IOException e) {}
		}
	}
	
	public static Properties getServerConnectionProperties(){
		Properties connectionInfo = new Properties();
		FileInputStream fileSysBaseIS = null;
		InputStream classPathBaseIS = null;

		try {
			// FTP 서버 접속 정보를 프로퍼티스 파일 시스템으로부터 취득.
//			fileSysBaseIS = new FileInputStream(FILESYSTEMBASE_FILENAME);
//			connectionInfo.load(fileSysBaseIS);
			
			// or 클래스 패스 루트로부터 접근취득(build path root : src와 conf)
			// ClassLoader의 default 패스는 클래스 패스 루트.
			classPathBaseIS = PropertiesCtrl.class.getClassLoader().getResourceAsStream(CLASSPATHMBASE_FILENAME);
			connectionInfo.load(classPathBaseIS);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fileSysBaseIS != null)	try { fileSysBaseIS.close(); } catch (IOException e) {}
			if(classPathBaseIS != null)	try { classPathBaseIS.close(); } catch (IOException e) {}
		}
		return connectionInfo;
	}
	
	
}
