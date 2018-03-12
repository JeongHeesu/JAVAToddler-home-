package zero07_threadBeginer;

import java.util.Random;

// 다중 cpu 환경(멀티코어)에서의 thread 우선순위 설정은 무의미함.
// 쓰레드 순차 처리를 위해서 join() 활용을 권장함.
public class ThreadStateView {

	// getState()를 이용한 Thread 상태
	// ﻿NEW - 실행되기 전 상태
    // RUNNABLE - 실행 상태
    // ﻿WAITING - wait()를 호출로 non_runnable state의 waiting 상태
	// TIMED_WAITING - sleep()를 호출로  non_runnable state의 sleeping 상태
    // BLOCKED - 입,출력에따른 특정 로직의 실행지연 및 동기화에따른 대기로인한 정지 상태 
	// TERMINATED - 스레드 실행  종료로인한 done state 상태
	public ThreadStateView() {
		
		Speedy thread1 = new Speedy();
		thread1.setHashcode(thread1.hashCode());
		thread1.setName("첫번째 스레드");
		thread1.setPriority(7);
		System.out.println("********* thread1 상태 : " + thread1.getState());
		thread1.start();
		
		ThreadStateReport tsr = new ThreadStateReport();
		tsr.setThread1(thread1);
		Thread tsr_ = new Thread(tsr);
		tsr_.start();

		
		try {
			thread1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("********* thread1 상태 : " + thread1.getState() + " : " + 
				thread1.isAlive());
	}
}

class Speedy extends Thread{
	
	private int hashcode;
	
	@Override
	public void run() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for(int i=0; i<30 ; i++){
			System.out.println(hashcode + " = name : " + this.getName() + " / id : " +
					this.getId() + " / priority : " +
					this.getPriority() + " / state : " +
					this.getState());
		}
	}

	public void setHashcode(int hashcode) {
		this.hashcode = hashcode;
	}
}

class ThreadStateReport implements Runnable{
	private Speedy thread1;
	private int timeWaitingCnt = 0;

	@Override
	public void run() {
		for(int i=0; i<500 ; i++){
			System.out.print("###### thread1 상태 = ");
			if(Thread.State.NEW == thread1.getState()){
				System.out.println("New \n");
			}else if(Thread.State.RUNNABLE == thread1.getState()){
				System.out.println("RUNNABLE \n");
				if(timeWaitingCnt == 10){
					try {
						thread1.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}else if(Thread.State.WAITING == thread1.getState()){
				System.out.println("WAITING \n");
			}else if(Thread.State.TIMED_WAITING == thread1.getState()){
				System.out.println("TIMED_WAITING \n");
			}else if(Thread.State.BLOCKED == thread1.getState()){
				System.out.println("BLOCKED \n");
				if(timeWaitingCnt == 300){
					try {
						thread1.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			timeWaitingCnt++;
		}
	}

	public void setThread1(Speedy thread1) {
		this.thread1 = thread1;
	}
}
