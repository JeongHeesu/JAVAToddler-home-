package zero28.akka.a03.msgTransfer;

import java.util.concurrent.TimeUnit;

import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import zero28.akka.a03.msgTransfer.futureNEventually.UseFutureCode_1;
import zero28.akka.a03.msgTransfer.futureNEventually.UseFutureCode_2;
import zero28.akka.a03.msgTransfer.futureNEventually.UseEventuallyCode_1;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.AskTimeoutException;
import akka.pattern.Patterns;
import akka.util.Timeout;

// Send And Receive Future : 슈퍼바이저가 특정 액터에 메시지 전달시 응답 대기하지 않으며,
//                          응답을 받기 위한 Future 취득 후 활용함.
public class MessageTransferFutureNEventuallyMain {

	public static void main(String[] args) {
		ActorSystem futureSytem = ActorSystem.create("futureSystem");

// ######### Send-And-Receive-Future start #########		
		// UseFutureCode_1을 활용한 Future 샘플1(단일 Future 처리)
		ActorRef useFutureCode1 = futureSytem.actorOf(Props.create(UseFutureCode_1.class), 
					"useFutureCode1");
		useFutureCode1.tell("", ActorRef.noSender());

 		try {
 			Timeout gracefulTimeout = new Timeout(Duration.create(5, "seconds"));
 			Timeout awaitTimeout = new Timeout(Duration.create(1, "minutes"));
 			
 			// 해당 액터의 postStop() 콜백함수 호출 후 설정된 Duration(지속시간)동안 액터의 실 종료 여부를 체크해 반환함.
 	 		Future<Boolean> successNComplete = Patterns.gracefulStop(useFutureCode1, gracefulTimeout.duration());
 	 		
 	 		// 설정된 최대 Duration(지속시간) 동안 UntypedActor 또는 Future의 complete 메세지 수신까지 현재의 스레드를 대기 시킴. 
 			boolean awaitResult = Await.result(successNComplete, awaitTimeout.duration());

 			System.out.println("해당 액터가 정상적으로 수행되어 액터가 정지된 시점(postStop() 콜백함수 종료)을 기준으로 "
 					+ "5초 동안 정지여부를 체크하는 Future가 반환[" + successNComplete.value() + "]되고, "
 					+ "주어진 시간내에 해당 Future의 처리완료 유무 : " + awaitResult);
		} catch (AskTimeoutException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
 		// UseFutureCode_2을 활용한 Future 샘플2(다중 동시 Future 처리)		
		ActorRef useFutureCode2 = futureSytem.actorOf(Props.create(UseFutureCode_2.class), 
				"useFutureCode2");
		useFutureCode2.tell("", ActorRef.noSender());
// ######### Send-And-Receive-Future end #########
		
// ######### Send-And-Receive-Eventually start #########		
		// UseEventuallyCode_1을 통해 onReceive() 호출을위해 활용되던 tell()를 Patterns.ask()로 대체 후 
		// ActorSystem에서의 반환 메세지 취득처리.
		try {
			ActorRef useEventuallyCode1 = futureSytem.actorOf(Props.create(UseEventuallyCode_1.class), 
					"useEventuallyCode_1");
			// 액터를 선언하고 해당 액터에 메세지를 전달하기위해서 tell() 함수 호출 대신, Patterns.ask()를 활용.
			// 대상 액터의 onReceive() 함수에 메세지를 전달하고, 해당 액터로부터의 반환값을 취득.
			String rtn = (String) Await.result(Patterns.ask(useEventuallyCode1, 
							                       "tell()함수 대신 해당 액터를 실행하고, 해당 액터의 onReceive() 콜백함수 전달값", 
							                       5000), 
							                   Duration.create(5000, TimeUnit.MILLISECONDS));
			System.out.println("useEventuallyCode1 반환값 : " + rtn);
		} catch (Exception e) {
			e.printStackTrace();
		}
// ######### Send-And-Receive-Eventually start #########		
	}
}
