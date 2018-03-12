package zero24_customEvent.publisher;

import zero24_customEvent.event.EventObject;
import zero24_customEvent.handler.CustomEventHandler;

public class CustomEventPublisher implements Runnable {

	// 전송 대상 객체 랩퍼 클래스
	private EventObject sendObject;
	
	private Thread thread = new Thread(this);
	
	public void doWork(EventObject sendObject){
		this.sendObject = sendObject;
		thread.start();
	}
	
	@Override
	public void run() {
		// 핸들러를 통한 이벤트 전파 
		CustomEventHandler.raiseEvent(this, this.sendObject);
	}

}
