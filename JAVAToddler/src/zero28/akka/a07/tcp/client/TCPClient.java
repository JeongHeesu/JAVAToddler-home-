package zero28.akka.a07.tcp.client;

import java.net.InetSocketAddress;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.io.Tcp;
import akka.io.Tcp.CommandFailed;
import akka.io.Tcp.Connected;
import akka.io.Tcp.ConnectionClosed;
import akka.io.Tcp.Received;
import akka.io.TcpMessage;
import akka.japi.Procedure;
import akka.util.ByteString;

// TCP 소켓통신 클라이언트 액터
public class TCPClient extends UntypedActor {
	// TCP소켓에 IP와Port 바인딩
	private InetSocketAddress remote = null;
	// 서버로부터 수신된 메세지 처리 액터
	private ActorRef listener = null;
	
	public TCPClient(InetSocketAddress remote, ActorRef listener){
		this.remote = remote;
		this.listener = listener;
		
		// AkkA.IO의 TCP 소켓통신 관리자(TCPExt 액터) 액터 취득
		ActorRef tcpManager = Tcp.get(getContext().system()).manager();
		// TCP 소켓 실행 관리자에 접속 정보(InetSocketAddress와 Port) 메세지 송신
		// TCP 소켓통신 관리자에 컨넥션 요청 및 onReceive()를 통한 접속 성패 여부 메세지 수신 대기.
		tcpManager.tell(TcpMessage.connect(remote), getSelf());
	}
	
	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof CommandFailed){
			listener.tell("접속에 실패하였습니다.", getSelf());
			getContext().stop(getSelf());
		}else if(msg instanceof Connected){
			listener.tell("접속에 성공하였습니다.", getSelf());

			// TCP 소켓통신 관리자로부터의 메세지 수신 액터 등록 처리
			// TCP 소켓통신 관리자에 클라이언트 등록 메세지 송신
			// 　　　getSender() : TCP 소켓통신 관리자
			// 　　　TcpMessage : TCP기반 통신 메세지 수신자 등록
			getSender().tell(TcpMessage.register(getSelf()), getSelf());
			
			// 해당액터에 전달되는 Receive Event의 핸들러를 onReceive()에서 connected()가 반환하는
			// Procedure로 변경함.
			getContext().become(connected(getSender()));
		}else if(msg instanceof String){
			System.out.println("TCPClientMsgListener로부터 수신 메세지 : " + msg);
		}
	}
	
	private Procedure<Object> connected(final ActorRef tcpManager){
		return new Procedure<Object>() {
			@Override
			public void apply(Object msg) throws Exception {
				// 네트워크를 통해 바이트코드로 변경 전달되는 문자열 객체
				if(msg instanceof ByteString){
					System.out.println("TCP 소켓 관리자에 전달되는 메세지 : " + ((ByteString)msg).utf8String());
					// TCPManage의 메세지 큐에 메세지 송신
					// 메세지 큐 총 2GB의 저장 가능
					tcpManager.tell(TcpMessage.write((ByteString)msg), getSelf());
				} else if(msg instanceof CommandFailed){
					System.out.println("TCPMessage 메세지 큐 풀.....");
					tcpManager.tell(TcpMessage.write(null), getSelf());
				} else if(msg instanceof Received){
					System.out.println("TCPServer 수신 메세지 : " + ((Received)msg).data());
					// 클라이언트 측 메세지 처리 액터에 메세지 전달
					listener.tell(((Received)msg).data(), getSelf());
				} else if(msg.equals("close")){
					// 서버로부터 통신 종료 전 메세지 수신.
					// 메세지 큐 종료
					tcpManager.tell(TcpMessage.close(), getSelf());
				} else if(msg instanceof ConnectionClosed){
					// TCP 소켓통신 종료
					getContext().stop(getSelf());
				}
			}
		};
	}

	public static void main(String[] args){
		ActorSystem clientSystem = ActorSystem.create("clientSystem");
		ActorRef clientListener =
				clientSystem.actorOf(Props.create(TCPClientMsgListener.class), "clientMsgListener");
		
		clientSystem.actorOf(Props.create(TCPClient.class, new InetSocketAddress("127.0.0.1", 5001), 
				clientListener));
	}
}

















