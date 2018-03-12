package zero07_threadPool;

import java.util.LinkedList;


// 스레드가 수행할 작업을 저장하는 큐.
public class WorkQueue{

	// 쓰레드가 수행할 태스크를 저장
	private LinkedList<Runnable> workList = new LinkedList<Runnable>();

	// WorkQueue의 사용이 끝났는지의 여부.
	private boolean closed = false;

	// 큐에 새로운 태스크를 추가
	public synchronized void enqueue(Runnable work){
		if (closed){
			System.out.println("큐가 닫혀서 새로운 태스크를 추가할수 없음.");
			return;
		}
		// 큐에 새로운 태스크 추가
		workList.addLast(work);
		
		// 스레드의 상태 : non-runnable state 중 waiting state 상태로 존재하는 불특정 스레드 하나를
		//            동작시키며, 동작하는 스레드의 소유주는 메인 스레드가 됨.
		notify();
	}

	// 큐에 저장된 태스크 리딩.
	public synchronized Runnable dequeue() throws InterruptedException{
		while (workList.size() <= 0) {
			// 현재의 스레드를 기타 스레드에의해 notify() 호출전까지 대기시킴.
			wait();
			if (closed) {
				System.out.println("큐가 닫혀서 태스크를 반환할수 없음.");
				throw new InterruptedException();
			}
		}
		return (Runnable) workList.removeFirst();
	}

	public synchronized int size() {
		return workList.size();
	}

	public synchronized boolean isEmpty() {
		return workList.size() == 0;
	}

	public synchronized void close() {
		closed = true;
		
		// 스레드의 상태 : non-runnable state 중 waiting state 상태로 존재하는 모든 스레드를
		//            동작시키며, 동작하는 스레드의 소유주는 메인 스레드가 됨.
		notifyAll();
	}
}
