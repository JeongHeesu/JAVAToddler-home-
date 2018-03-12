package zero28.akka.a02.lifeCycle;

import scala.Option;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

// ActorPath - akka://system/user/oneDepthActor_1
public class OneDepthChildActor_1 extends UntypedActor{
	
	@Override
	public void preStart() throws Exception {
		System.out.println("##### OneDepthChildActor_1 preStart()");
		
		// 해당 액터를 슈퍼바이저로 자식 액터 등록 및 실행
		ActorRef twoDepth_1 = this.getContext().actorOf(Props.create(TwoDepthChildActor_1.class), 
				"twoDepthActor_1");
		System.out.println("##### twoDepth_1 ActorPath : " + twoDepth_1.path().toString());
	}
	
	// 외부로부터 해당 액터로 전달되는 메세지 수신시 콜백됨. 
	@Override
	public void onReceive(Object message) throws Exception {
		System.out.println("##### OneDepthChildActor_1 파라메터 : [" + message + "]");
		
		// 메세지 수신 처리중 익셉션 발생시 슈퍼바이저 액터는 디폴트 permanent(예외 발생시 해당 액터의 재시작처리)설정에
		// 의해 preRestart() -> postRestart()를 순차적으로 콜백되며, 익셉션 발생 액터를 재시작 함.
		if(String.valueOf(message).intern() == "error".intern()){
			int i = 1/0;
		}else{
			// 해당 수신건내에서 처리되지 않아도 되는 수신 메세지임을 ActorSystem에게 통보 후 종료 처리.
			unhandled(message);
		}
	}

	@Override
	public void postStop() throws Exception {
		System.out.println("##### OneDepthChildActor_1 postStop()");
	}

	@Override
	public void postRestart(Throwable reason) throws Exception {
		System.out.println("##### OneDepthChildActor_1 postRestart()");
	}

	@Override
	public void preRestart(Throwable reason, Option<Object> message)
			throws Exception {
		System.out.println("##### OneDepthChildActor_1 preRestart()");
	}	
	
	
}
