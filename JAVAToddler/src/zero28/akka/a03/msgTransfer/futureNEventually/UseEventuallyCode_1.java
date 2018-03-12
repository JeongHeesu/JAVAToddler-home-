package zero28.akka.a03.msgTransfer.futureNEventually;

import akka.actor.UntypedActor;

public class UseEventuallyCode_1 extends UntypedActor{
	
	@Override
	public void onReceive(Object msg) throws Exception {
		System.out.println("UseEventuallyCode_1 파라메터 : " + msg);
		
		getSender().tell("UseEventuallyCode_1 전송값", getSelf());
	}

}
