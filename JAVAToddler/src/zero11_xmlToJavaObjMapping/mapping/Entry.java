package zero11_xmlToJavaObjMapping.mapping;

import java.util.ArrayList;
import java.util.List;

public class Entry {
	
	private List<Chain> chains = new ArrayList<Chain>();
    
	public void addChains(Chain chain){
        this.chains.add(chain);
    }

	public List<Chain> getChains() {
		return chains;
	}
}
