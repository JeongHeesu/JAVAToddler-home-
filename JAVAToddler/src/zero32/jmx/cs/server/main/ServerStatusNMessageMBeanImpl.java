package zero32.jmx.cs.server.main;

import javax.management.AttributeChangeNotification;
import javax.management.NotificationBroadcasterSupport;

public class ServerStatusNMessageMBeanImpl extends NotificationBroadcasterSupport
		implements ServerMBean {

	private String status = "initial status";
	private String message = "";
	private String oldMessage = "";
	
	@Override
	public String getStatus() {
		return this.status;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	@Override
	public void setMessage(String message) {
		this.oldMessage = this.message;
		this.message = message;
		
		AttributeChangeNotification notification = new AttributeChangeNotification(this, 0, System.currentTimeMillis(), 
				"message changed", "message", "String", this.oldMessage, this.message);
		
		this.sendNotification(notification);
	}

	@Override
	public void reset() {
		AttributeChangeNotification notification = new AttributeChangeNotification(this, 0, System.currentTimeMillis(), 
				"status changed", "status", "String", null, this.status);
		
		this.sendNotification(notification);
	}

}
