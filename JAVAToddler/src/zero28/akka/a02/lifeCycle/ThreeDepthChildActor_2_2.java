package zero28.akka.a02.lifeCycle;

import scala.Option;
import akka.actor.UntypedActor;

// ActorPath - akka://system/user/oneDepthActor_1/twoDepthActor_2/threeDepthActor_2_2
public class ThreeDepthChildActor_2_2 extends UntypedActor {

	@Override
	public void preStart() throws Exception {
		System.out.println("***** ThreeDepthChildActor_2_2 Actor preStart()");
	}

	@Override
	public void onReceive(Object message) throws Exception {
		System.out.println("***** ThreeDepthChildActor_2_2 파라메터 : [" + message + "]");
		getSender().tell("kill", getSelf());
	}

	@Override
	public void postStop() throws Exception {
		System.out.println("***** ThreeDepthChildActor_2_2 postStop()");
	}

	@Override
	public void postRestart(Throwable reason) throws Exception {
		System.out.println("***** ThreeDepthChildActor_2_2 postRestart()");
	}

	@Override
	public void preRestart(Throwable reason, Option<Object> message)
			throws Exception {
		System.out.println("***** ThreeDepthChildActor_2_2 preRestart()");
	}
}
