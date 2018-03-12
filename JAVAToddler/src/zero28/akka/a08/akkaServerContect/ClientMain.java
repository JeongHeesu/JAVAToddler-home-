package zero28.akka.a08.akkaServerContect;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ClientMain {
	private static final String akkaBatchFile = 
			"D:\\programming\\library\\AkkA\\akka-2.2.4\\bin\\akka.bat"; 

	public static void main(String[] args) {
		System.out.println("client 구성...");
		ClientMain.serverExecute();
	}

	private static void serverExecute() {

		Properties properties = System.getProperties();
		String executePath = properties.getProperty("user.dir");
		System.out.println("프로젝트 위치 : " + executePath);
		executePath += "\\bin\\";

		Runtime rt = Runtime.getRuntime();
		Process process;
		try {
			process = rt.exec(akkaBatchFile + 
					" zero28.akka.a06.akkaServerContect.server.LocalSystem",
					null, new File(executePath));
			
			 InputStream in = process.getInputStream();
			 InputStreamReader isr = new InputStreamReader(in);
			
			 BufferedReader br = new BufferedReader(isr);
			 String line;
			 while ((line = br.readLine()) != null) {
				 System.out.println(line);
			 }
			 in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
