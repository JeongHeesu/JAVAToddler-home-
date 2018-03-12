package zero24_customEvent.event;

import zero16_ibatis.bean.MemberBean;

public class SendTargetObject extends EventObject {
	private MemberBean member = null;
	
	
	public SendTargetObject(MemberBean member) {
		this.member = member;
	}
	
	public MemberBean getMemberInfo(){
		return this.member;
	}
}
