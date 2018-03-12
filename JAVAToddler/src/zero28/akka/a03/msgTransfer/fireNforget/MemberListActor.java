package zero28.akka.a03.msgTransfer.fireNforget;

import java.util.List;

import zero16_ibatis.bean.MemberBean;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class MemberListActor extends UntypedActor {
	private ActorRef identityReceiver = null;
	@Override
	public void preStart() throws Exception {
		System.out.println("MemberListActor preStart()");
		
		ActorRef receiver1 = getContext().actorOf(Props.create(MessageReceiver1.class), "receiver1");
		ActorRef receiver2 = getContext().actorOf(Props.create(MessageReceiver2.class), "receiver2");
		
		// 현재의 액터(getSelf())를 송신자로 지정 후 해당 액터(actorRef)에 메세지 송신. 해당 액터의 onReceive() 콜백됨.
		receiver1.tell(this, getSelf());
	}

	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof List){
			for(MemberBean member : (List<MemberBean>)msg){
				System.out.println("mem_id : " + member.getMem_id());
				System.out.println("mem_pass : " + member.getMem_pass());
				System.out.println("mem_name : " + member.getMem_name());
			}
		} else if(msg instanceof String){
			for (ActorRef ar : getContext().getChildren()) {
				System.out.println("Actor Path : " + ar.path().toString());
			}
		} else{
			this.unhandled(msg);
		}
	}
}
