package zero12_toolProvider;

import java.io.IOException;
import java.net.URI;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;

public class DynamicJavaSourceCodeObject extends SimpleJavaFileObject {

	private String qualifiedName;
	private String sourceCode;

	protected DynamicJavaSourceCodeObject(String name, String code){
		super(URI.create("string:///" + name.replaceAll("\\.", "/")
				+ JavaFileObject.Kind.SOURCE.extension),
				JavaFileObject.Kind.SOURCE);
		this.qualifiedName = name;
		this.sourceCode = code;
	}

	@Override
	public CharSequence getCharContent(boolean ignoreEncodingErrors)throws IOException{
		return sourceCode;
	}
}
