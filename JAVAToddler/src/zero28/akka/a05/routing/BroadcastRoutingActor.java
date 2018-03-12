package zero28.akka.a05.routing;

import java.util.Arrays;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.BroadcastRouter;
import akka.routing.RoundRobinRouter;

public class BroadcastRoutingActor extends UntypedActor {

	@Override
	public void onReceive(Object message) throws Exception {
		System.out.println(message);
		if(!message.toString().contains("응답메세지")){
			ActorRef rrm1 = getContext().actorOf(Props.create(ReceiveRoutingMsgActor1.class), "rrm1");
			ActorRef rrm2 = getContext().actorOf(Props.create(ReceiveRoutingMsgActor2.class), "rrm2");
			ActorRef rrm3 = getContext().actorOf(Props.create(ReceiveRoutingMsgActor3.class), "rrm3");
			ActorRef rrm4 = getContext().actorOf(Props.create(ReceiveRoutingMsgActor4.class), "rrm4");
			
			Iterable<ActorRef> actors = 
					Arrays.asList(new ActorRef[]{ rrm1, rrm2, rrm3, rrm4 });
			
			// BroadcastRouter : 모든 액터들을 대상 라우팅
			ActorRef routeExecute = getContext().actorOf(Props.empty().withRouter(BroadcastRouter.create(actors)));
			routeExecute.tell("브로드캐스트라우팅 메세지", getSelf());
			
			// RoundRobinRouter : 라우팅시마다 순차적으로 액터가 지정됨.
//			ActorRef routeExecute = getContext().actorOf(Props.empty().withRouter(RoundRobinRouter.create(actors)));
//			for(int i=0; i<10; i++){
//				routeExecute.tell("라우팅 메세지", getSelf());
//			}
		}else{
			System.out.println("브로드캐스트라우팅 수신 메세지 : " + message);
		}
		
	}

	@Override
	public void postStop() throws Exception {
		System.out.println("브로드캐스트 라우팅 종료");
	}
	
}
