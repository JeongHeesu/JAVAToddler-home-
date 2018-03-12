package zero28.akka.a02.lifeCycle;

import scala.Option;
import akka.actor.UntypedActor;

// ActorPath - akka://system/user/oneDepthActor_1/twoDepthActor_1
public class TwoDepthChildActor_1 extends UntypedActor {

	@Override
	public void preStart() throws Exception {
		System.out.println("##### TwoDepthChildActor_1 Actor preStart()");
	}

	@Override
	public void onReceive(Object arg0) throws Exception {
		
	}

	@Override
	public void postStop() throws Exception {
		System.out.println("##### TwoDepthChildActor_1 postStop()");
	}

	@Override
	public void postRestart(Throwable reason) throws Exception {
		System.out.println("##### TwoDepthChildActor_1 postRestart()");
	}

	@Override
	public void preRestart(Throwable reason, Option<Object> message)
			throws Exception {
		System.out.println("##### TwoDepthChildActor_1 preRestart()");
	}
}
