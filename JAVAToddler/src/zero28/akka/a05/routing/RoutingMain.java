package zero28.akka.a05.routing;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class RoutingMain {

	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("system");
		
		
		system.actorOf(Props.create(BroadcastRoutingActor.class), 
				"routing1").tell("브로드캐스트 라우팅 시작", ActorRef.noSender());
		
		system.actorOf(Props.create(RandomRoutingActor.class)).tell("랜덤 라우팅 시작", null);
	}

}
