package zero32.jmx.base;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

public class TestMain {

	/**
	 * MBeanServer의 특정 객체의 로컬(RMI Adapter), 원격(Http Adapter), 
	 * SNMP(Simple Network Management Protocol - 단순한 네트워크 망 장비 관리 프로토콜)의
	 * 각 컨넥션 레벨을 통해 요청처로부터의 요청과 응답을 처리. 
	 * @param args
	 */
	public static void main(String[] args) {
		// MBeanServer 취득(로컬 JVM내에 존재하는 MBeanServer취득)
		MBeanServer server = ManagementFactory.getPlatformMBeanServer();

		// MBeanServer 등록명 설정
		try {
			ObjectName objName = new ObjectName("mbean.object.register:type=Hello");

			// MBean 객체(로컬 접속 또는 원격 접속 또는 네트워크 망 장비 내 펌웨어 형태로 존재하며, 
			// 어플리케이션 외부 접속 대상 객체)
			Hello hello = new Hello();
		
			// 서버 등록
			server.registerMBean(hello, objName);
		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
		} catch (InstanceAlreadyExistsException e) {
			e.printStackTrace();
		} catch (MBeanRegistrationException e) {
			e.printStackTrace();
		} catch (NotCompliantMBeanException e) {
			e.printStackTrace();
		}

		System.out.println("무한 대기.....");
		
		try {
			Thread.sleep(Long.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// jconsole을 활용한 MBean 객체 접근 실행 순서
		// 1. Application 실행
		// 2. cmd 실행
		// 3. > %java_home%\ben\jconsole.exe 실행
		// 4. jconsole 실행시 ConnectionFailedSSL1 출력시 [insecure] 버튼 클릭
		// 5. MBeans탭 내 MBeanServer 대상 Hello MBean 객체 등록시의 ObjectName 속성 확인
		// 6. ObjectName 속성 하위 Hello MBean 객체의 변수와 함수 확인 및 실행
		
	}
}
