package zero07_threadBeginer;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.Semaphore;

public class TestMain {

	// main()도 스레드.
	public static void main(String[] args) {
		
//		ImplementsRunnable ir = new ImplementsRunnable();
//		ir.init();
//		
//		ExtendsThread et;
//		try {
//			Class<ExtendsThread> cls = 
//					(Class<ExtendsThread>) Class.forName("zero7_thread.ExtendsThread");
//			et = cls.newInstance();
//			et.init();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		}

//		ThreadSynchBase1 tsb1 = new ThreadSynchBase1();
		
		ThreadSynchBase2 tsb2 = new ThreadSynchBase2();

//		ThreadStateView tv = new ThreadStateView();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// main 스레드는 종료되어도 구동되는 스레드는 동작됨()
		// main 스레드가 종료되었지만 기타 스레드가 동작 중 이상 증상에의해 무한 실행상태가 되는것을 방지하기위해
		// 생성된 스레드 객체.setDemon(true) 설정으로 해당 스레드를 데몬 드레드로 설정하고
		// main 스레드 종료 시점에 데몬 스레드는 같이 종료됨.
		// 모든 스레드가 종료되어야 어플리케이션이 종료되고, JVM의 실행은 정지됨.
		System.out.println("main 함수의 종료로 main thread 종료됨.");
		
		// 시스템 정상종료를 JVM에게 통보. 0외 숫자는 비정상종료를 JVM에게 통보함.
//		System.exit(0);
		
		
		// 스레드 SendProduction의 메세지의 스레드 ReceiveConsumption으로 전송
//		try{
//			PipedOutputStream pos = new PipedOutputStream();
//			PipedInputStream pis = new PipedInputStream();
//			
//			// 두개의 스트리밍을 파이프라인으로 연결.
//			pos.connect(pis);
//			
//			new SendProduction(pos);
//			new ReceiveConsumption(pis);
//		}catch(Exception e){}
		
        // 1. 크리티컬 섹션내 공유자원 대상 동시에 2개의 스레드가 접근할수있는 세마포어 생성.
		// 2. 세메포어의 대기열 FIFO 지원여부.
//        Semaphore semaphore = new Semaphore(2, true);

        // 스레드 3개 생성
//        Thread th1 = new Thread(new SemaphoreSample(semaphore, "A"));
//        Thread th2 = new Thread(new SemaphoreSample(semaphore, "B"));
//        Thread th3 = new Thread(new SemaphoreSample(semaphore, "C"));

        // 스레드 시작
        // 최초 무작위 2개의 스레드가 크리티컬 섹션내 자원을 공유하며, 2개의 스레드중 특정 스레드 종료시 
        // 대기 스레드가 섹션내 입장
//        th1.start();
//        th2.start();
//        th3.start();
	}
}
