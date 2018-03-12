package zero28.akka.a03.msgTransfer.fireNforget;

import akka.actor.UntypedActor;

public class MessageReceiver2 extends UntypedActor {

	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof String){
			System.out.println("MessageReceiver2 전달 메세지 : " + msg);
		} else{
			this.unhandled(msg);
		}
	}
}
