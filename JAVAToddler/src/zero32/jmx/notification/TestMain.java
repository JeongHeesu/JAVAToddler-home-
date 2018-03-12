package zero32.jmx.notification;

import java.io.IOException;
import java.lang.management.ManagementFactory;

import javax.management.Attribute;
import javax.management.AttributeNotFoundException;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.NotificationBroadcasterSupport;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.StandardMBean;

import zero16_ibatis.bean.MemberBean;
import zero32.jmx.notification.listener.FirstNotificationListener;
import zero32.jmx.notification.listener.SecondNotificationListener;

public class TestMain {

	public static void main(String[] args) {
		MBeanServer server = ManagementFactory.getPlatformMBeanServer();
		
		try {
			ObjectName objName = new ObjectName("zero32.jmx.notification:type=INotificationMBean");
			INotificationMBeanImpl noti = new INotificationMBeanImpl();
			// StandardMBean으로 랩핑..
			final StandardMBean mbean = new StandardMBean(noti, INotificationMBean.class);
			server.registerMBean(mbean, objName);

			// 서버(MBeanServer) <= MBean 등록(extends NotificationBroadcasterSupport) <= 리스너 등록(implements NotificationListener)
			// 청취자 등록
			FirstNotificationListener firstListener = new FirstNotificationListener();
			SecondNotificationListener secondListener = new SecondNotificationListener();
			
			noti.addNotificationListener(firstListener, null, null);
			noti.addNotificationListener(secondListener, null, null);
			
			// MBeanServer는 등록된 MBean의 setter/getter명을 attribute로 관리
			Double dbl = (Double) server.getAttribute(objName, "PresentTemperature");
			
			MemberBean memberBean = new MemberBean();
			memberBean.setMem_id("a001");
			memberBean.setMem_pass("asdfasdf");
			memberBean.setMem_name("김은대");
			// 등록된 NotificationListener 대상 통지 발행(MemberBean 전달)
			server.setAttribute(objName, new Attribute("MemberBean", memberBean));
			
			System.out.println("Agent is ready.... Press Enter to close...");
			System.in.read();
		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
		} catch (InstanceAlreadyExistsException e) {
			e.printStackTrace();
		} catch (MBeanRegistrationException e) {
			e.printStackTrace();
		} catch (NotCompliantMBeanException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		} catch (ReflectionException e) {
			e.printStackTrace();
		} catch (AttributeNotFoundException e) {
			e.printStackTrace();
		} catch (MBeanException e) {
			e.printStackTrace();
		} catch (InvalidAttributeValueException e) {
			e.printStackTrace();
		}
		
		// jconsole을 활용한 MBean 객체 접근 실행 순서
		// 1. Application 실행
		// 2. cmd 실행
		// 3. > %java_home%\ben\jconsole.exe 실행
		// 4. jconsole 실행시 ConnectionFailedSSL1 출력시 [insecure] 버튼 클릭
		// 5. MBeans탭 내 MBean 클래스의 MBeanServer 등록시의 name중 팩키지명 확인.
		// 6. 팩키지명 내 MBean 클래스의 MBeanServer 등록시의 name중 type명 확인.
		// 7. Attributes의 PresentTemperature에 값 다수 입력 후 MaximumTemperature와 
		//    MinimumTemperature 값 확인
		// 8. Operations의 resetMaxAndMin 실행 및 PresendTemperature, MaximumTemperature,
		//    MinimumTemperature 확인
		// 9. server.setAttribute(objName, new Attribute("MemberBean", memberBean)); 실행시
		//    NotificationListener 등록 리스너에 통지 발행 확인
	}
}
