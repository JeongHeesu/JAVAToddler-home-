package zeroetc4_morphemeanalysor.res;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import kr.ac.kaist.swrc.jhannanum.share.JSONReader;

import org.json.JSONException;

public class PlugInLoading {
    private static Map<String, String> pluginInfoMap = null;
    
    static {
    	loadPluginInformation();
    }
    
    // conf.zip내에 존재하는 plugin 리스트 로딩
    private static void loadPluginInformation() {
		pluginInfoMap = new HashMap<String, String>();
		pluginInfoMap.put("InformalSentenceFilter", "conf/plugin/SupplementPlugin/PlainTextProcessor/InformalSentenceFilter.json");
		pluginInfoMap.put("SentenceSegmentor", "conf/plugin/SupplementPlugin/PlainTextProcessor/SentenceSegmentor.json");
		pluginInfoMap.put("ChartMorphAnalyzer", "conf/plugin/MajorPlugin/MorphAnalyzer/ChartMorphAnalyzer.json");
		pluginInfoMap.put("UnknownMorphProcessor", "conf/plugin/SupplementPlugin/MorphemeProcessor/UnknownMorphProcessor.json");
		pluginInfoMap.put("SimpleMAResult09", "conf/plugin/SupplementPlugin/MorphemeProcessor/SimpleMAResult09.json");
		pluginInfoMap.put("SimpleMAResult22", "conf/plugin/SupplementPlugin/MorphemeProcessor/SimpleMAResult22.json");
		pluginInfoMap.put("HmmPosTagger", "conf/plugin/MajorPlugin/PosTagger/HmmPosTagger.json");
		pluginInfoMap.put("NounExtractor", "conf/plugin/SupplementPlugin/PosProcessor/NounExtractor.json");
		pluginInfoMap.put("SimplePOSResult09", "conf/plugin/SupplementPlugin/PosProcessor/SimplePOSResult09.json");
		pluginInfoMap.put("SimplePOSResult22", "conf/plugin/SupplementPlugin/PosProcessor/SimplePOSResult22.json");
    }
    
    // plugin 설정 json 파일 문자열 반환
    private static String getPluginAbstarct(String filePath) throws JSONException, IOException {
    	JSONReader json = new JSONReader(filePath);
        String res = null;
        
        res = String.format("* Name: %s\n* Type: %s\n* Version: %s\n* Author: %s\n* Description: %s\n",
        		json.getName(), json.getType(), json.getVersion(), json.getAuthor(), json.getDescription());
        return res;
    }

	public static Map<String, String> getPluginInfoMap() {
		return pluginInfoMap;
	}
}
