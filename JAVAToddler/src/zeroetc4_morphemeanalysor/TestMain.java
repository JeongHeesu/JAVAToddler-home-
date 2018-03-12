package zeroetc4_morphemeanalysor;

import java.io.IOException;

import org.json.JSONException;

import zeroetc4_morphemeanalysor.res.JsonFileLoader;
import zeroetc4_morphemeanalysor.res.MorphemeAnalysingManager;

public class TestMain {

	public static void main(String[] args) {
		
		System.out.println("====================== 확장자 json 파일 내용 ======================");
		// 확장자 json 파일 리딩
		try {
			String rtnValue = 
					JsonFileLoader.getPluginAbstarct("conf/plugin/SupplementPlugin/PlainTextProcessor/InformalSentenceFilter.json");
			System.out.println("확장자 json 파일 로드 : " + rtnValue);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 형태소 분석을위한 타겟 문서 로딩 및 분석기 플러그인 선택
		// 1. 타겟 파일, 2. Parse1 Supplement plugin
//		new MorphemeAnalysingManager().start("zeroetc1_unitTest/readme.txt", 
//				MorphemeAnalysingManager.PARSE1_INFORMAL_SENTENCE_SUPPLEMENT, 
//				null, null);
		new MorphemeAnalysingManager().start("zeroetc1_unitTest/readme.txt", 
				null, 
				MorphemeAnalysingManager.PARSE2_CHARTMORPHANALYZER, 
				MorphemeAnalysingManager.PARSE2_UNKNOWNMORPHPROCESSOR_SUPPLEMENT);
	}

}
