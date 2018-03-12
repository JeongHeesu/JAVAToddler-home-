package zeroetc4_morphemeanalysor.res;

import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;
import kr.ac.kaist.swrc.jhannanum.hannanum.Workflow;
import kr.ac.kaist.swrc.jhannanum.plugin.MajorPlugin.MorphAnalyzer.ChartMorphAnalyzer.ChartMorphAnalyzer;
import kr.ac.kaist.swrc.jhannanum.plugin.SupplementPlugin.MorphemeProcessor.SimpleMAResult09.SimpleMAResult09;
import kr.ac.kaist.swrc.jhannanum.plugin.SupplementPlugin.MorphemeProcessor.SimpleMAResult22.SimpleMAResult22;
import kr.ac.kaist.swrc.jhannanum.plugin.SupplementPlugin.MorphemeProcessor.UnknownMorphProcessor.UnknownProcessor;
import kr.ac.kaist.swrc.jhannanum.plugin.SupplementPlugin.PlainTextProcessor.InformalSentenceFilter.InformalSentenceFilter;
import kr.ac.kaist.swrc.jhannanum.plugin.SupplementPlugin.PlainTextProcessor.SentenceSegmentor.SentenceSegmentor;

public class MorphemeAnalysingManager {
	// Parse1 Supplement plugin 
	// 세밀한 분석이 필요하지 않는 비형식적 패턴 분석 및 처리
	//   ex) ########## 일시 ############
	//       화랑이똥꼬화랑이똥꼬화랑이똥꼬화랑이똥꼬화랑이똥꼬
	public static final String PARSE1_INFORMAL_SENTENCE_SUPPLEMENT = "InformalSentenceFilter";
	// 마침표, 물음표, 느낌표 등의 문장 기호를 기준으로 전,후 조건에 따라 문장을 구분하는 조건을 추가함
	public static final String PARSE1_SENTENCE_SEGMENTOR_SUPPLEMENT = "SentenceSegmentor";
	// 형태소분석을위해 내부 저장 공간으로 Lattice 형태의 차트를 사용하며 제공하는 또는 수정가능한 사전의 검색 및 
	// 숫자 인식 분석기
	public static final String PARSE2_CHARTMORPHANALYZER = "ChartMorphAnalyzer";
	// 형태소 분석 단계 결과 미등록어로 처리된 형태소에 대해서 비서술성명사(ncn) 태그를 부착. 
	public static final String PARSE2_UNKNOWNMORPHPROCESSOR_SUPPLEMENT = "UnknownMorphProcessor";
	// 간단한 형태소 분석 결과를 제공하기 위하여 총 9개의 품사 태그만을 사용한 결과 추출
	public static final String PARSE2_SIMPLEMARESULT09_SUPPLEMENT = "SimpleMAResult09";
	// 간단한 형태소 분석 결과를 제공하기 위하여 총 9개의 품사 태그만을 사용한 결과 추출
	public static final String PARSE2_SIMPLEMARESULT22_SUPPLEMENT = "SimpleMAResult22";
	
	private Map<String, String> pluginInfoMap = null;
	private StringBuffer targetString = null;
	
	public void start(String fileClassPath, String parse1SupplementName, String parse2MajorPluginName,
			String parse2Supplement){
		// 한나눔 형태소 분석기의 모든 플러그인 로딩
		pluginInfoMap = PlugInLoading.getPluginInfoMap();
		// 분석 대상 문자열 로딩
		loadTargetText(fileClassPath);
		// Analyzing
		doWork(parse1SupplementName, parse2MajorPluginName, parse2Supplement);
	}
	
	
	// 분석 대상 문자열 로딩
	private void loadTargetText(String fileClassPath){
		targetString = new StringBuffer();
		InputStream inputStream =
				MorphemeAnalysingManager.class.getClassLoader().getResourceAsStream(fileClassPath);
		Scanner scanner = new Scanner(inputStream);
		while(scanner.hasNext()){
			targetString.append(scanner.nextLine() + "\n");
			
		}
	}
	
	// WorkFlow 셋팅 
	private String initWorkFlow(String parse1SupplementName){
		Workflow workFlow = new Workflow();

		if(parse1SupplementName.equals(PARSE1_INFORMAL_SENTENCE_SUPPLEMENT)){
			workFlow.appendPlainTextProcessor(new InformalSentenceFilter(), null);
		} else if (parse1SupplementName.equals(PARSE1_SENTENCE_SEGMENTOR_SUPPLEMENT)) {
			workFlow.appendPlainTextProcessor(new SentenceSegmentor(), null);
		}
		
		try {
			// 멀티 쓰레드의 활용
			workFlow.activateWorkflow(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		workFlow.analyze(targetString.toString());
		
		return workFlow.getResultOfDocument();
	}

	private String initWorkFlow(String parse2MajorPluginName, String parse2Supplement){
		Workflow workFlow = new Workflow();
		
		workFlow.setMorphAnalyzer(new ChartMorphAnalyzer(), 
				pluginInfoMap.get(PARSE2_CHARTMORPHANALYZER));
		
		if (parse2Supplement.equals(PARSE2_UNKNOWNMORPHPROCESSOR_SUPPLEMENT)) {
			workFlow.appendMorphemeProcessor(new UnknownProcessor(), null);
		} else if (parse2Supplement.equals(PARSE2_SIMPLEMARESULT09_SUPPLEMENT)) {
			workFlow.appendMorphemeProcessor(new SimpleMAResult09(), null);
		} else if (parse2Supplement.equals(PARSE2_SIMPLEMARESULT22_SUPPLEMENT)) {
			workFlow.appendMorphemeProcessor(new SimpleMAResult22(), null);
		}
		
		try {
			// 멀티 쓰레드의 활용
			workFlow.activateWorkflow(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		workFlow.analyze(targetString.toString());
		
		return workFlow.getResultOfDocument();
	}

	private void doWork(String parse1SupplementName, String parse2MajorPluginName, String parse2Supplement){
		String analyzingResult = "";
		if(parse1SupplementName != null && 
				parse2MajorPluginName == null && parse2Supplement == null){
			analyzingResult = initWorkFlow(parse1SupplementName);
		}
		if(parse1SupplementName == null && 
				parse2MajorPluginName != null && parse2Supplement != null){
			analyzingResult = initWorkFlow(parse2MajorPluginName, parse2Supplement);
		}
		
		
		System.out.println("====================== result ======================");
		System.out.println(analyzingResult);
	}
}
