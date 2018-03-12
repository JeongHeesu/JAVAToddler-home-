package zero28.akka.a01.createActor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class KindnessGreeter extends UntypedActor{
	private ActorRef customer_1;
	private ActorRef customer_2;
	private ActorRef customer_3;
	
	// 액터의 인스턴스화 이후 실행 전 호출
	@Override
	public void preStart() throws Exception {
		System.out.println("친절한 종업원 Hello!! AkkA!!!");
		
		// UnTypedActorContext : 모든 액터 클래스는 다른 액터를 자식으로 생성해 실행상태를 관리할수있음.
		// 액터의 인스턴스화 및 실행.
		// 액터의 시작, 정지, 일시정지, 재시작 등의 실행 관리를 위한 해당 액터대상의 명명처리.
		// (명명처리 되지않은 액터는 액터시스템을통한 자동 생성 부여됨.
		
		// akka://system/user/kindnessGreeter/customer_1
		customer_1 = this.getContext().actorOf(Props.create(KindnessCustomer_1.class), 
				"customer_1");
		// akka://system/user/kindnessGreeter/customer_2
		customer_2 = this.getContext().actorOf(Props.create(KindnessCustomer_2.class), 
				"customer_2");
		// akka://system/user/kindnessGreeter/customer_3
		customer_3 = this.getContext().actorOf(Props.create(KindnessCustomer_3.class), 
				"customer_3");
	}
	
	@Override
	public void onReceive(Object message) throws Exception {
	}
}
