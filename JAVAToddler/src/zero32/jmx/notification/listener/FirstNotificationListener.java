package zero32.jmx.notification.listener;

import javax.management.Notification;
import javax.management.NotificationListener;

import zero16_ibatis.bean.MemberBean;

public class FirstNotificationListener implements NotificationListener {

	// 1. 발행된 Notification 수취
	// 2. 변경금지 객체
	@Override
	public void handleNotification(Notification notification, Object handback) {
		System.out.println("FirstNotificationListener 취득  message : " + notification.getMessage());
		System.out.println("FirstNotificationListener 취득  sequenceNumber : " + notification.getSequenceNumber());
		System.out.println("FirstNotificationListener 취득  type : " + notification.getType());
		if(notification.getSource() instanceof MemberBean){
			MemberBean memberBean =  (MemberBean) notification.getSource();
			System.out.println("FirstNotificationListener 취득 MemberBean : " + memberBean.getMem_id() + " / " + 
					memberBean.getMem_pass() + " / " + memberBean.getMem_name());
		}else{
			System.out.println("FirstNotificationListener 취득  source : " + notification.getSource());
		}
		System.out.println("FirstNotificationListener 취득  userData : " + notification.getUserData());
	}

}
