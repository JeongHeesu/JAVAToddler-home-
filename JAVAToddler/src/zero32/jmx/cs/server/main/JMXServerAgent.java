package zero32.jmx.cs.server.main;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.management.StandardMBean;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import javax.management.remote.rmi.RMIConnectorServer;
import javax.print.attribute.standard.Severity;

import zero32.jmx.cs.utils.ICodeConstants;
import zero32.jmx.cs.utils.IMSGConstants;
import zero32.jmx.cs.utils.LogPrtUtil;
import zero32.jmx.notification.INotificationMBean;

public class JMXServerAgent implements IMSGConstants, ICodeConstants{
	private static MBeanServer server = null;
	private static JMXConnectorServer connector = null;
	private static Map<String,ObjectName> mngObjectName = null;
	
	private JMXServerAgent(){}
	
	static { 
		server = MBeanServerFactory.createMBeanServer(MBEAN_SERVER_DOMAIN);
		mngObjectName = new HashMap<String,ObjectName>();
	}

	public static void startServerAgent(){
		JMXServiceURL serviceUrl = null;
		ObjectName connectorName = null;
		
		try {
			LocateRegistry.createRegistry(Integer.parseInt(SERVER_PORT));
			// service:jmx:rmi:// + /jndi/rmi://localhost:9999/server
			serviceUrl = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://" + SERVER_DOMAIN + ":" + SERVER_PORT + "/server");
			LogPrtUtil.echo(serviceUrl.getURLPath());
			
			Map<String, String> environment = new HashMap<String, String>();
			environment.put(RMIConnectorServer.JNDI_REBIND_ATTRIBUTE, "true");
			
			connectorName = new ObjectName(CONNECTOR_NAME);
			
			connector = JMXConnectorServerFactory.newJMXConnectorServer(serviceUrl, environment, server);
			
			server.registerMBean(connector, connectorName);
			
			connector.start();
			
			LogPrtUtil.echo(MSG_0001);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void registMBean(ServerMBean mbeanObject, String registMBeanName){
		try {
			final StandardMBean mbean = new StandardMBean(mbeanObject, ServerMBean.class);
			
			ObjectName objName = createSimpleMBean(registMBeanName);
			server.registerMBean(mbean, objName);
			
			printMBeanInfo(objName);
		} catch (InstanceAlreadyExistsException e) {
			e.printStackTrace();
		} catch (MBeanRegistrationException e) {
			e.printStackTrace();
		} catch (NotCompliantMBeanException e) {
			e.printStackTrace();
		}
		LogPrtUtil.echo(MSG_0003);
	}
	
	private static ObjectName createSimpleMBean(String mbeanClassName) {
		try {
			ObjectName mbeanObjectName = new ObjectName(MBEAN_HEADNAME + mbeanClassName);
			
			mngObjectName.put(MBEAN_HEADNAME + mbeanClassName, mbeanObjectName);
			
			LogPrtUtil.echo(MSG_0005);	
			
			return mbeanObjectName;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
	public static void stopServerAgent(){
		try {
			Iterator<String> keys = mngObjectName.keySet().iterator();
			while(keys.hasNext()){
				String key = keys.next();
				server.unregisterMBean(mngObjectName.get(key));
			}
			
			connector.stop();
			
		} catch (MBeanRegistrationException e) {
			e.printStackTrace();
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		LogPrtUtil.echo(MSG_0002);
	}
	
	public static void unregistMBean(String unregistTargetMBeanNames){
		ObjectName targetMBean = mngObjectName.get(MBEAN_HEADNAME + unregistTargetMBeanNames);
		try {
			server.unregisterMBean(targetMBean);
		} catch (MBeanRegistrationException e) {
			e.printStackTrace();
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		}
		LogPrtUtil.echo(MSG_0004);
	}
	
	private static void printMBeanInfo(ObjectName mbeanObjectName) {
		MBeanInfo info = null;
		try {
			info = server.getMBeanInfo(mbeanObjectName);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		System.out.println("\nclass name : \t" + info.getClassName());
		System.out.println("\ndescription : \t" + info.getDescription());
		System.out.println("\nattributes");

		MBeanAttributeInfo[] attrInfo = info.getAttributes();

		if (attrInfo.length > 0) {
			for (int i = 0; i < attrInfo.length; i++) {
				System.out.println(" ** name : \t" + attrInfo[i].getName());
				System.out.println("    descr : \t"
						+ attrInfo[i].getDescription());
				System.out.println("    type : \t" + attrInfo[i].getType()
						+ "\tread : " + attrInfo[i].isReadable() + "\twrite : "
						+ attrInfo[i].isWritable());
			}
		} else {
			System.out.println(" ** No attributes **");
			System.out.println("\nconstructors");
			MBeanConstructorInfo[] constrInfo = info.getConstructors();
			for (int i = 0; i < constrInfo.length; i++) {
				System.out.println(" ** name : \t" + constrInfo[i].getName());
				System.out.println("    descr : \t"
						+ constrInfo[i].getDescription());
				System.out
						.println("    param : \t"
								+ constrInfo[i].getSignature().length
								+ " parameter(s)");
			}

			System.out.println("\noperations");
			MBeanOperationInfo[] opInfo = info.getOperations();
			if (opInfo.length > 0) {
				for (int i = 0; i < opInfo.length; i++) {
					System.out.println(" ** name : \t" + opInfo[i].getName());
					System.out.println("    descr : \t"
							+ opInfo[i].getDescription());
					System.out
							.println("    param : \t"
									+ opInfo[i].getSignature().length
									+ " parameter(s)");
				}
			} else {
				System.out.println(" ** No operations ** ");
				System.out.println("\nnotifications");
				MBeanNotificationInfo[] notifInfo = info.getNotifications();
				if (notifInfo.length > 0) {
					for (int i = 0; i < notifInfo.length; i++) {
						System.out.println(" ** name : \t"
								+ notifInfo[i].getName());
						System.out.println("    descr : \t"
								+ notifInfo[i].getDescription());
						String notifTypes[] = notifInfo[i].getNotifTypes();
						for (int j = 0; j < notifTypes.length; j++) {
							System.out.println("    type : \t" + notifTypes[j]);
						}
					}
				} else {
					System.out.println(" ** No notifications **");
				}
			}
		}
	}
}
