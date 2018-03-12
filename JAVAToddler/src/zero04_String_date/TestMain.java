package zero04_String_date;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBException;

import zero04_String_date.common.CommonUtil;
import zero04_String_date.dateCls.CalendarAfterAndBfore;
import zero04_String_date.dateCls.DateUtil;
import zero04_String_date.hanjaToHangle.Hanja;
import zero04_String_date.stringCls.StringUtil;

public class TestMain
{
	public static void main(String[] args) 
	{
		// 문자열 처리
		CalendarAfterAndBfore cal = new CalendarAfterAndBfore();
		Date date = cal.setDate(2005, 3, 2);
		
		SimpleDateFormat formater = new SimpleDateFormat();
		formater.applyPattern("yyyy/MM/dd");
		
		System.out.println("해당일 : " + date.toString());
		System.out.println("해당일 다음날 : " + formater.format(cal.afterOneDay(date)));
		
		formater.applyPattern("dd hh:mm:ss");
		System.out.println("해당일 : " + formater.format(cal.beforOneDay(date)));
		
		CommonUtil cu = new CommonUtil();
		// 주민번호 인증
		System.out.println(cu.certifyRegnos("-를포함하는 본인주민번호"));
		
		// 셋팅된 JVM 디폴트 문자셋 확인
		System.out.println("JVM 디폴트 문자셋 : " + System.getProperty("file.encoding"));
		
		String keyword = "";
		try {
			try {
				Hanja hanja = new Hanja();
				keyword = hanja.toHangle("全寅鎬");
				System.out.println("한글 변환 이름 : " + keyword);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (JAXBException e) {
				e.printStackTrace();
			}
			
			// 인코딩 체크 코드
			String charset[] = {"euc-kr", "ksc5601", "iso-8859-1", "8859_1", "ascii", "UTF-8"};
			for(int i=0; i<charset.length ; i++){
				for(int j=0 ; j<charset.length ; j++){
					if(i==j) continue;
					System.out.println(charset[i]+" : "+charset[j]+" :"+new String(keyword.getBytes(charset[i]),charset[j]));
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		System.out.println("천단위 변환 : " + StringUtil.moneyFormat("123456789"));
		
		// 문자열 결합시의 속도 비교(String+String vs StringBuffer)
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long now = System.currentTimeMillis();
		sp();
		System.out.printf("String : %d\n", System.currentTimeMillis()-now);
		
		now = System.currentTimeMillis();
		sb();
		System.out.printf("StringBuffer : %d\n", System.currentTimeMillis()-now);
	}
	
	private static void sp(){
		String data = "";
		for(int i=0; i<10000; i++){
			data += "*";
		}
	}
	private static void sb(){
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<10000; i++){
			sb.append("*");
		}
	}
}
