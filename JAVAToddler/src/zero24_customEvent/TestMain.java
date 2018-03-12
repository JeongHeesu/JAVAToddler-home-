package zero24_customEvent;

import zero16_ibatis.bean.MemberBean;
import zero24_customEvent.event.EventObject;
import zero24_customEvent.event.SendTargetObject;
import zero24_customEvent.handler.CustomEventHandler;
import zero24_customEvent.listener.CustomEventListener;
import zero24_customEvent.publisher.CustomEventPublisher;

public class TestMain{

	public static void main(String[] args) {
		// 이벤트 퍼블리싱 객체 생성
		CustomEventPublisher publisher = new CustomEventPublisher();
		
		// 실 전달 목적 클래스
		MemberBean member = new MemberBean();
		member.setMem_id("a001");
		member.setMem_pass("asdfasdf");
		member.setMem_name("김은대");
		
		// 리스너 클래스 등록
		CustomEventHandler.addEventListener(new ListenerObject1());
		CustomEventHandler.addEventListener(new ListenerObject2());
		
		// 이벤트 퍼블리싱 시작
		// SendTargetObject는 EventObject를 상속받아 실 전달 목적 클래스를 전달하는 랩퍼 클래스
		publisher.doWork(new SendTargetObject(member));
	}

}

class ListenerObject1 implements CustomEventListener<EventObject>{
	// 이벤트 취득 리스너 구현.
	@Override
	public void eventReceived(Object sender, Object e) {
		MemberBean member = ((SendTargetObject)e).getMemberInfo();
		System.out.println("ListenerObject1 mem_id : " + member.getMem_id() + 
				" / mem_pass : " + member.getMem_pass() +
				" / mem_name : " + member.getMem_name());
	}
}

class ListenerObject2 implements CustomEventListener<EventObject>{
	// 이벤트 취득 리스너 구현.
	@Override
	public void eventReceived(Object sender, Object e) {
		MemberBean member = ((SendTargetObject)e).getMemberInfo();
		System.out.println("ListenerObject2 mem_id : " + member.getMem_id() + 
				" / mem_pass : " + member.getMem_pass() +
				" / mem_name : " + member.getMem_name());
	}
}