package zero28.akka.a06.scheduler;

import akka.actor.UntypedActor;

public class ReceiveActor extends UntypedActor {

	@Override
	public void onReceive(Object msg) throws Exception {
		System.out.println("수신 메세지 : " + msg);
	}

	@Override
	public void postStop() throws Exception {
		
	}
	
	

}
