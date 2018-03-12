package zero04_String_date.hanjaToHangle;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// <hanjaToHangle>
//     <unicodeMap>
@XmlRootElement
public class HanjaToHangle {
	@XmlElement
	public XMLUnicodeMap unicodeMap;
}
