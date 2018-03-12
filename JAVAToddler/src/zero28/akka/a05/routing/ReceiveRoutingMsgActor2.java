package zero28.akka.a05.routing;

import akka.actor.UntypedActor;

public class ReceiveRoutingMsgActor2 extends UntypedActor {

	@Override
	public void onReceive(Object message) throws Exception {
		System.out.println(String.format("[%s] 액터에서 수신한 라우팅 메세지  \"%s\"",this.getClass().getSimpleName(), message));
		getSender().tell(String.format("[%s:%d]의 응답메세지", this.getClass().getSimpleName(),
				                      getSelf().hashCode()),  
				         getSelf());
	}
}
