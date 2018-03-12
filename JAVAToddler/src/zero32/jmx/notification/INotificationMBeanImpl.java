package zero32.jmx.notification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.management.Attribute;
import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

import zero16_ibatis.bean.MemberBean;

// NotificationBroadcasterSupport : MBean내에서 Notification을 발행 권한 부여  
public class INotificationMBeanImpl extends NotificationBroadcasterSupport 
		implements INotificationMBean {
	private double presentTemperature = 0d;
	private double oldTemperature = 0d;
	private List<Double> temperList = new ArrayList<Double>();
	private int sequenceNumber = 1;
	private MemberBean memberBean;
	
	public void setPresentTemperature(double temperature) {
		this.oldTemperature = this.presentTemperature;
		this.presentTemperature = temperature;
		this.temperList.add(new Double(temperature));
		
		// 공지 로직
		// Notification은 MBean으로부터 시작되어 MBeanServer를 경유해 로컬 또는
		// 원격 접속측으로 통지됨.
		//    AttributeChangeNotification : MBean의 속성 변경 통지
		
		//    1. Notification 발행 MBean
		//    2. 발행 순번(notification 리스너 측 전달 시퀀스)
		//    3. 발행일자
		//    4. 발행 메세지(notification 리스너 측 전달 메세지)
		//    5. 속성 이름
		//    6. 속성 타입
		//    7. 변경전 값
		//    8. 변경후 값
		Notification noti = new AttributeChangeNotification(this, 
									sequenceNumber++, 
									System.currentTimeMillis(), 
									"temperature changed", 
									"presentTemperature", 
									"double", 
									this.oldTemperature, 
									this.presentTemperature);
		
		// NotificationListener를 구현한 클래스의 handleNotification()를 통해 발행된 Notification 취득.
		sendNotification(noti);
	}
	
	// NotificationBroadcasterSupport의 getNotificationInfo()은 MBean내에서 발행될수있는
	// Notification 관련 정보를 접근측에 반환.
	@Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        String[] types = new String[] {
            AttributeChangeNotification.ATTRIBUTE_CHANGE
        };
        String name = AttributeChangeNotification.class.getName();
        String description = "온도 설정이 변화되어 AttributeChangeNotification이 발행됨.";
        MBeanNotificationInfo info =
            new MBeanNotificationInfo(types, name, description);
        return new MBeanNotificationInfo[] {info};
    }
	
	@Override
	public double getPresentTemperature() {
		return presentTemperature;
	}

	@Override
	public double getMaximumTemperature() {
		Double max = Collections.max(this.temperList);
		return max;
	}

	@Override
	public double getMinimumTemperature() {
		Double min = Collections.min(this.temperList);
		return min;
	}

	@Override
	public void resetMaxAndMin() {
		this.presentTemperature = 0d;
		this.oldTemperature = 0d;
		this.temperList.clear();
	}

	@Override
	public void setMemberBean(MemberBean memberBean) {
		System.out.println("server.setAttribute(objName, new Attribute(\"MemberBean\", memberBean));에의한 콜");
		this.memberBean = memberBean;
		
		Notification noti = new AttributeChangeNotification(this.memberBean, 
						sequenceNumber++, 
						System.currentTimeMillis(), 
						"memberBean send", 
						"memberBean", 
						"Type MemberBean", 
						null, 
						null);
		
		// NotificationListener를 구현한 클래스의 handleNotification()를 통해 발행된 Notification 취득.
		sendNotification(noti);
	}
}
