package zero13_javascriptEnginAPI.util;

import java.util.List;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

/**
 * <h3>파일시스템 또는 url 기반 특정 javascript 파일의 제어</h3>
 * <pre>
 * JSR223-Scripting for the Java Platform
 * Rhino라는 명칭으로 자바에 포함된 스크립팅 엔진을 활용해 특정 자바스크립트 파일의
 * 변수와 함수를 파악하고 변수 및 함수를 활용한다.
 * </pre>
 * @author inho jon
 * @since 2013.05.01
 * @version 1.0.0
 * @see javax.script.ScriptEngineManager
 */
public class ScriptEngineInstance{
	private static ScriptEngine engine = null;
	
	static{
		try{
			engine = new ScriptEngineManager().getEngineByExtension("javascript");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static ScriptEngine getScriptEngine() {
		if(engine == null)
			engine = new ScriptEngineManager().getEngineByName("js");
		return engine;	
	}
	
	public static List<ScriptEngineFactory> getEngineList(){
		List<ScriptEngineFactory> factorys = new ScriptEngineManager().getEngineFactories();
		return factorys;
	}
}
