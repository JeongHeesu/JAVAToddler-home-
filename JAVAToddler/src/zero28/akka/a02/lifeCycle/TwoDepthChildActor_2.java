package zero28.akka.a02.lifeCycle;

import scala.Option;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.actor.SupervisorStrategy.Directive;
import akka.japi.Function;

// ActorPath - akka://system/user/oneDepthActor_1/twoDepthActor_2
public class TwoDepthChildActor_2 extends UntypedActor {
	// 에러 발생 액터만을 대상으로한 재시작 처리
	// OneForOneStrategy 생성자 파라메터
	// 1. 재시작시도횟수(분당).
	// 2. 총소요시간.
	//    Duration.create("5 seconds") | Duration.create("5 minute") |
	//    Duration.create(1000)
	// 3. 로직(재시작 대상 액터에서 발생된 익셉션 타입에따른 
	// * 재시작시도횟수 -1 and 총소요시간 Duration.Inf() 설정시 재시작시도횟수 및 소요시간 제한없음.
	//              0 and Duration.zero() 즉시 재시작.
	private static SupervisorStrategy strategy = new OneForOneStrategy(-1,
			Duration.Inf(),	new Function<Throwable, Directive>() {
				@Override
				public Directive apply(Throwable ex) throws Exception {
					System.out.println("***** 하위 패스 액터 중 에러발생 : " +ex.getMessage());
					
					if (ex instanceof ArithmeticException) {
						// 액터 다시 시작
						return SupervisorStrategy.restart();
					} else if (ex instanceof NullPointerException) {
						// 액터 일시 중지
						return SupervisorStrategy.resume();
					} else if (ex instanceof IllegalArgumentException) {
						// 액터 정지
						return SupervisorStrategy.stop();
					} else {
						// 슈퍼바이저 액터에 실패 정보 전송
						return SupervisorStrategy.escalate();
					}
				}
			});

	// Default Actor Permanent Tolerance One For One 처리를위한 오버라이딩(필수).
	@Override
	public SupervisorStrategy supervisorStrategy() {
		return strategy;
	}
	
	@Override
	public void preStart() throws Exception {
		System.out.println("***** TwoDepthChildActor_2 Actor preStart()");
		// 해당 액터를 슈퍼바이저로 자식 액터 등록 및 실행
		ActorRef threeDepth_2_1 = this.getContext().actorOf(
				Props.create(ThreeDepthChildActor_2_1.class), "threeDepthActor_2_1");
		System.out.println("***** twoDepth_2_1 ActorPath : "
				+ threeDepth_2_1.path().toString());
		
		ActorRef threeDepth_2_2 = this.getContext().actorOf(
				Props.create(ThreeDepthChildActor_2_2.class), "threeDepthActor_2_2");
		System.out.println("***** twoDepth_2_2 ActorPath : "
				+ threeDepth_2_2.path().toString());
		
		// 해당 슈버바이저에 등록된 액터들을 대상으로 대상 액터 종료시 Terminated 메세지를 
		// onReceive() 콜백함수를 통해 취득할수있음.
		this.getContext().watch(threeDepth_2_1);

		// TwoDepthChildActor_2를 송신자로 ThreeDepthChildActor_2로 메세지 송신.(에러발생)
		threeDepth_2_1.tell("error", this.getSelf());
	}

	@Override
	public void onReceive(Object message) throws Exception {
		System.out.println("***** TwoDepthChildActor_2 파라메터 : [" + message + "]");
		
		// watch()를 통한 특정 액터의 종료상태 처리
		if(message instanceof Terminated){
			Terminated t = (Terminated) message;
			ActorRef actor = t.getActor();
			System.out.println(actor.path().toString() + "가 종료되었습니다.");
		}
		// kill 메세지 수신시 송신 액터를 종료처리
		if("kill".intern() == message.toString().intern()){
			getContext().stop(getSender());
		}
	}

	@Override
	public void postStop() throws Exception {
		System.out.println("***** TwoDepthChildActor_2 postStop()");
	}

	@Override
	public void postRestart(Throwable reason) throws Exception {
		System.out.println("***** TwoDepthChildActor_2 postRestart()");
	}

	@Override
	public void preRestart(Throwable reason, Option<Object> message)
			throws Exception {
		System.out.println("***** TwoDepthChildActor_2 preRestart()");
	}
}
