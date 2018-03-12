package zero28.akka.a04.swapping;

import akka.actor.UntypedActor;
import akka.japi.Procedure;

public class HotSwappingActor extends UntypedActor {

	// tell() 호출을 통해 해당 액터에게 Receive Event가 전달되며 onReceive()가 Receive Event Handler로서 콜백됨.
	// ActorContext.become(Procedure or Function)을 통해 Receive Event Handler를 변경함.
	@Override
	public void onReceive(Object message) {
		System.out.println("HotSwappingActor onReceive() 호출 : 메세지 - " + message);
		if (message.equals("bar")) {
			// Receive Event Handler 변경
			getContext().become(angry);
		} else if (message.equals("foo")) {
			// Receive Event Handler 변경
			getContext().become(happy);
		} else {
			unhandled(message);
		}
	}
	
	// Receive Handler 정의
	Procedure<Object> angry = new Procedure<Object>() {
		@Override
		public void apply(Object message) {
			System.out.println("angry message : " + message);
			if (message.equals("bar")) {
				System.out.println("I am already angry");
			} else if (message.equals("foo")) {
				// Receive Event Handler 변경
				getContext().become(happy);
			}
		}
	};

	// Receive Handler 정의
	Procedure<Object> happy = new Procedure<Object>() {
		@Override
		public void apply(Object message) {
			System.out.println("happy message : " + message);
			if (message.equals("bar")) {
				System.out.println("I am already happy :-)");
			} else if (message.equals("foo")) {
				// Receive Event Handler 변경
				getContext().become(angry);
			}
		}
	};
}
