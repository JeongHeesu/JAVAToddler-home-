package zero28.akka.a02.lifeCycle;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class ActorLifeCycleMain {

	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("system");
		System.out.println("Root Path : " + system.toString());
		
		// 1) Permanent Tolerance All For One
		// ActorRef oneDepth_1 = system.actorOf(Props.create(OneDepthChildActor_1.class) , 
		//		"oneDepthActor_1");
		
		// 액터 패스 출력
		// System.out.println("##### OneDepth_1 ActorPath : " + oneDepth_1.path().toString());

		// Default Actor Permanent Tolerance All For One를 통한 익셉션 발생 액터의 재시작 처리.  
		// oneDepth_1.tell("not error", null);
		// ActorPath를 기초로 에러가 발생된 해당 액터와 해당 액터의 하위 ActorPath내 모든 액터가 재시작됨.
		// 해당 액터의 onReceive() 콜백함수 호출 및 비동기 방식 파라메터 전달.
		// ActorRef.noSender() == null
		// oneDepth_1.tell("error", ActorRef.noSender());

		// 2) Permanent Tolerance One For One
		ActorRef oneDepth_2 = system.actorOf(Props.create(OneDepthChildActor_2.class) , 
				"oneDepthActor_2");
		System.out.println("***** OneDepth_2 ActorPath : " + oneDepth_2.path().toString());
		
		// ActorPath내 '//System' 하위의 path 불문 모든 액터를 정지처리
		// system.shutdown();
		
	}
}
