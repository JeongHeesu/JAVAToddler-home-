package zero28.akka.a07.tcp.server;

import akka.actor.UntypedActor;
import akka.io.Tcp.ConnectionClosed;
import akka.io.Tcp.Received;
import akka.io.TcpMessage;
import akka.util.ByteString;

public class TCPServerMsgHandler extends UntypedActor {

	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof Received){
			ByteString data = ((Received)msg).data();
			System.out.println("TCPClient로부터 수신 메세지 : " + data);
			
			ByteString sendMsg = ByteString.fromString("[server : " + data + " ]");
			
			getSender().tell(TcpMessage.write(sendMsg), getSelf());
		}else if(msg instanceof ConnectionClosed){
			getContext().stop(getSelf());
		}
	}

}
