package zero11_xmlReadingNSave;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class TestMain {
	private final static String READPATH = 
			"D:\\programs\\apache-tomcat-7.0.29\\conf\\server.xml";
	
	// tomcat 5.5 이상 버젼에서는 가상 호스팅을위한 Context 설정을 server.xml에서 분리하였음.
	// server.xml의 변경시 반영을위해서는 서버가 재기동 되어야하지만, Context를 분리하여 추가되는
	// 웹 어플리케이션이 서버에 반영되어도 서버는 재기동할 필요가 없어졌음.
	// 추가되는 Context 파일의 이름은 추가되는 웹 어플리케이션의 실행시 http://localhost/projectName/...의 형식으로 구성됨.
	// ex) http://localhost/board/free/...로 구성되는 Context파일의 이름은 board#free.xml로 작성함.
	private final static String WRITEPATH = 
			"D:\\programs\\apache-tomcat-7.0.29\\conf\\Catalina\\localhost\\projectName.xml";
	
	public static void main(String[] args) {
		
		// server.xml reading
		SAXBuilder sb = new SAXBuilder();

		Document readDoc = null;
		try {
			readDoc = sb.build(READPATH);
		} catch (JDOMException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// server.xml내 특정 엘리먼트의 값 변경 및 추가
		Element root = readDoc.getRootElement();
		// server.xml내 주석처리된 엘리먼트는 취득되지 않음.
		List<Element> connectors = root.getChild("Service").getChildren("Connector");
		for(Element element : connectors){
			System.out.println("port : " + element.getAttribute("port").getValue());
			System.out.println("protocol : " + element.getAttribute("protocol").getValue());
			System.out.println("URIEncoding : " + element.getAttribute("URIEncoding"));
			System.out.println("redirectPort : " + element.getAttribute("redirectPort"));
		}
		
		
		// 추가된 웹 어플리케이션의 Context 추가 작업
		// 추가 엘리먼트 구성
		Element context = new Element("Context");
		// docBase가 상대경로일때 default appBase의 webapps가 기준이됨.
		// D:\\programs\\apache-tomcat-7.0.29\\conf\\Catalina\\localhost\\ 폴더에 생성될 xml 파일명은
		// path명과 대소문자 일치되어야함.
		// docBase와는 무관함.
		context.setAttribute("path", "/projectName");
		context.setAttribute("docBase", "경로");
		context.setAttribute("reloadable", "true");
		context.setAttribute("autoreload", "true");
		context.setAttribute("cached", "false");
		
		Document writeDoc = new Document(context); 
		
		// 출력 설정
		XMLOutputter xo = new XMLOutputter();
		Format fo = xo.getFormat();
		fo.setEncoding("UTF-8");
		// 들여쓰기
		fo.setIndent("    ");
		// 줄바꿈
		fo.setLineSeparator("\r\n");
		//XML내 CRLF로인한 자동줄바꿈 해제
		fo.setTextMode(Format.TextMode.TRIM);
		xo.setFormat(fo);
		try {
			// 읽어온 server.xml 내용의 화면출력
			xo.output(readDoc, System.out);
			
			// 추가되는 Context 파일 저장
			xo.output(writeDoc, new FileOutputStream(WRITEPATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
