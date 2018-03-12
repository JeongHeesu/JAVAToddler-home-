package zero28.akka.a07.tcp.server;

import akka.actor.UntypedActor;
import akka.io.Tcp.Bind;
import akka.io.Tcp.Bound;
import akka.io.Tcp.Connected;

public class ServerManagement extends UntypedActor {

	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof Bound){
			System.out.println("정상적으로 TCP 소켓 서버 환경이 설정되었습니다.");
		} else if(msg instanceof Connected){
			Connected connection = (Connected)msg;
			System.out.println("local address : " + connection.localAddress() + "\n" +
					"remote address : " + connection.remoteAddress());
		}
	}
}
