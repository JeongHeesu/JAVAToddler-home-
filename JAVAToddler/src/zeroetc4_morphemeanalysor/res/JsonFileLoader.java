package zeroetc4_morphemeanalysor.res;

import java.io.IOException;
import org.json.JSONException;

public class JsonFileLoader {
	
	public static String getPluginAbstarct(String filePath) throws JSONException, IOException {
    	JSONReader json = new JSONReader(filePath);
        String res = null;
        
        res = String.format("* Name: %s\n* Type: %s\n* Version: %s\n* Author: %s\n* Description: %s\n",
        		json.getName(), json.getType(), json.getVersion(), json.getAuthor(), json.getDescription());
        return res;
    }
}
