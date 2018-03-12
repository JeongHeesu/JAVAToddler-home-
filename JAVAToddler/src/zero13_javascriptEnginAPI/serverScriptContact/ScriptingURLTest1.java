package zero13_javascriptEnginAPI.serverScriptContact;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import sun.org.mozilla.javascript.internal.NativeArray;
import sun.org.mozilla.javascript.internal.NativeObject;

public class ScriptingURLTest1 {
	// test.js 파일을 서버의 Root에 위치시킴.
	public static void main(String[] args) {
		// 스크립트 엔진 매니저 객체 생성
		ScriptEngineManager scriptManager = new ScriptEngineManager();
		
		// 엔진 객체 생성
		ScriptEngine engine = scriptManager.getEngineByExtension("js");
		
		URL url = null;
		InputStreamReader reader = null;
		try {
			url = new URL( "http://localhost/test.js" );
			reader = new InputStreamReader( url.openStream());

			// Reader를통해 내부 버퍼에 저장된 javascript code 파악 및 컴파일.
			engine.eval( reader ); 
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		try {
			
			// 자바스크립트 함수 접근을위해 ScriptEngine을 Invocable로 캐스팅.
			Invocable invo = (Invocable)engine;
			
			// 자바스크립트 함수 add 호출 및 결과 출력.
			Object addFuncRtn = invo.invokeFunction("add", 1, 2);
			System.out.println("add 함수 호출 결과값 : " + addFuncRtn);
			
			// 자바스크립트 함수 minus 호출 및 결과 출력.
			Object minusFuncRtn = invo.invokeFunction("minus", 5, 4);
			System.out.println("minus 함수 호출 결과값 : " + minusFuncRtn);
			
			// 자바스크립트 변수 value1, value2의 값 취득.
			System.out.println("javascript value1 변수값 : " + engine.get("value1"));
			System.out.println("javascript value2 변수값 : " + engine.get("value2"));
			
			//  자바스크립트 변수 obj의 값 취득
			NativeObject obj = (NativeObject)engine.get("obj");
			// obj의 모든 키값 취득
			Object[] keyArr = obj.getAllIds();
			System.out.println("javascript obj 변수값 x : " + NativeObject.getProperty(obj, "x") + 
					" / javascript obj 변수값 y : " + NativeObject.getProperty(obj, "y"));
			
			//  자바스크립트 변수 obj의 값 취득
			NativeArray objArr = (NativeArray)engine.get("arr");
			
			for(Object val : objArr.getIds()){
				int i = (Integer)val;
				System.out.println("javascript objArr 변수값 : " + objArr.get(i, null));
			}
			
			//  자바스크립트 변수 obj의 값 취득
			NativeArray objArray = (NativeArray)engine.get("objArray");
			for(Object val : objArray.getIds()){
				int i = (Integer)val;
				System.out.println("javascript objArray 변수값 : " + objArray.get(i, null));
			}
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		
	}

}
