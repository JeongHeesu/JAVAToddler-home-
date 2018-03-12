package zero28.akka.a06.scheduler;

import java.util.concurrent.TimeUnit;

import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Cancellable;
import akka.actor.Props;

public class SchedulerMain {
	
	private static ActorRef receiveActor = null;
	
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("scheduleSystem");
		
		receiveActor = system.actorOf(Props.create(ReceiveActor.class), "receiveActor");
		
		// 해당 설정 시간 이후 한번만 실행되는 스케줄 설정.
		system.scheduler().scheduleOnce(Duration.create(50, TimeUnit.MILLISECONDS), 
				receiveActor, "send message1", system.dispatcher(), null);

		// 해당 설정 시간 이후 한번만 실행되는 스케줄 설정.
		system.scheduler().scheduleOnce(Duration.create(50, TimeUnit.MILLISECONDS), 
				new Runnable() {
					@Override
					public void run() {
						receiveActor.tell("send message2", ActorRef.noSender());
					}
				}, system.dispatcher());
		
		// 1. Duration.Zero() : 즉시 적용
		// 2. tick 간격
		// 3. 메세지 수신 액터
		// 4. 전달 메세지
		// 5. 이벤트 트리거
		// 6. 메세지 송신 액터(메세지 수신 액터로부터의 수신 메세지를 통해 스케줄 취소 처리.)
		Cancellable cancellable = system.scheduler().schedule(Duration.Zero(), 
				Duration.create(50, TimeUnit.MILLISECONDS), 
				receiveActor,
				"send message3",
				system.dispatcher(), null);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		cancellable.cancel();
	}

}












