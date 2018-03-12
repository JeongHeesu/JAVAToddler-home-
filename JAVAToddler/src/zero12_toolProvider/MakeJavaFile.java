package zero12_toolProvider;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class MakeJavaFile {
	private String fileName;
	private String content;

	public MakeJavaFile(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void make() {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

		SimpleJavaFileObject fileObj = new DynamicJavaSourceCodeObject(
				"zero12_toolProvider.HelloJava", this.content);

		JavaFileObject[] objects = new JavaFileObject[] { fileObj };

		StandardJavaFileManager stdFileManager = 
				compiler.getStandardFileManager(null, Locale.getDefault(),
						Charset.forName("UTF-8"));

		Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(objects);

		Iterable<String> compileOptions = Arrays.asList(new String[] { "-d",
				"bin" });

		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();

		CompilationTask compileTask = compiler.getTask(null, stdFileManager,
				diagnostics, compileOptions, null, compilationUnits);

		// new DynamicJavaSourceCodeObject("common_test_toolProvider.HelloJava")
		// 설정기반
		// 해당 팩키지 HelloJava 바이트코드 생성
		boolean status = compileTask.call();

		if (!status) {
			for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
				System.out.format("Error on line %d in %s",
						diagnostic.getLineNumber(), diagnostic);
			}
		}

		try {
			stdFileManager.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean javaRun(String filename, Runtime rt) {
		// 어플리케이션 실행위치
		Properties properties = System.getProperties();
		String executePath = properties.getProperty("user.dir");
		System.out.println("프로젝트 위치 : " + executePath);
		executePath += "\\bin\\";

		// 자바 홈
		Map<String, String> map = System.getenv();
		String java_home = map.get("JAVA_HOME");
		System.out.println("java home 위치 : " + java_home);

		String orderString = "java " + filename;
		
		System.out.println("실행 : " + orderString);
		Process p;
		try {
//			p = rt.exec(orderString)
			p = rt.exec("java zero12_toolProvider.HelloJava", null, new File(executePath));
			
			if(p.waitFor() != 0){
				getOutAndErrStream(p);
			}
			
			getOutAndInputStream(p);
			
			rt.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	private String getOutAndErrStream(Process p){
		new StreamGobbler(p.getErrorStream()).start();
		return "";
	}
	
	private String getOutAndInputStream(Process p){
		new StreamGobbler(p.getInputStream()).start();
		return "";
	}
	
	public class StreamGobbler extends Thread
	{
		InputStream is = null;
	    public StreamGobbler(InputStream is){
	        this.is = is;
	    }

	    public void run(){
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;
			try{
				while ( (line = br.readLine()) != null)
				    System.out.println(line);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	}
}
