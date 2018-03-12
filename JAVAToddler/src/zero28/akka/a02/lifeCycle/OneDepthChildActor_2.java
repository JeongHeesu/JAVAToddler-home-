package zero28.akka.a02.lifeCycle;

import scala.Option;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

// ActorPath - akka://system/user/oneDepthActor_2
public class OneDepthChildActor_2 extends UntypedActor {

	@Override
	public void preStart() throws Exception {
		System.out.println("***** OneDepthChildActor_2 preStart()");

		// 해당 액터를 슈퍼바이저로 자식 액터 등록 및 실행
		ActorRef twoDepth_2 = this.getContext().actorOf(
				Props.create(TwoDepthChildActor_2.class), "twoDepthActor_2");
		System.out.println("***** twoDepth_2 ActorPath : "
				+ twoDepth_2.path().toString());
	}

	// 외부로부터 해당 액터로 전달되는 메세지 수신시 콜백됨.
	@Override
	public void onReceive(Object message) throws Exception {
	}

	@Override
	public void postStop() throws Exception {
		System.out.println("***** OneDepthChildActor_2 postStop()");
	}

	@Override
	public void postRestart(Throwable reason) throws Exception {
		System.out.println("***** OneDepthChildActor_2 postRestart()");
	}

	@Override
	public void preRestart(Throwable reason, Option<Object> message)
			throws Exception {
		System.out.println("***** OneDepthChildActor_2 preRestart()");
	}
}
