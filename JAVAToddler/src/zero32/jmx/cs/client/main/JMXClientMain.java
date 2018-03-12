package zero32.jmx.cs.client.main;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import zero32.jmx.cs.server.main.ServerMBean;

public class JMXClientMain {

	public static void main(String[] args) {
		try {
			JMXServiceURL serviceUrl = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://192.168.202.27:9999/server");
			JMXConnector serviceConnector = JMXConnectorFactory.connect(serviceUrl);
			serviceConnector.connect();
			
			MBeanServerConnection serverConnector = serviceConnector.getMBeanServerConnection();
			
			ObjectName serverMBeanGetName = new ObjectName("JmxServerAgent:name=ServerStatusNMessageMBeanImpl");
			
			ServerMBean serverMBean =
					(ServerMBean)MBeanServerInvocationHandler.newProxyInstance(serverConnector, serverMBeanGetName, 
																					ServerMBean.class, true);
			System.out.println("1" + serverMBean.getMessage());
			
			serverMBean.setMessage("send a message to server");
			
			System.out.println("2" + serverMBean.getMessage());
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
		}
	}

}
