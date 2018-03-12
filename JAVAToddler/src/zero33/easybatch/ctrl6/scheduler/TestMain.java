package zero33.easybatch.ctrl6.scheduler;

import java.util.Date;

import org.easybatch.core.api.RecordProcessor;
import org.easybatch.core.api.Report;
import org.easybatch.core.impl.Engine;
import org.easybatch.core.impl.EngineBuilder;
import org.easybatch.core.reader.StringRecordReader;
import org.easybatch.core.record.StringRecord;
import org.easybatch.integration.quartz.BatchScheduler;
import org.easybatch.integration.quartz.BatchSchedulerException;

// easybatch-quartz-3.0.0.jar import
// slf4j-nop-1.6.6.jar import
public class TestMain {

	public static void main(String[] args) {
		// CSV 형식의 데이타
		String dataSource = "a001,asdfasdf,김은대,760115,1406420,76/01/15,135-972\n" +
				"b001,1004,이쁜이,741004,2900000,74/01/07,700-030\n";
		
		// 첨부된 easyBatch_pic03.jpg와 easyBatch_pic07.jpg 참조 
		// 1. Batch Engine 생성.
		// 2. Batch Engine Reader 및 타겟 설정
		// 3. Batch Processor 설정
		Engine engine = new EngineBuilder()
		                   .reader(new StringRecordReader(dataSource))
		                   .processor(new SimpleStringBaseProcessor())
		                   .build();
		try {
			// Quartz를 활용한 BatchScheduler 생성
			BatchScheduler scheduler = new BatchScheduler(engine);
			
			// 초    분       시    일   월  년
			// 0   0/1   *   *  *  *   1분마다 동작하는 스케줄 설정
			// 0   0/30  *   *  *  *   20분마다 동작하는 스케줄 설정
//			scheduler.scheduleAtWithInterval(new Date(), 1000);
			// 30초마다 스케줄 실행
			scheduler.scheduleCron("0/30 * * * * ?");
			scheduler.start();
			
		} catch (BatchSchedulerException e) {
			e.printStackTrace();
		}
		
	}
	
	// RecordProcessor<StringRecord, StringRecord> : 대상 전체 데이타 내 단일 행 레코드 단위의 접근
	//                  Input type, Output type      행 레코드별 processRecord 메서드 콜백
	static class SimpleStringBaseProcessor implements RecordProcessor<StringRecord, StringRecord>{

		// 5. 상기 4.의 engine.call()에 의해 콜백
		@Override
		public StringRecord processRecord(StringRecord stringRecord) throws Exception {
			System.out.println(new Date() + " : " + stringRecord.getPayload());
			return stringRecord;
		}
		
	}

}
