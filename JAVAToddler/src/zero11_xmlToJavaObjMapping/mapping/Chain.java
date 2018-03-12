package zero11_xmlToJavaObjMapping.mapping;

import java.util.ArrayList;
import java.util.List;

public class Chain {
	private String name;
	private String test;
	private List<Command> commands = new ArrayList<Command>();
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public void addCommands(Command command) {
		this.commands.add(command);
	}

	public List<Command> getCommands() {
		return commands;
	}
}
