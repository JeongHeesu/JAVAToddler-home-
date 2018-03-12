package zero12_toolProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class TestMain {
	
	public static void main(String[] args) {
		Runtime rt = Runtime.getRuntime();

		// JVM활용 메모리 정보
		System.out.println("JVM이 사용 가능한 메모리 사이즈 : " + rt.freeMemory() + "byte");
		System.out.println("JVM이 사용 가능한 최대 메모리 사이즈 : " + rt.maxMemory() + "byte");
		System.out.println("JVM이 사용하고있는 메모리 사이즈 : " + rt.totalMemory() + "byte");
		
		// 가비지컬렉션 의뢰(
		// rt.gc();
		// JVM 종료
		// rt.exit(0);
		
		Process start = null;
		try {
			start = rt.exec(new String[] { "java", "-version" });
			
			// 해당 프로세스 종료
			// start.destroy();
			// 해당 프로세스의 inputStream 반환
			// start.getInputStream();
			// 해당 프로세스의 outputStream 반환
			// start.getOutputStream();
			// 해당 프로세스의 종료시점까지 대기(스레드 정지) 후 종료시 정상종료 값(0) 반환
			// 스트리밍 제어시 waitFor()가 무한 대기 상황이 발생될수 있음
			//    process.getErrorStream().close() or 
			//    process.getInputStream().close() or
			//    process.getOutputStream().close()로 무한 대기상태 해결.
			// start.waitFor();
			
			// 프로세스 실행시의 에러처리.
			BufferedReader r = new BufferedReader(
					new InputStreamReader(start.getErrorStream()));
			String line = null;
			while ((line = r.readLine()) != null){
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		final String baseFileName = "HelloJava";
		final String packageName = "zero12_toolProvider";
		final String content = "package " + packageName + ";"
				+ "class " + baseFileName + "{"
				+ "     public static void main(String[] args){"
				+ "          System.out.println(\"Hello!!! Java\");" + "     }"
				+ "}";

		MakeJavaFile makeJavaFile = new MakeJavaFile(baseFileName + ".java");
		makeJavaFile.setContent(content);

		// 문자열기반 자바파일 대상 컴파일
		makeJavaFile.make();

		makeJavaFile.javaRun(packageName+"."+baseFileName, rt);
	}
}






