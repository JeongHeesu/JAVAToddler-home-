package zero07_threadBeginer;

import java.util.Random;
import java.util.RandomAccess;

public class ExtendsThread {
	
	public void init(){
		System.out.println("================ 초기화 ================");
		this.threadsStart();
	}
	
	private  void threadsStart(){
		ThreadObj to1 = new ThreadObj(1);
		to1.start();
		
		ThreadObj to2 = new ThreadObj(2);
		to2.start();
		
		ThreadObj to3 = new ThreadObj(3);
		to3.start();
	}
	
	class ThreadObj extends Thread{
		
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
