package zero28.akka.a05.routing;

import java.util.Arrays;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RandomRouter;

public class RandomRoutingActor extends UntypedActor {

	@Override
	public void onReceive(Object message) throws Exception {
		System.out.println(message);
		if(!message.toString().contains("응답메세지")){
			Iterable<String> actors = Arrays.asList(
					new String[] { "/user/routing1/rrm1", "/user/routing1/rrm2", 
							"/user/routing1/rrm3", "/user/routing1/rrm4" });
		
			// RandomRouter : 라우팅시 랜덤하게 액터가 지정됨.
			ActorRef routeExecute = getContext().actorOf(Props.empty().withRouter(new RandomRouter(actors)));
			routeExecute.tell("랜덤 라우팅 메세지", getSelf());
		}else{
			System.out.println("랜덤 라우팅 수신 메세지 : " + message);
		}
		
	}
}
