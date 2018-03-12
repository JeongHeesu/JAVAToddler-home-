package zero07_threadBeginer;

public class ThreadSynchBase1 {
	
	// 다중 cpu 환경(멀티코어)에서의 thread 우선순위 설정은 무의미함.
	// 쓰레드 순차 처리를 위해서 join() 활용을 권장함.
	public ThreadSynchBase1() {
  		LockPrint mr1 = new LockPrint();
  		LockPrint mr2 = new LockPrint();
  		LockPrint mr3 = new LockPrint();
  		
  		Thread t1=new Thread(mr1,"a");
  		System.out.println("default 우선순위 t1 : " + t1.getPriority());
  		
  		Thread t2=new Thread(mr2,"b");
  		System.out.println("default 우선순위 t2 : " + t2.getPriority());
  		t2.setPriority(6);
  		System.out.println("갱신 우선순위 t2 : " + t2.getPriority());
  		
  		Thread t3=new Thread(mr3,"c");
  		System.out.println("default 우선순위 t3 : " + t3.getPriority());
  		t3.setPriority(7);
  		System.out.println("갱신 우선순위 t3 : " + t3.getPriority());
  		
  		t1.start();  
  		t2.start();  
  		t3.start();
	}
}

class  LockPrint implements Runnable{
	
	public void run(){
		show();
	}
	
	public void show(){

		// 임의대로 출력
		// 스레드 스케줄러에의해 show() 함수 내 코드의 실행
//		for(int i=0; i<100; i++){
//			if(((Thread.currentThread()).getName()).equals("a") ){
//				System.out.println("[A"+i+"]");
//			}else if(((Thread.currentThread()).getName()).equals("b") ){
//				System.out.println("[B"+i+"]");
//			}else if(((Thread.currentThread()).getName()).equals("c") ){
//				System.out.println("[C"+i+"]");
//			}
//		}

		// 순차접근으로 순차 출력
		// show()함수 내 코드는 t1, t2, t3 스레드가 선점 실행을위해 경쟁하게되는 임계영역에
		// 위치하게되며, 임계영역 내 코드는 스레드간 경쟁이 아닌 순차적인 접근 설정을위해 synchronized
		// 지정함.
		// 이때 순차적으로 임계영역에 접근한 특정 스레드가 해당 코드 실행 중 정지되거나 비정상 종료되면
		// 임계영역 내 접근하기위해 대기중인 기타 스레드가 무한대기(deadlock)됨.
		// 메서드에 synchronized를 걸지않고 메서드내 처리를위한 데이타에 락을 걸어 세그먼팅을 할때(lock striping)
		// 다중 스레드상황에서 해당 메서드를 스레드들이 취득하기위해 대기해야하는 시간이 짦아짐.
		synchronized(LockPrint.class){
			for(int i=0; i<100; i++){
				if(((Thread.currentThread()).getName()).equals("a") ){
					System.out.println("[A"+i+"]");
				}else if(((Thread.currentThread()).getName()).equals("b") ){
					System.out.println("[B"+i+"]");
				}else if(((Thread.currentThread()).getName()).equals("c") ){
					System.out.println("[C"+i+"]");
					if(i == 99){
						// 스레드 종료
						Thread.currentThread().interrupt();
					}
				}
			}
		}
	}
}
