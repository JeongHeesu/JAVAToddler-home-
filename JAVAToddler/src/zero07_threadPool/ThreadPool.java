package zero07_threadPool;

/**
 * 스레드 풀 클래스. 
 * WorkQueue 활용 쓰레드가 수행해야 할 태스크 저장.
 */
public class ThreadPool extends ThreadGroup {
	// 스레드 풀 구성 상수
	public static final int DEAFULT_MAX_THREAD_COUNT = 30;     // 스레드 풀 최대 스레드 수
	public static final int DEAFULT_MIN_THREAD_COUNT = 0;      // 스레드 풀 최소 스레드 수
	public static final int DEFAULT_INITIAL_THREAD_COUNT = 10; // 스레드 풀 초기 스레드 수

	// 허용되는 휴면 스레드 수 
	public static final int DEFAULT_ALLOWED_IDLE_COUNT = 5;

	// 수행할 태스트 저장 큐 
	private WorkQueue pool = new WorkQueue();

	// 최초 최소 스레드 개수
	private int minThreadCount;

	// 최대 스레드의 개수
	private int maxThreadCount;

	// 현재 생성되어 있는 스레드의 개수
	private int createdThreadCount = 0;

	// 현재 태스크를 수행하고 있는 스레드의 개수
	private int workThreadCount = 0;

	// 현재 태스크를 수행하고 있지 않은 스레드의 개수
	// idleThreadCount = createdThreadCount - workThreadCount
	private int idleThreadCount = 0;

	// 스레드 풀 허용 idle 스레드의 개수
	private int allowedIdleCount = 0;

	// 스레드 풀 닫힘 여부
	private boolean closed = false;

	private static int groupId = 0;
	private static int threadId = 0;

	/**
	 * ThreadPool을 생성
	 * 
	 * @param initThreadCount   초기 생성되어야할 스레드 개수
	 * @param maxThreadCount    생성할 수 있는 최대 스레드 개수
	 * @param minThreadCount    최소한 생성되어 있어야 할 스레드의 개수
	 * @param allowedIdleCount  풀에서 허용되는 Idle 스레드의 개수
	 */
	public ThreadPool(int initThreadCount, int maxThreadCount,
			int minThreadCount, int allowedIdleCount) {
		
		super(ThreadPool.class.getName() + Integer.toString(groupId++));

		if(minThreadCount < 0)
			minThreadCount = 0;                 // 최소 스레드 개수 검사
		if (initThreadCount < minThreadCount)
			initThreadCount = minThreadCount;   // 초기 스레드 개수 검사
		if (maxThreadCount < minThreadCount || maxThreadCount < initThreadCount)
			maxThreadCount = Integer.MAX_VALUE; // 최대 스레드 개수 검사

		if (allowedIdleCount < 0)
			allowedIdleCount = DEFAULT_ALLOWED_IDLE_COUNT;

		this.minThreadCount = minThreadCount;
		this.maxThreadCount = maxThreadCount;
		this.createdThreadCount = initThreadCount;
		this.idleThreadCount = initThreadCount;
		this.allowedIdleCount = allowedIdleCount;

		// 메인함수의 메인 스레드에 종속적인 스레드를 만듬....
		for (int i = 0; i < this.createdThreadCount; i++) {
			new PooledThread().start();
		}
	}

	public ThreadPool(int initThreadCount, int maxThreadCount,
			int minThreadCount) {
		this(initThreadCount, maxThreadCount, minThreadCount,
				DEFAULT_ALLOWED_IDLE_COUNT);
	}

	// 큐에 태스크 추가
	public synchronized void execute(Runnable work){
		if(closed){
			System.out.println("큐가 닫혀서 새로운 태스크를 추가할수 없음.");
			return;
		}

		// 현재 상태 파악 후, 필요하다면 쓰레드 개수를 증가시킨다.
		increasePooledThread();
		
		// 태스크 추가 
		pool.enqueue(work);
	}

	// 쓰레드 풀을 종료
	public synchronized void close(){
		if(closed){
			System.out.println("큐가 이미 닫혔음.");
			return;
		}
		closed = true;
		pool.close();
	}

	// 필요하다면 PooledThread의 개수 증가
	private void increasePooledThread() {
		synchronized (pool) {
			// 수행해야 할 작업의 개수가 휴면상태의 스레드 개수보다 많다면,
			// 그 차이만큼 쓰레드를 생성한다.
			if (idleThreadCount == 0 && createdThreadCount < maxThreadCount) {
				new PooledThread().start();
				createdThreadCount++;
				idleThreadCount++;
			}
		}
	}

	private void beginRun() {
		synchronized (pool) {
			workThreadCount++;
			idleThreadCount--;
		}
	}

	/**
	 * 쓰레드를 종료할 지의 여부를 나타낸다.
	 * 
	 * @return 쓰레드가 계속 수행해야 하는 경우 false를 리턴, 쓰레드를 종료하고자 할 경우 true를 리턴.
	 */
	private boolean terminate() {
		synchronized (pool) {
			workThreadCount--;
			idleThreadCount++;

			if (idleThreadCount > allowedIdleCount
					&& createdThreadCount > minThreadCount) {
				// idle 쓰레드의 개수가 10개를 넘기고,
				// 현재 생성되어 있는 쓰레드의 개수가 minThreadCount 보다 큰 경우
				createdThreadCount--;
				idleThreadCount--;

				return true;
			}
			return false;
		}
	}

	// 큐에 저장되어있는 태스크(Runnable 인스턴스들)들을 읽어와 run() 메소드를 수행하는 스레드
	private class PooledThread extends Thread {

		public PooledThread() {
			super(ThreadPool.this, "PooledThread #" + threadId++);
		}

		public void run(){
			try {
				// 반복을 통해 스레드 풀내 스레드들의 인스턴스들을 스레드 풀이 닫히지 않는 조건에서
				// 실행.
				while (!closed){
					
					Runnable work = pool.dequeue();
					
					// 현재 태스크를 수행하고 있는 스레드의 개수 증가
					// 현재 태스크를 수행하고 있지 않은 스레드의 개수 감소
					beginRun();
					
					// 스레드 실행
					work.run();
					
					// 스레드 종료 여부 판단
					if (terminate()){
					//  idle 스레드의 개수가 많을 경우 해당 스레드 종료시킴.
						break;            
					}
				}
			} catch (InterruptedException ex){}
		}
	}

	public void printStatus() {
		synchronized (pool) {
			System.out.println("Total Thread=" + createdThreadCount);
			System.out.println("Idle  Thread=" + idleThreadCount);
			System.out.println("Work  Thread=" + workThreadCount);
		}
	}
}
