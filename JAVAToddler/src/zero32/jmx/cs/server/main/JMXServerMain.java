package zero32.jmx.cs.server.main;

public class JMXServerMain {
	
	// 관련 정보 검색과 API를 활용해 스스로 주석을 작성해 코드를 이해해주세요.
	
	public static void main(String[] args) {
		JMXServerAgent.startServerAgent();
		JMXServerAgent.registMBean(new ServerStatusNMessageMBeanImpl(), "ServerStatusNMessageMBeanImpl");
	}
}
