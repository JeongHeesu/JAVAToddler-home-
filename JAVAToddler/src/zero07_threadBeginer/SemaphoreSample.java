package zero07_threadBeginer;

import java.util.concurrent.Semaphore;

public class SemaphoreSample implements Runnable {

	// 상호 배제를 위한 세마포어 변수
	private Semaphore sem;
	private String msg;

	public SemaphoreSample(Semaphore sem, String msg) {
		this.sem = sem;
		this.msg = msg;
	}

	@Override
	public void run() {
		try {
			// acquire() 호출을 통해 Cretical Section에 enter section 가능할때 스레드는 실행되고,
			// enter section이 불가능할경우 대기(블록킹)함.
			sem.acquire(); 

			System.out.println(msg);
			Thread.sleep(10000);
		} catch (Exception e) {}

		// exit section 처리
		sem.release(); 

	}

}
