package zero28.akka.a01.createActor;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.TypedActor;

public class CreateActorMain {

	public static void main(String[] args) {
		// ActorRefFactory를 상속받아 구현되어 Actor의 생성 및 그룹핑, 개별 접근 및 원격 접근 등의 역활을 수행하는 슈퍼바이져
		// ActorSystem 생성과 명명
		// akka://system
		ActorSystem system = ActorSystem.create("system");
		
		// sender로 명명된 MessageSendActor 액터의 인스턴스화 및 실행 시작.
		// 해당 액터의 preStart() 메서드 호출
		// akka://system/user/kindnessGreeter
		ActorRef kg = system.actorOf(Props.create(KindnessGreeter.class) , "kindnessGreeter");
		// akka://system/user/cynicalGreeter
		ActorRef cg = system.actorOf(Props.create(CynicalGreeter.class), "cynicalGreeter");
		
		// 활용된 ActorSytem 자원 반환처리. 
		system.shutdown();
	}
}
