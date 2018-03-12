package zero11_zacksB;

public class TestMain {

	public static void main(String[] args) {
		// XML을 자바 객체로....
		JAXBCtrl.unmarshalling("zero11_ZacksBJavaAndXmlMapping/members_unmarshalling.xml");
		
		// 자바 객체를 XML로....		
		JAXBCtrl.marshalling();
	}

}
