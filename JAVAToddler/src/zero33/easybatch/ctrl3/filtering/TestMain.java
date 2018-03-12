package zero33.easybatch.ctrl3.filtering;

import java.io.File;
import java.io.FileNotFoundException;
import org.easybatch.core.api.ComputationalRecordProcessor;
import org.easybatch.core.api.RecordProcessor;
import org.easybatch.core.api.Report;
import org.easybatch.core.filter.GrepFilter;
import org.easybatch.core.impl.Engine;
import org.easybatch.core.impl.EngineBuilder;
import org.easybatch.core.record.StringRecord;
import org.easybatch.flatfile.FlatFileRecordReader;

public class TestMain {

	public static void main(String[] args) {
		File members = new File("D:\\workspace(java)\\JAVAToddler\\src\\zero33\\easybatch\\member.csv");
		
		EngineBuilder engineBuilder = new EngineBuilder();
		try {
			engineBuilder.reader(new FlatFileRecordReader(members));

			// ** 필터를통한 필터링과 다수의 프로세서 선언에의한 프로세서 체이닝 **
			// 1. 단위 레코드별 GrepFilter 생성자에 선언된 문자열과 일치하는 단어가 포함된 레코드만을 대상으로 
			//    Processing 처리
			engineBuilder.filter(new GrepFilter("이"));
			// 2. 생성자 대상 단위 레코드 구분자 및 구분자가 활용되어 split된 배열의 특정 인덱스 지정
			//    상기 1.에의해 필터링되지않은 레코드를 input으로 취득
			engineBuilder.processor(new TestMain().new CutProcessor(",", 2));
			// 3. 상기2의 output을 input으로 취득
			engineBuilder.processor(new TestMain().new WordCountProcessor());
			
			Engine engine = engineBuilder.build();
			
			// 필터링 시작, 등록순에의해 CutProcessor와 WordCountProcessor 실행
			Report report = engine.call(); 
			
			System.out.println(report);
			System.out.println("개별 레코드 단위로 '이' 단어가 포함되지 않아 필터링된 레코드 갯수 : " + 
						report.getFilteredRecordsCount());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// members.csv 파일 내 레코드별 접근시 RecordProcessor의 input은 StringRecord(각 레코드) 타입,
	// output은 processRecord 메서드의 반환타입(WordCountProcessor의 input으로 전달될 값).
	// 각 레코드 순차 접근시마다 processRecord 메서드 콜백
	class CutProcessor implements RecordProcessor<StringRecord, String>{

		private String delim;
		private int fieldNumber;
		
		public CutProcessor(String delim, int fieldNumber) {
			this.delim = delim;
			this.fieldNumber = fieldNumber;
		}

		@Override
		public String processRecord(StringRecord record) throws Exception {
			String payload = record.getPayload();
			System.out.println("payload : " + payload);
			
			String rtnValue = payload.split(delim)[fieldNumber];
			System.out.println("rtnValue : " + rtnValue);
			
			return rtnValue + "님은 이씨입니다.";
		}
	}

	class WordCountProcessor implements ComputationalRecordProcessor<String, String, String>{

		private int cnt = 0;
		
		@Override
		public String processRecord(String record) throws Exception {
			// CutProcessor의 processRecord()의 반환값 이쁜이 + "님은 이씨입니다."가 input.
			// [이쁜이님은 이씨입니다.] output.
			System.out.println("record : " + record);
			cnt++;
			return "[" + record + "]";
		}

		@Override
		public String getComputationResult() {
			// batch 실행 결과 반환
			return cnt + "개의 레코드";
		}
		
	}
}











