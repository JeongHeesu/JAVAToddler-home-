package zero11_xmlToJavaObjMapping.mapping;

import java.util.ArrayList;
import java.util.List;

public class Entries {
	private List<Entry> entries = new ArrayList<Entry>();
	
	public void addEntries(Entry entry){
		this.entries.add(entry);
	}

	public List<Entry> getEntries() {
		return entries;
	}
}
