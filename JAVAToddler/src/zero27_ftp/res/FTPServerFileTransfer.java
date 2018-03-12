package zero27_ftp.res;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FTPServerFileTransfer {
	private static Map<String, HashMap<String, FTPFile>> totalFilesInfo = 
			new HashMap<String, HashMap<String, FTPFile>>();

	static{
		totalFilesInfo.put("/", null);
	}
	
	public static Map<String, HashMap<String, FTPFile>> getFileSystemInfo(FTPClient client) {
		// 서버 측 디렉토리 리스트 취득 및 저장
		setDirectorysName(client);
		
		try {
			Iterator<String> dirItr = totalFilesInfo.keySet().iterator();
			while(dirItr.hasNext()){
				String dir = dirItr.next();
				// FTP 서버의 파일시스템 루트 접근
				client.changeWorkingDirectory(dir);
				
				// 해당 패스의 파일 또는 디렉토리 취득
				FTPFile[] files = client.listFiles();
				for(FTPFile file : files){
					// 파일정보 출력
					prtFileNDirInfo(file);
					
					if(file.isFile())
						// 파일 저장
						setFilesInfo(dir, file);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return totalFilesInfo;
	}
	
	private static void setDirectorysName(FTPClient client){
		FTPFile[] directories = null;
		try {
			System.out.println("서버측 현재 패스명 : " + client.printWorkingDirectory());
			
			// 서버측 현재 패스의 디렉토리 리스트 취득
			directories = client.listDirectories();
			for(FTPFile dir : directories){
				totalFilesInfo.put(dir.getName(), null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void setFilesInfo(String dir, FTPFile file){
		if(totalFilesInfo.get(dir) == null){
			HashMap<String, FTPFile> fileInfo = new HashMap<String, FTPFile>();
			fileInfo.put(file.getName(), file);
			totalFilesInfo.put(dir, fileInfo);
		}else{
			totalFilesInfo.get(dir).put(dir, file);
		}
	}
	
	private static void prtFileNDirInfo(FTPFile file){
		System.out.println("대상 파일의 디렉토리여부 : " + file.isDirectory());
		System.out.println("대상 파일의 파일여부 : " + file.isFile());
		System.out.println("대상 파일의 미분류 여부 : " + file.isUnknown());
		System.out.println("대상 파일의 이름 : " + file.getName());
		System.out.println("대상 파일의 사이즈 : " + file.getSize());
		System.out.println("대상 파일의 저장시간 : " + file.getTimestamp());
		System.out.println("대상 파일의 타입 : " + file.getType());
		System.out.println("대상 파일의 업로더 : " + file.getUser());
	}
	
	// FTP 파일 업로드 
	public static void fileUpload(FTPClient client, String uploadPath, File uploadFile){
         FileInputStream fis = null;
         try {
        	 // 업로드 패스 설정
        	 client.changeWorkingDirectory(uploadPath);
        	 
             fis = new FileInputStream(uploadFile);

             // 업로드 처리
             boolean isSuccess = client.storeFile(uploadFile.getName(), fis);
             
             if (isSuccess) {
                 System.out.println("업로드 성공");
             }
         } catch(IOException ex) {
             System.out.println(ex.getMessage());
         } finally {
             if (fis != null) try { fis.close(); } catch(IOException ex) {}
         }
     }

	// FTP 파일 다운로드
	public static void fileDownload(FTPClient client, String downloadPath, 
		 String downloadFileName){
 
    	 // 다운로드 파일 저장 폴더 및 이름 설정.
         File file = new File("d:\\temp", downloadFileName);
         
         FileOutputStream fos = null;
         try {
        	 client.changeWorkingDirectory(downloadPath);
             fos = new FileOutputStream(file);
             
             // 다운로드
             boolean isSuccess = client.retrieveFile(downloadFileName, fos);
             if (isSuccess) {
                 System.out.println("다운로드 성공");
             } else {
            	 System.out.println("다운로드 실패에따른 서버 응답코드 : " + client.getReplyCode());
             }
         } catch(IOException ex) {
             System.out.println(ex.getMessage());
         } finally {
             if (fos != null) try { fos.close(); } catch(IOException ex) {}
         }
	}
}
