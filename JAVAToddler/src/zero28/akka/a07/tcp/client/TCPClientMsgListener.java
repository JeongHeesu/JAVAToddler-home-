package zero28.akka.a07.tcp.client;

import akka.actor.UntypedActor;

public class TCPClientMsgListener extends UntypedActor {

	@Override
	public void onReceive(Object msg) throws Exception {
		System.out.println("TCPClient로부터 수신 메세지 : " + msg);
		if(msg.toString().contains("실패")){
			getContext().stop(getSelf());
		}
	}
}
