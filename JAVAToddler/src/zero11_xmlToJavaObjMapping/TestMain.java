package zero11_xmlToJavaObjMapping;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.binder.DigesterLoader;
import org.apache.commons.digester3.xmlrules.FromXmlRulesModule;
import org.xml.sax.SAXException;

import zero11_xmlToJavaObjMapping.mapping.Chain;
import zero11_xmlToJavaObjMapping.mapping.Command;
import zero11_xmlToJavaObjMapping.mapping.Entries;
import zero11_xmlToJavaObjMapping.mapping.Entry;


public class TestMain {

	public static void main(String[] args) {
		// digester rule 로딩
		DigesterLoader  digesterLoader = 
				DigesterLoader.newLoader(new FromXmlRulesModule() {
					            @Override
					            protected void loadRules() {
					                loadXMLRules(getClass().getResource("/zero11_xmlToJavaObjMapping/rule/digester-catalog-rules.xml"));
					            }
					        });
		
		// 로딩된 rule을 기초로 digester 생성.
		Digester digester = digesterLoader.newDigester();
		
        // 정의된 rule과 맵핑되는 대상 xml 파싱을위한 스트리밍 생성
        InputStream input = Digester.class.getClass().getResourceAsStream("/zero11_xmlToJavaObjMapping/target/entries.xml");

        Entries entries = null;
        try {
        	// 파싱과 객체 맵핑을 통한 결과 객체 반환.
        	entries = (Entries)digester.parse(input);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        
        int cnt = 0;
        for(Entry entryCnt : entries.getEntries()){
			System.out.println("entry : " + cnt);
			for(Chain chainCnt : entryCnt.getChains()){
				System.out.print("\tchain name : " + chainCnt.getName() + 
						"chain testAttribute : " + chainCnt.getTest()+ 
						" \t\t");
				for(Command commandCnt : chainCnt.getCommands()){
					System.out.print("command attribute : " + commandCnt.getAttribute() + 
							" / className : " + commandCnt.getClassName());
					System.out.println();
				}
			}
			cnt++;
		}
	}
}
