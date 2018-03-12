package zero26_schema.generate;

public class TestMain {

	public static void main(String[] args) {
		// XML을 자바 객체로....
		JAXBCtrl.unmarshalling("zero26_schema/sample.xml");
	}

}
