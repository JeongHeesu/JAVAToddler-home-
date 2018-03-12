package zero33.easybatch.ctrl4.transaction;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.sql.SQLException;

import org.easybatch.core.api.RecordProcessor;
import org.easybatch.core.api.event.batch.BatchProcessEventListener;
import org.easybatch.core.api.event.step.RecordProcessorEventListener;
import org.easybatch.core.filter.HeaderRecordFilter;
import org.easybatch.core.impl.Engine;
import org.easybatch.core.impl.EngineBuilder;
import org.easybatch.flatfile.FlatFileRecordReader;
import org.easybatch.flatfile.dsv.DelimitedRecordMapper;
import zero16_ibatis.build.BuildedSqlMapClient;
import zero33.easybatch.MemberBean;
import com.ibatis.sqlmap.client.SqlMapClient;

public class TestMain_BatchStatusEventListener {

	private static SqlMapClient client = null;
	
	public static void main(String[] args) {
		EngineBuilder engineBuilder = new EngineBuilder();
		File members;
		try {
			members = new File(TestMain_BatchStatusEventListener.class.getResource("/zero33/easybatch/member.csv").toURI());
			
			// 첨부된 easyBatch_pic04.jpg와 easyBatch_pic06.jpg, easyBatch_pic07.jpg 참조
			// FlatFileRecordReader : CSV(Comma Separated Value) 전용 Reader
			engineBuilder.reader(new FlatFileRecordReader(members));
			
			// CSV 파일내 선언된 Header와 DelimitedRecordMapper에 선언된 String 배열 형식의 헤더와의 맵핑 여부 검증 및
			// 개별 레코드의 MemberBean객체 맵핑(setter를 활용한 레코드내 컬럼값의 MemberBean 셋팅) 
			engineBuilder.filter(new HeaderRecordFilter());
			engineBuilder.mapper(new DelimitedRecordMapper<MemberBean>(MemberBean.class, 
					new String[]{"mem_id","mem_pass","mem_name","mem_regno1",
				"mem_regno2","mem_bir","mem_zip","mem_add1",
				"mem_add2","mem_hometel","mem_comtel","mem_hp",
				"mem_mail","mem_job","mem_like","mem_memorial",
				"mem_memorialday","mem_mileage"}));
			
			engineBuilder.processor(new TestMain_BatchStatusEventListener().new MemberInfoLoader());
			
			// 레코드 단위 프로세싱 대상 이벤트 리스너 등록
			engineBuilder.batchProcessEventListener(new TransactionProcessingEventListener());
			
			Engine engine = engineBuilder.build();
			
			engine.call();
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static SqlMapClient getIBatisClient(){
		return BuildedSqlMapClient.getSqlMapClient();
	}

	class MemberInfoLoader implements RecordProcessor<MemberBean, MemberBean>{
		
		public MemberInfoLoader() {
			client = getIBatisClient();
		}

		// 익셉션 발생시 
		@Override
		public MemberBean processRecord(MemberBean memberInfo) throws Exception {
			// members.csv 파일 내 개별 레코드를 구성하는 각 컬럼의 setter를통한 MemberBean 객체 맵핑 후 input
			// zero16_ibatis/mapper/member.xml내 updateMileage
			String mem_id = String.valueOf(memberInfo.getMem_id().charAt(0));
			String targetId = "abcd";
			if(targetId.contains(mem_id)){
				memberInfo.setMem_mileage(String.valueOf(Integer.parseInt(memberInfo.getMem_mileage())+ 100));
				client.update("member.updateMileageBatchProcessing", memberInfo);
			}
			return memberInfo;
		}
	}
	
	static class TransactionProcessingEventListener implements BatchProcessEventListener{

		@Override
		public void beforeBatchStart() {
			try {
				System.out.println("Batch 실행 시작 : 트랜잭션 처리를 시작합니다.");
				client.startTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void afterBatchEnd() {
			try {
				System.out.println("Batch 실행 정지 : 컴밋 처리를 시작합니다.");
				client.commitTransaction();
				client.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
			}
		}
		
		@Override
		public void onException(Throwable throwable) {
			try {
				System.out.println("Batch 실행간 프로세싱 익셉션 발생 : 롤백 처리를 시작합니다.");
				client.endTransaction();
				
				System.exit(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}











