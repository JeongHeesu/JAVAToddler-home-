package zero09_scheduler.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SecondBatchJob implements Job {

	// 스케줄러에의해 수행되어지는 로직은 execute 함수를 오버라이딩해 구성.
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		JobDataMap jdm = context.getMergedJobDataMap();
		System.out.println("SecondBatchJob 실행 시간 : " + 
						format.format(new Date()) + 
						" / param : " + jdm.get("mem_pass"));
	}

}
