package zero28.akka.a04.swapping;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class SwappingMain {

	public static void main(String[] args) {
		ActorSystem actorSystem = ActorSystem.create("system");
		
		ActorRef swappingActor = actorSystem.actorOf(Props.create(HotSwappingActor.class) , 
						"swappingActor");
		
		try {
			swappingActor.tell("bar", ActorRef.noSender()); // onReceive() 콜백 처리됨.
			                                                // become("angry")를통해 Procedure angry로 Receive Handler 변경.
			swappingActor.tell("bar", ActorRef.noSender()); // Procedure angry의 apply() 콜백 및 메세지 전달.
			                                                // 'I am already angry'
			swappingActor.tell("foo", ActorRef.noSender()); // Procedure angry의 apply() 콜백 및 메세지 전달 및
			                                                // become("happy")로 Procedure happy로 Receive Handler 변경.
			swappingActor.tell("bar", ActorRef.noSender()); // 'I am already happy :-)'
			swappingActor.tell("foo", ActorRef.noSender()); // Procedure happy의 apply() 콜백 및 메세지 전달 및
                                                            // become("angry")로 Procedure angry로 Receive Handler 변경.
			swappingActor.tell("bar", ActorRef.noSender()); // Procedure angry의 apply() 콜백 및 메세지 전달.
                                                            // 'I am already angry'
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
