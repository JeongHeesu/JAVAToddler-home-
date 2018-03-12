package zero28.akka.a07.tcp.server;

import java.net.InetSocketAddress;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.io.Tcp;
import akka.io.Tcp.Bind;
import akka.io.Tcp.Bound;
import akka.io.Tcp.CommandFailed;
import akka.io.Tcp.Connected;
import akka.io.TcpMessage;

public class TCPServer extends UntypedActor {

	private ActorRef serverManager = null;

	public TCPServer(ActorRef manager) {
		this.serverManager = manager;
	}
	
	@Override
	public void preStart() throws Exception {
		// AkkA.IO의 TCP 소켓통신 관리자(TCPExt 액터) 액터 취득
		ActorRef tcpManager = Tcp.get(getContext().system()).manager();
		
		// 서버 액터 바인딩 및 서버 접속 정보 설정.
		// address : localhost(127.0.0.1)
		// port : 5001
		// 클라이언트의 접속 풀 카운트
		tcpManager.tell(TcpMessage.bind(getSelf(), new InetSocketAddress("127.0.0.1", 5001), 10), 
				getSelf());
	}

	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof Bound){
			// 정상적인 서버 액터 바인딩 및 서버 접속 정보 반영 통보
			serverManager.tell(msg, getSelf());
		} else if(msg instanceof CommandFailed){
			TcpMessage.unbind();
			// 서버 정지
			getContext().stop(getSelf());
		} else if(msg instanceof Connected){
			Connected connection = (Connected)msg;
			serverManager.tell(connection, getSelf());
			
			ActorRef serverMsgHandler =
					getContext().actorOf(Props.create(TCPServerMsgHandler.class));
			
			// TCP 소켓통신 관리자로부터의 메세지 핸들러 액터 등록 처리
			//     getSender() : TCP 소켓통신 관리자
			getSender().tell(TcpMessage.register(serverMsgHandler), getSelf());
		}
	}

	public static void main(String[] args){
		ActorSystem serverSystem = ActorSystem.create("serverSystem");
		
		ActorRef serverMgr = serverSystem.actorOf(Props.create(ServerManagement.class));
		
		serverSystem.actorOf(Props.create(TCPServer.class, serverMgr));
	}
}















