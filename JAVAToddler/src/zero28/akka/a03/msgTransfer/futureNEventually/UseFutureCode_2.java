package zero28.akka.a03.msgTransfer.futureNEventually;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import scala.concurrent.Await;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import zero28.akka.a03.msgTransfer.futureNEventually.UseFutureCode_1.PrintResult;
import akka.actor.UntypedActor;
import akka.dispatch.ExecutionContexts;
import akka.dispatch.OnComplete;
import akka.dispatch.OnSuccess;
import akka.pattern.Patterns;
import akka.util.Timeout;
import static akka.dispatch.Futures.future;
import static akka.dispatch.Futures.sequence;

public class UseFutureCode_2 extends UntypedActor{
	private ExecutorService executor = null;
	private ExecutionContext context = null;
	
	@Override
	public void preStart() throws Exception {
		// Executors(스레드 풀 활용으로 풀내의 스레드를 효율적으로 관리)
		//           newSingleThreadExecutor() : 한개의 Thread 관리 풀 생성.
		//                          해당 Thread 동작 중 익셉션 발생시 Thread를 신규 생성처리.
		//           newFixedThreadPool() : 최대 Thread 풀내에 생성될 Thread 갯수를 지정하고,
		//                          Thread의 생성은 작업 등록과 비례하여 생성됨.
		//           newCachedThreadPool() : 풀내 생성되어진 Thread의 갯수 대비 등록되어지는 작업이
		//                          많은 상황에서 풀내의 유휴 Thread를 종료시키고, 신규 Thread를 생성해
		//                          작업을 수행하며, Thread의 갯수 제한 없음.
		//           newScheduledThreadPool() : 작업 등록에따라 신규 생성된 Thread의 실행은 일정시간 이후
		//                          또는, 주기적으로 처리될수 있는 환경 제공.
		executor = Executors.newFixedThreadPool(5);
		
		// 스래드 풀 내 생성된 스레드의 관리를위한 ExecutorService와 해당 스레드 풀내의 스레드 실행 및 
		// 종료상태를 모니터링할 ExecutionContext 생성. 
		context = ExecutionContexts.fromExecutorService(executor);
	}

	@Override
	public void onReceive(Object msg) throws Exception {
		List<Future<Object>> futureCollection = new ArrayList<Future<Object>>();
		
		// 스레드풀내에 랜덤한 대기시간을 가지는 스레드 4개 생성 및 각각의 스레드의 실행 및 종료 상태를 모니터링하고
		// 상황을 Future로 콜백되도록 ExecutionContext와 연동 선언.
		futureCollection.add(future(new RandomPause(), context));
		futureCollection.add(future(new RandomPause(), context));
		futureCollection.add(future(new RandomPause(), context));
		futureCollection.add(future(new RandomPause(), context));
		
		// 스레드 당 하나의 Future가 할당되고, 다수의 Future를 하나의 Future로 구성해 콜백을 관리하기위한 처리.
		Future<Iterable<Object>> seq = sequence(futureCollection, context);
		
		seq.onSuccess(new OnSuccess<Iterable<Object>>() {
			// 스레드 풀을 구성하는 스레드의 반환값들을 순환접근 객체로 반환함.
			public void onSuccess(Iterable<Object> result) {
				if (result != null) {
					System.out.println("############## 정상 실행 결과 #############");
					for(Object obj : result){
						System.out.println("결과값 : " + obj);
					}
				} else {
					System.out.println("return null!!");
				}
			}
		}, getContext().dispatcher());
	}

	@Override
	public void postStop() throws Exception {
		// 스레드 풀을 구성하는 각각의 스레드를 종료처리하며, 스레드의 수행작업 종료시 스레드도 종료됨.
		executor.shutdown();
	}

	// 랜덤한 스레드 대기시간을 가진 스레드 실행
	class RandomPause implements Callable<Object> {
		// 대기시간
		private Long millisPause;
		
		public RandomPause() {
			millisPause = Math.round(Math.random() * 3000) + 1000;
			System.out.println(this.getClass().getSimpleName() + 
					":" + System.identityHashCode(this) +  
					" 는 랜덤 실행 지연 시간 " + millisPause + 
					" 밀리세컨즈를 설정합니다.");
		}
		
		@Override
		public Long call() throws Exception {
			Thread.sleep(millisPause);
			System.out.println(this.getClass().getSimpleName() + 
					":" + System.identityHashCode(this) +
					" 는 " + millisPause	+ 
					" 밀리세컨즈 후 실행되었습니다.");
			return millisPause;
		}
	}
}
