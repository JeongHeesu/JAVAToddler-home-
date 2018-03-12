package zero07_threadIntermidate;

public class CakePlate {
	// 케익 갯수
	private int breadCount=0;
	
	public CakePlate(){}
	
	// 케익 10개 만들기
	public synchronized void makeBread(){
		
		// 케익 갯수가 10개 넘으면....
		if(breadCount>=10){
			try{
				System.out.println("케익이 10개 이상 만들어졌습니다..");
				// wait()는 java.lang.Object 객체의 함수 
				// 스레드는 RUNNABLE => WAITING 상태로 천이됨.
				// 케익 만드는 스레드 대기.
				wait();
			}catch(InterruptedException ire){}
		}
		
		// 케익을 만든다.
		System.out.println("케익을 새로 만듭니다.");
		breadCount++;
		System.out.println("케익의 갯수 : " + breadCount + "개");
		
		
		//                            (ready state -> run state)    non_runnable state 
		// NEW(thread 생성) => RUNNABLE(start() ----------> run()) => WAITING(wait())===> (1)
		// (1) notify() or notifyAll() 호출시
		//         (ready state -> run state)
		// RUNNABLE(start() ----------> run())
		this.notifyAll();
		
	}

	public synchronized void eatBread(){
		// 케익갯수가 0이면...
		if(breadCount<1){
			try{
				System.out.println("만들어질때까지 기다리세요..");
				// 케익 먹는 스레드 대기...
				wait();
			}catch(InterruptedException ire){}
		}
		// 케익을 먹는다.
		System.out.println("케익을 먹습니다.");
		breadCount--;
		System.out.println("케익의 갯수 : " + breadCount + "개");
		this.notifyAll();
	}
}
