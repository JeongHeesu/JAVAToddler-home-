package zero24_customEvent.handler;

import java.util.ArrayList;
import zero24_customEvent.event.EventObject;
import zero24_customEvent.listener.CustomEventListener;
public class CustomEventHandler {
	
	// 이벤트 리스너 목록
	private static ArrayList<CustomEventListener<EventObject>> observerList = 
			new ArrayList<CustomEventListener<EventObject>>();

	// 전파할 객체를 등록된 이벤트 리스너에 전달
	public static void raiseEvent(Object sender, EventObject e){
		for(CustomEventListener<EventObject> listener : observerList){
			listener.eventReceived(sender, e);
		}
	}
	
	// 이벤트 리스너 등록
	public static void addEventListener(CustomEventListener<EventObject> listener){
		observerList.add(listener);
	}
	
	// 이벤트 리스너 삭제
	public static void removeEventListener(CustomEventListener<EventObject> listener){
		observerList.remove(listener);
	}
}
