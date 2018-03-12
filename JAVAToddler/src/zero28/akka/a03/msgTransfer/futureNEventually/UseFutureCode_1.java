package zero28.akka.a03.msgTransfer.futureNEventually;

import static akka.dispatch.Futures.future;

import java.util.concurrent.Callable;

import scala.concurrent.Future;
import akka.actor.UntypedActor;
import akka.dispatch.OnFailure;
import akka.dispatch.OnSuccess;
import akka.dispatch.OnComplete;
import akka.pattern.Patterns;

public class UseFutureCode_1 extends UntypedActor{

	@Override
	public void onReceive(Object arg0) throws Exception {
		
		// 해당 스레드 상태와 반환값을 비동기식으로 관리하는 Future 선언.
		// ExecutionContext : getContext().dispatcher() 또는
		//                    ActorSytem.dispatcher()를 통해 취득해 활용하며,
		//                    액터의 실행 상황을 모니터링하고 종료 상황을 Future에 콜백.             
		Future<Object> simpleFuture1 = future(new Callable<Object>() {
			@Override
			public String call() throws Exception {
				return "Future의 활용 예제1";
			}
		}, getContext().dispatcher());
		
		// Future로 콜백되는 대상 액터의 정상/비정상 종료 설정.
		simpleFuture1.onComplete(new OnComplete<Object>() {
			public void onComplete(Throwable failure, Object result) {
				if (failure != null) {
					System.out.println("액터의 비정상 종료 : " + failure.getMessage());
				} else {
					System.out.println("액터의 정상 종료 : " + result);
				}
			}
		}, getContext().dispatcher());

		// Future로 콜백되는 대상 액터의 정상적인 로직처리 종료 설정. 
		simpleFuture1.onSuccess(new PrintResult(), getContext().dispatcher());
		
		// Future로 콜백되는 대상 액터의 익셉션 발생에따른 비정상 종료시 발생된 익셉션 타입에 따른 처리.
		simpleFuture1.onFailure(new OnFailure() {
			public void onFailure(Throwable failure) {
				if(failure instanceof IllegalStateException) {
					System.out.println("IllegalStateException 발생");
				} else if(failure instanceof IndexOutOfBoundsException) {
					System.out.println("IndexOutOfBoundsException 발생");
				} else if(failure instanceof RuntimeException) {
					System.out.println("RuntimeException 발생");
				} else{
					System.out.println("Exception 발생");
				}
			}
		}, getContext().dispatcher());
		
		if(simpleFuture1.isCompleted()){
			System.out.println("액터 실행 종료 체크2");
			
		}
	}
	
	final class PrintResult extends OnSuccess<Object> {
		@Override public final void onSuccess(Object t) {
			System.out.println(t);
		}
	}

	@Override
	public void postStop() throws Exception {
		// postStop() 콜백 후 Patterns.gracefulStop()에 Duration 설정에 반하므로 
		// AskTimeoutException 발생
		// Thread.sleep(5000);
		
		Thread.sleep(4000);
		System.out.println("UseFutureCode_1의 postStop() 호출");
	}
}
