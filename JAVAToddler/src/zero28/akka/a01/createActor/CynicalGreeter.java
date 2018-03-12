package zero28.akka.a01.createActor;

import akka.actor.UntypedActor;

public class CynicalGreeter extends UntypedActor{
	
	@Override
	public void preStart() throws Exception {
		System.out.println("까칠한 종업원 Hello!! AkkA!!!");
	}
	
	@Override
	public void onReceive(Object message) throws Exception {
		System.out.println("CynicalGreeter : " + message);
	}
}
