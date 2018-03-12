package zero33.easybatch.ctrl2.headermapping;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import org.easybatch.core.api.ComputationalRecordProcessor;
import org.easybatch.core.api.Report;
import org.easybatch.core.filter.HeaderRecordFilter;
import org.easybatch.core.impl.Engine;
import org.easybatch.core.impl.EngineBuilder;
import org.easybatch.flatfile.FlatFileRecordReader;
import org.easybatch.flatfile.dsv.DelimitedRecordMapper;
import org.easybatch.validation.BeanValidationRecordValidator;

import zero33.easybatch.MemberBean;

// easybatch-flatfile-3.0.0.jar import
// easybatch-validation-3.0.0.jar import
// hibernate-validator-5.1.3.Final.jar import
// validation-api-1.1.0.Final.jar import
// javax.el-api-2.2.5.jar import
// jboss-logging-3.1.0.CR1.jar import
// classmate-0.7.0.jar import
public class TestMain_CSV {

	public static void main(String[] args) {

        File members;
        EngineBuilder engineBuilder = new EngineBuilder();
		try {
			// Reader를 통해 반복적으로 Reading될 파일 설정
//			members = new File("D:\\workspace(java)\\JAVAToddler\\src\\zero33\\easybatch\\member.csv");
			members = new File(TestMain_CSV.class.getResource("/zero33/easybatch/member.csv").toURI());
			
			// 첨부된 easyBatch_pic04.jpg와 easyBatch_pic07.jpg 참조
			// FlatFileRecordReader : CSV(Comma Separated Value) 전용 Reader
			engineBuilder.reader(new FlatFileRecordReader(members));
			
			// CSV 파일내 선언된 Header와 DelimitedRecordMapper에 선언된 String 배열 형식의 헤더와의 맵핑 여부 검증 및
			// 개별 레코드의 MemberBean객체 맵핑(setter를 활용한 레코드내 컬럼값의 MemberBean 셋팅)
			// (CSV내 선언된 Header의 갯수와 DelimitedRecordMapper에 선언된 헤더명의 갯수가 일치되어야 함.)
			engineBuilder.filter(new HeaderRecordFilter());
			engineBuilder.mapper(new DelimitedRecordMapper<MemberBean>(MemberBean.class, 
					new String[]{"mem_id","mem_pass","mem_name","mem_regno1",
								"mem_regno2","mem_bir","mem_zip","mem_add1",
								"mem_add2","mem_hometel","mem_comtel","mem_hp",
								"mem_mail","mem_job","mem_like","mem_memorial",
								"mem_memorialday","mem_mileage"}));
			
			// MemberBean에 선언된 속성값을 대상으로 해당 속성에 setting될수있는 값 대상의 validation 설정. 
			engineBuilder.validator(new BeanValidationRecordValidator<MemberBean>());
			
			// 배치 프로세서 설정
			engineBuilder.processor(new TestMain_CSV().new SimpleCSVBaseProcessor());
			
			Engine engine = engineBuilder.build();
			
			Report report = engine.call();

			System.out.println(report);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	// ComputationalRecordProcessor<MemberBean, MemberBean, Integer> : RecordProcessor를 상속하여 
	//                           input type, output type, result type  대상 전체 데이타 내 단일 행 레코드 단위의 접근하게되며,
	//                                                                 단일 행 레코드 단위의 계수 또는 최종 결과 반환.
	class SimpleCSVBaseProcessor implements ComputationalRecordProcessor<MemberBean, MemberBean, String>{
		private int count;
		@Override
		public MemberBean processRecord(MemberBean memberInfo) throws Exception {
			System.out.println(memberInfo.getMem_name() + "\n");
			count++;
			return memberInfo;
		}
		
		@Override
		public String getComputationResult() {
			if(count%2 == 0)
				return "성공";
			else
				return "실패";
		}
	}
}
