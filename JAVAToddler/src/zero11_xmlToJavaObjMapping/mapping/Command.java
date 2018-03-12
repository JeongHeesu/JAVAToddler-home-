package zero11_xmlToJavaObjMapping.mapping;

public class Command {
	private String attribute;
	private String className;
	private String command;
	public String getAttribute() {
		return attribute;
	}
	
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	
	public String getClassName() {
		return className;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}

	public void setCommand(String command) {
		this.command = command;
	}
	
}
