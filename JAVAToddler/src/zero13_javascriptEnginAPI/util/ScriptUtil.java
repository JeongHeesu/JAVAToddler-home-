package zero13_javascriptEnginAPI.util;

import java.net.MalformedURLException;
import java.util.Iterator;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;

/**
 * <h3>스크립트 유틸리티</h3>
 * <pre>
 * 1. 스크립트 엔진 정보 반환
 * 2. 
 * </pre>
 * @author inho jon
 * @since 2013.05.01
 * @version 1.0.0
 */
public class ScriptUtil {
	private ScriptEngine engine =  null;
	
	public ScriptUtil(String jsFilePath){
		try {
			engine = (ScriptEngine)ScriptBinding.getInvocable(jsFilePath);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 스크립트 엔진 정보 설정 후 반환
	 * @param jsFilePath
	 * @return kr.or.ddit.javascript.ScriptInfomationBean 스크립트 엔진 정보
	 * @exception java.net.MalformedURLException
	 */
	public EngineInfomationBean getScriptInfomation(){
		EngineInfomationBean infomation = new EngineInfomationBean();
		infomation.setEngineName(this.engine.getFactory().getEngineName());
		infomation.setEngineVersion(this.engine.getFactory().getEngineVersion());
		infomation.setFileName(this.engine.FILENAME);
		infomation.setLanguageName(this.engine.getFactory().getLanguageName());
		infomation.setLanguageVersion(this.engine.getFactory().getLanguageVersion());
		
		return infomation;
	}
}
