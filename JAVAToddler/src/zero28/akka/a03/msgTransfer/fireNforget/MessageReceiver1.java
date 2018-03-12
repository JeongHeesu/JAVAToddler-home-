package zero28.akka.a03.msgTransfer.fireNforget;

import java.util.List;
import zero16_ibatis.bean.MemberBean;
import zero16_ibatis.serviceLayer.IMemberServcie;
import zero16_ibatis.serviceLayer.IMemberServiceImpl;
import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class MessageReceiver1 extends UntypedActor {

	// 메세지 수신 함수
	@Override
	public void onReceive(Object msg) throws Exception {
		
		ActorRef parentActor = this.getContext().parent();
		System.out.println("부모 액터패스 : " + parentActor.path().toString());
		System.out.println("자식 액터패스 : " + this.getSelf().path().toString());
		
		if(msg instanceof MemberListActor){
			IMemberServcie service = IMemberServiceImpl.getInstance();
			
			List<MemberBean>  memberList = service.getMemberList();
			
			// 현재의 액터를 송신자로 설정 후 원 송신측 응답처리 
			getSender().tell(memberList, getSelf());
		} else if(msg instanceof String){
			System.out.println("MessageReceiver1 전달 메세지 : " + msg);
		} else{
			this.unhandled(msg);
		}
	}
}
