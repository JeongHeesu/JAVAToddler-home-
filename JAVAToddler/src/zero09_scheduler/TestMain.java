package zero09_scheduler;

import java.text.ParseException;
import java.util.Collection;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import zero07_threadBeginer.MemberBean;
import zero09_scheduler.job.FirstBatchJob;
import zero09_scheduler.job.SecondBatchJob;
import zero09_scheduler.job.ThirdBatchJob;

public class TestMain {

	public static void main(String[] args) {
		// newJob : 신규 잡 등록 및 비지니스 로직 구현 클래스 설정 
		// withIdentity : 등록될 잡 이름설정
		JobDetail job1 = JobBuilder.newJob(FirstBatchJob.class)
				.withIdentity("first").usingJobData("mem_id", "a001").build();
		
		JobDetail job2 = JobBuilder.newJob(SecondBatchJob.class)
				.withIdentity("second").usingJobData("mem_pass", "asfdasdf").build();
		
		MemberBean member = new MemberBean();
		member.setMem_id("b001");
		member.setMem_pass("1004");
		member.setMem_name("김은대");
		
		JobDataMap jdm = new JobDataMap();
		jdm.put("memberInfo", member);
		JobDetail job3 = JobBuilder.newJob(ThirdBatchJob.class)
				.withIdentity("third").usingJobData(jdm).build();
		
		// Trigger를 이용한 스케줄링 설정
		// newTrigger : 신규 스케줄링 등록
		// withIdentity : 트리거 등록명
		// startNow : 1회 작동 트리거 등록
		Trigger trigger1 = TriggerBuilder.newTrigger()
				.withIdentity("firstTrigger").startNow().build();

		// withIntervalInSeconds : 초단위 트리거 동작설정(10초마다 트리거 동작)
		// repeatForever : 스케줄러 작동 정지시까지 지속
		Trigger trigger2 = TriggerBuilder.newTrigger().withSchedule(
							SimpleScheduleBuilder.simpleSchedule()
							    .withIntervalInSeconds(10).repeatForever())
				  .withIdentity("secondTrigger").build();

		// 1분마다 트리거 동작
		Trigger trigger3 = TriggerBuilder.newTrigger().withSchedule(
				SimpleScheduleBuilder.simpleSchedule()
				.withIntervalInMinutes(1) .repeatForever())
				.withIdentity("thirdTrigger").build();

		Trigger trigger4 = null;
		try {
			// 초  분   시  일  월  년
			// 0  20 9  *  *  ?   정의되지않은 년에 매월 매일 9시 20분 0초에 트리거 동작 설정
			trigger4 = TriggerBuilder.newTrigger()
					.withSchedule(CronScheduleBuilder.cronSchedule("0 20 9 * * ?").
							withMisfireHandlingInstructionFireAndProceed()).build();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler scheduler = null;
		try {
			scheduler =  sf.getScheduler();
			scheduler.start();
			
			scheduler.scheduleJob(job1, trigger1);
			scheduler.scheduleJob(job2, trigger2);
			scheduler.scheduleJob(job3, trigger3);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}
