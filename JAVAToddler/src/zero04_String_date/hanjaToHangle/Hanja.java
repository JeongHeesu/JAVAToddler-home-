package zero04_String_date.hanjaToHangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.StringTokenizer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Hanja {
	private static char[] HANJA_TO_HANGLE_MAP = new char[65565];
	 
    public Hanja() throws FileNotFoundException, JAXBException {
         loadXMLData();
     }
 
    private void loadXMLData() throws FileNotFoundException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(HanjaToHangle.class);
 
        //XML을 자바 Object로 변환하기 위한 Unmarshaller 작성
        Unmarshaller unmarshaller = context.createUnmarshaller();
        //unmarshaller.setSchema(schema);
 
        //XML에서 자바 Object로 변환
        URL url = ClassLoader.getSystemResource("zero04_String_date/hanjaToHangle/hanjatohangle.xml");
        
        HanjaToHangle root = (HanjaToHangle) unmarshaller.unmarshal(
        		new FileInputStream(url.getFile()));
        
        StringTokenizer stok = new StringTokenizer(root.unicodeMap.code, ",");
        
        int i = 0;
        while(stok.hasMoreTokens()) {
             int value = Integer.decode(stok.nextToken().trim());
             HANJA_TO_HANGLE_MAP[i++] = (char)value;
        }
     }
 
    public static String toHangle(String hanja) throws UnsupportedEncodingException {
         char unicode = 0x0000;
         byte[] hanjaByte = hanja.getBytes("UTF-8");
         for(int i = 0 ; i < hanjaByte.length; ) {
             if( (hanjaByte[i]&0xFF) < 0x80 ) {
                 i++;
                 continue;
             } else if( (hanjaByte[i]&0xFF) < 0xE0 ) {
                 i += 2;
                 continue;
             } else if( (hanjaByte[i]&0xFF) < 0xF0 ) {
                 unicode = (char)(hanjaByte[i] & 0x0f);
                 i++;
                 unicode = (char)(unicode << 6);
                 unicode = (char)(unicode | (hanjaByte[i] & 0x3f));
                 i++;
                 unicode = (char)(unicode << 6);
                 unicode = (char)(unicode | (hanjaByte[i] & 0x3f));
                 i++;
             }
 
            if(HANJA_TO_HANGLE_MAP[unicode] != unicode) {
                 unicode = HANJA_TO_HANGLE_MAP[unicode];
                 hanjaByte[i-1] = (byte)((unicode & 0x3f) | 0x80);
                 hanjaByte[i-2] = (byte)(((unicode << 2) & 0x3f00 | 0x8000) >> 8);
                 hanjaByte[i-3] = (byte)(((unicode << 4) & 0x3f0000 | 0xe00000) >> 16);
                 continue;
             }
         }
         return (new String(hanjaByte, "UTF-8"));
     }
}
