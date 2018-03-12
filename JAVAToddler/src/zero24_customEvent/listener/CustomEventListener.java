package zero24_customEvent.listener;

import zero24_customEvent.event.EventObject;

// 이벤트 리스너 
public interface CustomEventListener<ExtendsEventObject extends EventObject> {
	public void eventReceived(Object sender, Object e);
}
