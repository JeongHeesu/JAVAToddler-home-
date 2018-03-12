package zero13_javascriptEnginAPI.util;

import java.util.List;

public class EngineInfomationBean {
	private String engineName;
	private String engineVersion;
	private String fileName;
	private String languageName;
	private String languageVersion;
	private List<String> mimeType;
	public String getEngineName() {
		return engineName;
	}
	public void setEngineName(String engineName) {
		this.engineName = engineName;
	}
	public String getEngineVersion() {
		return engineVersion;
	}
	public void setEngineVersion(String engineVersion) {
		this.engineVersion = engineVersion;
	}
	public String getLanguageName() {
		return languageName;
	}
	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}
	public String getLanguageVersion() {
		return languageVersion;
	}
	public void setLanguageVersion(String languageVersion) {
		this.languageVersion = languageVersion;
	}
	public List<String> getMimeType() {
		return mimeType;
	}
	public void setMimeType(List<String> mimeType) {
		this.mimeType = mimeType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
