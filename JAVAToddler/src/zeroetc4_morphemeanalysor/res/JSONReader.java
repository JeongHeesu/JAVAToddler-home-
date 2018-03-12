/*  Copyright 2010, 2011 Semantic Web Research Center, KAIST

This file is part of JHanNanum.

JHanNanum is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

JHanNanum is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with JHanNanum.  If not, see <http://www.gnu.org/licenses/>   */

package zeroetc4_morphemeanalysor.res;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * <code>JSONReader</code> is for reading data from the configuration files for each plug-in.
 * 
 * @see <a href="http://json.org">http://json.org</a>
 */
public class JSONReader {
	private String filePath = null;
	private JSONObject json = null;
	
	public JSONReader(String filePath) throws JSONException, IOException {
		FileReader reader = new FileReader(filePath);
		StringBuffer buf = new StringBuffer();
		char[] cbuf = new char[4096];
		int idx = 0;
		while ((idx = reader.read(cbuf)) != -1) {
			buf.append(cbuf, 0, idx);
		}
		json = new JSONObject(buf.toString());
		
		reader.close();
	}
	
	public String getName() throws JSONException {
		return json.getString("name");
	}
	
	public String getVersion() throws JSONException {
		return json.getString("version");
	}
	
	public String getAuthor() throws JSONException {
		String res = "";
		
		JSONArray array = json.getJSONArray("author");
		JSONObject obj = null;
		for (int i = 0; i < array.length(); i++) {
			if (i > 0) {
				res += ", ";
			}
			obj = array.getJSONObject(i);
			if (!obj.getString("email").equals("null")) {
				res += obj.getString("name") + "<" + obj.getString("email") + ">";
			} else {
				res += obj.getString("name");
			}
		}
		return res;
	}
	
	public String getDescription() throws JSONException {
		return json.getString("description");
	}
	
	public String getType() throws JSONException {
		return json.getString("type");
	}
	
	public String getValue(String key) throws JSONException {
		return json.getString(key);
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
