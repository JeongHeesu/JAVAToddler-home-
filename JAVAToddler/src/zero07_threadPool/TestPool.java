package zero07_threadPool;

public class TestPool {
	static int count = 0;
	static long sleepTime = 0;
	static ThreadPool pool = null;

	public static void main(String[] args) {
		pool = new ThreadPool(Integer.parseInt(args[0]), // 초기 생성
							Integer.parseInt(args[1]),   // max
							Integer.parseInt(args[2]),   // min
							Integer.parseInt(args[3]));  // 허용되는 idle 개수
		sleepTime = Long.parseLong(args[4]);

		try {
			
			for (int i = 0; i < 15; i++) {
				// 최초 ThreadPool 클래스에서 생성된 PooledThread 5개 중 한개를
				// WorkQueue에 저장처리.
				pool.execute(new Runnable() {
								public void run() {
									pool.printStatus();
									int local = count++;
			
									try {
										Thread.currentThread().sleep(sleepTime);
										System.out.println("Test " + local);
									} catch (Exception ex) {
										ex.printStackTrace();
									}
								}
				             });
				
				try {
					Thread.currentThread().sleep(10);
				} catch (Exception ex) {
				}
			}
			try {
				Thread.currentThread().sleep(10000);
			} catch (Exception ex) {
			}

			pool.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
