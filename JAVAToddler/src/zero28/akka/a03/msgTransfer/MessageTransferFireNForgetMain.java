package zero28.akka.a03.msgTransfer;


import zero28.akka.a03.msgTransfer.fireNforget.MemberListActor;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;

// Fire And Forget : 슈퍼바이저에서 특정 액터에 메시지를 전달하고 메시지에 대한 응답 대기를 수행하지않으며,
//                   동시 또는 병행처리에 적합 
public class MessageTransferFireNForgetMain {

	public static void main(String[] args) {
		ActorSystem actorSystem = ActorSystem.create("system");
		
		ActorRef memberListActor = actorSystem.actorOf(Props.create(MemberListActor.class) , 
						"memberList");
		
		// akka://system/user/memberList/receiver1
		//               "               receiver2의 액터패스에서 receiver1과 receiver2
		// 모두를 대상으로 메세지 송신처리.
		ActorSelection selection1 = actorSystem.actorSelection("/user/**/*");
		selection1.tell("ActorSytem으로부터의 메세지", ActorRef.noSender());
	}
}
