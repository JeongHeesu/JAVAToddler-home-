package zero13_javascriptEnginAPI.util;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

/**
 * <h3>자바 스크립트 엔진을 이용한 특정 자바스크립트 파일 접근 Invocable 객체 및 
 * Engine 객체 반환
 * </h3>
 * <ul>
 * <li>ScriptEngine : 특정 js 파일을 대상으로 자바의 자바스크립트 객체 생성</li>
 * <li>Invocable : interpreter의 컴파일 결과로 특정 js객체 및 해당 객체의 함수 반환 및 호출</li>
 * </ul>
 * @author inho jon
 * @see javax.script.ScriptEngine
 * @see javax.script.Invocable
 * @since 2013.05.01
 * @version 1.0.0
 */
public class ScriptBinding {
	private static ScriptEngine engine = null;
	private static Invocable invocable = null;
	
	static{
		engine = ScriptEngineInstance.getScriptEngine();
	}
	
	private ScriptBinding(String fileName){
		Reader in = null;
		try {
			in = new FileReader(fileName);
			engine.eval(in); 
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		invocable = (Invocable)engine;
	}
	
	private ScriptBinding(URL url){
		InputStreamReader reader = null;
		try {
			reader = new InputStreamReader(url.openStream());
			engine.eval(reader);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ScriptException e) {
			e.printStackTrace();
		} 
		invocable = (Invocable)engine;
	}
	
	public static Invocable getInvocable(String jsFilePath) 
			throws MalformedURLException{
		if(invocable == null){
			if(jsFilePath.contains("http")){
				new ScriptBinding(new URL(jsFilePath));
			}else{
				new ScriptBinding(jsFilePath);
			}
		}
		return invocable;
	}
}
