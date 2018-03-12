package zero26_schema.generate;

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
			
			JAXBContext jaxbContext = JAXBContext.newInstance(SqlMapConfig.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			SqlMapConfig config = (SqlMapConfig) jaxbUnmarshaller.unmarshal(new File(resUrl.getFile()));

			System.out.println("namespace : " + config.getNamespace());
			System.out.println(config.getSqlMap());
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
}
