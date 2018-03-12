package zero32.jmx.cs.server.main;

import javax.management.DynamicMBean;

public interface ServerMBean {
	public String getStatus();
	public void setStatus(String status);
	public String getMessage();
	public void setMessage(String message);
	public void reset();
}
