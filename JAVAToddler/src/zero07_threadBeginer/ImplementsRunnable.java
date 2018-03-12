package zero07_threadBeginer;

import java.util.Random;

public class ImplementsRunnable {
	
	public void init(){
		System.out.println("================ 초기화 ================");
		this.threadsStart();
	}
	
	private void threadsStart(){
		ThreadObj to1 = new ThreadObj(1);
		Thread thread1 = new Thread(to1);
		thread1.start();
		
		ThreadObj to2 = new ThreadObj(2);
		Thread thread2 = new Thread(to2);
		thread2.start();
		
		ThreadObj to3 = new ThreadObj(3);
		Thread thread3 = new Thread(to3);
		thread3.start();
	}
	
	class ThreadObj implements Runnable{
		
		private int code;
		
		public ThreadObj(int code){
			this.code = code;
		}
		
		@Override
		public void run(){
			System.out.println("start another thread!!");
			try{
				Thread.sleep(4000);
				while(true){
					// 0~9 램덤 정수값 반환
					int value = new Random().nextInt(10);
					System.out.println(code + "=" + value);
					
					if(5 == value) break;
				}
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
		
	}
}
