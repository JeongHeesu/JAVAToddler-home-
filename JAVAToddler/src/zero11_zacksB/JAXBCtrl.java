package zero11_zacksB;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JAXBCtrl {

	// XML to Java Object
	public static void unmarshalling(String resName) {
		try {
			URL resUrl = ClassLoader.getSystemResource(resName);
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Members.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Members members = (Members) jaxbUnmarshaller.unmarshal(new File(resUrl.getFile()));

			System.out.println("members : " + members.total);
			List<MemberVO> memberList = members.members; 
			for (MemberVO member : memberList)
				System.out.println(member.getMem_id() + " " + member.getMem_pass()
						+ "  " + member.getMem_name());

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	// JavaObject to XML
	public static void marshalling() {
		JAXBContext contextObj;
		try {
			contextObj = JAXBContext.newInstance(Members.class);
		
			Marshaller marshallerObj = contextObj.createMarshaller();  
			marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
			
			MemberVO memberVO1 = new MemberVO();
			memberVO1.setMem_id("aa01");
			memberVO1.setMem_pass("fdasfdas");
			memberVO1.setMem_name("홍길동");
	
			MemberVO memberVO2 = new MemberVO();
			memberVO2.setMem_id("bb01");
			memberVO2.setMem_pass("4001");
			memberVO2.setMem_name("박길동");
			  
			List<MemberVO> memberList = new ArrayList<MemberVO>();
			memberList.add(memberVO1);
			memberList.add(memberVO2);
			  
			Members rootElement = new Members();
			rootElement.members = memberList;
		
			marshallerObj.marshal(rootElement, new FileOutputStream("D:\\temp\\members_marshalling.xml"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}  
	}
}
