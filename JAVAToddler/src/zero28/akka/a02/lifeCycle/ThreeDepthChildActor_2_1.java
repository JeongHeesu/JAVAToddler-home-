package zero28.akka.a02.lifeCycle;

import scala.Option;
import akka.actor.UntypedActor;

// ActorPath - akka://system/user/oneDepthActor_1/twoDepthActor_2/threeDepthActor_2_1
public class ThreeDepthChildActor_2_1 extends UntypedActor {

	@Override
	public void preStart() throws Exception {
		System.out.println("***** ThreeDepthChildActor_2_1 Actor preStart()");
	}

	@Override
	public void onReceive(Object message) throws Exception {
		System.out.println("***** ThreeDepthChildActor_2_1 파라메터 : [" + message + "]");
		if(String.valueOf(message).intern() == "error".intern()){
			int i = 1/0;
		}else{
			unhandled(message);
		}
	}

	@Override
	public void postStop() throws Exception {
		System.out.println("***** ThreeDepthChildActor_2_1 postStop()");
	}

	@Override
	public void postRestart(Throwable reason) throws Exception {
		System.out.println("***** ThreeDepthChildActor_2_1 postRestart()");
	}

	@Override
	public void preRestart(Throwable reason, Option<Object> message)
			throws Exception {
		System.out.println("***** ThreeDepthChildActor_2_1 preRestart()");
	}
}
