package zero13_javascriptEnginAPI.localScriptContact;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import sun.org.mozilla.javascript.internal.NativeArray;
import sun.org.mozilla.javascript.internal.NativeObject;

// 자바스크립트 파일 검증 및 결과 출력
// *  
public class ScriptingTest1 {
    // D:\workspace(java)\JAVAToddler\src\zero13_javascriptEnginAPI\localScriptContact\test.js 를
	// 실행시 프로그램 arguments로 전달 설정.
	public static void main(String[] args) {
		// 스크립트엔진 객체 생성을위한 매니져객체 생성.
		ScriptEngineManager scriptManager = new ScriptEngineManager();
		
		// 확장자 js 파일류를 대상으로한 스크립트 엔진 객체 생성
		ScriptEngine engine = scriptManager.getEngineByExtension("js");
		
		// 실행 대상의 스크립트 파일명.
		
		// javascript 변수를 작성하기위한 key=value 맵핑 객체.
		Bindings bindings = engine.createBindings();
		
		String fileName = args[0];
		
		if(fileName == null) usage();
		
		Reader in=null;
		try {
			in = new FileReader(fileName);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try {
			// Reader를통해 내부 버퍼에 저장된 javascript code 파악 및 컴파일.
			engine.eval(in);
			
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
	
	static void usage(){
		System.err.println("Usage : java RunScript [-Dname=value... script.js");
		System.exit(1);
	}
}
