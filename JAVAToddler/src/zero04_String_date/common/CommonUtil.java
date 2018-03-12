package zero04_String_date.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class CommonUtil {

	/**
	 * Int형을 String형으로 변환
	 * 
	 * @param int
	 * @return String
	 */
	public String itos(int i) {
		return (new Integer(i).toString());
	}

	/**
	 * String형을 Int형으로 변환
	 * 
	 * @param String
	 * @return int
	 */
	public int stoi(String s) {
		try {
			if (s == null || s.trim().length() == 0)
				return 0;
			else
			return Integer.parseInt(s);
		} catch (NumberFormatException numberFormatException) {
			return 0;
		} catch (Exception exception) {
			return 0;
		}
	}

	/**
	 * String형을 Float 형으로 변환
	 * 
	 * @param String
	 * @return float
	 */
	public float stof(String s) {
		try {
			if (s == null || s.trim().length() == 0)
				return 0;
			else
				return new Float(s).floatValue();
		} catch (NumberFormatException numberFormatException) {
			return 0;
		} catch (Exception exception) {
			return 0;
		}
	}

	/**
	 * String을 Double형으로 변환
	 * 
	 * @param String
	 * @return double
	 */
	public double stod(String s) {
		double d = 0.0D;
		try {
			if (s == null || s.trim().length() == 0)
				return d;
			else
				return d = Double.valueOf(s).doubleValue();
		} catch (NumberFormatException numberformatexception) {
			return d;
		} catch (Exception exception) {
			return d;
		}
	}

	/**
	 * Object형을 Int형으로 변환
	 * 
	 * @param Object
	 * @return int
	 */
	public int objtoi(Object obj) {
		try {
			if (obj == null || obj.toString().trim().length() == 0)
				return 0;
			else
				return Integer.parseInt(obj.toString());
		} catch (NumberFormatException numberformatexception) {
			return 0;
		} catch (Exception exception) {
			return 0;
		}
	}

	/**
	 * char형을 Int형으로 변환
	 * 
	 * @param char
	 * @return int
	 */
	public int ctoi(char c) {
		try {
			return Integer.parseInt(String.valueOf(c));
		} catch (NumberFormatException numberformatexception) {
			return 0;
		} catch (Exception exception) {
			return 0;
		}
	}

	/**
	 * String 변수의 Null 체크후 Null일시 공백을 반환
	 * 
	 * @param String
	 * @return String
	 */
	public String nullCheck(String str) {
		if (str == null || str.trim().equals(""))
			return "";
		else
			return str;
	}

	/**
	 * String 변수의 값이 Null 이거나 공백일시 대체 값으로 변환한 후 반환
	 * 
	 * @param String
	 * @param String
	 * @return String
	 */
	public String nullCheck(String str, String cng) {
		if (str == null || str.trim().equals(""))
			return cng;
		else
			return str;
	}

	/**
	 * 두 String 변수의 값이 같다면 true를 반환
	 * 
	 * @param String
	 * @param String
	 * @return Boolean
	 */
	public boolean stringCompare(String sOne, String sTwo) {
		if (sOne == null && sTwo == null) {
			return true;
		}
		if (sOne == null && sTwo != null) {
			return false;
		}
		if (sOne != null && sTwo == null) {
			return false;
		}
		if (sOne.equals(sTwo))
			return true;
		else
			return false;
	}

	/**
	 * 숫자를 해당 자릿수 만큼 앞에 0을 붙여 스트링으로 리턴하는 메소드
	 * 
	 * @param int
	 * @param int
	 * @return String
	 *         <p>
	 *         <B>기타:</B>
	 *         <ul>
	 *         <li>ex) toLeadingZeroString(23, 5) => 00023
	 *         <li>toLeadingZeroString(123, 3) => 123
	 *         </ul>
	 */
	public String toLeadingZeroString(int original, int digit) {
		String result = "";
		String tr = Integer.toString(original);

		for (int i = 1; i <= (digit - tr.length()); i++) {
			result = result + "0";
		}
		return result + tr;
	}

	/**
	 * String 변수의 값을 Null, "null" 여부를 체크한 후 반환
	 * 
	 * @param String
	 * @return String null 아닌 값일 시 변수값에서 trim() 함수 적용후 반환
	 */
	public String nullToEmptyString(String str) {
		if (null == str) {
			return "";
		} else {
			if ("null".equals(str)) {
				return "";
			} else {
				return str.trim();
			}
		}
	}

	/**
	 * String 변수의 값이 Null 이거나 공백일시 공백으로 반환
	 * 
	 * @param String
	 * @return String
	 */
	public String empty(String src) {
		if (src == null || src.trim().equals(""))
			return "";
		return src;
	}

	/**
	 * 문자열을 substring 할때 해당 target 문자가 Null일경우 substring 하지않고 ""으로 리턴하는 메쏘드
	 * 
	 * @param String
	 * @param int
	 * @return String
	 */
	public String toSubString(String str, int beginIndex) {
		String ret = null;
		String str_temp = null;

		str_temp = nullToEmptyString(str);

		if ("".equals(str_temp)) {
			return str_temp;
		} else {
			ret = str.substring(beginIndex);

			return ret;
		}

	}

	/**
	 * 문자를 substring 할때 해당 target 문자가 null일경우 substring 하지않고 ""으로 리턴하는 메쏘드
	 * 
	 * @param String
	 * @param int
	 * @param int
	 * @return String 시작 위치와 끝 위치를 구분하여 substring
	 */
	public String toSubString(String str, int beginIndex, int endIndex) {
		String ret = null;
		String str_temp = null;

		str_temp = nullToEmptyString(str);

		if ("".equals(str_temp)) {
			return str_temp;
		} else {
			ret = str.substring(beginIndex, endIndex);

			return ret;
		}

	}

	/**
	 * String, String[], String[][](첫번째 기준열)의 Length를 구함
	 * 
	 * @param Object
	 * @return int
	 */
	public int getLength(Object oValue) {
		int iValue = 0;

		if (oValue instanceof String) {
			String one = (String) oValue;

			if ((String) oValue == null) {
				return iValue;
			} else {
				return one.length();
			}
		}
		if (oValue instanceof String[]) {
			String[] two = (String[]) oValue;

			if ((String[]) oValue == null) {
				return iValue;
			} else {
				return two.length;
			}
		}
		if (oValue instanceof String[][]) {
			String[][] three = (String[][]) oValue;

			if ((String[][]) oValue == null) {
				return iValue;
			} else {
				return three.length;
			}
		} else
			return iValue;
	}

	/**
	 * HexCode로 반환
	 * 
	 * @param String
	 * @return HexCode
	 */
	public static String getHexCode(String inputValue) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String eip;
		byte[] bip;
		String temp = "";
		String tst = inputValue;

		bip = md5.digest(tst.getBytes());
		for (int i = 0; i < bip.length; i++) {
			eip = "" + Integer.toHexString((int) bip[i] & 0x000000ff);
			if (eip.length() < 2)
				eip = "0" + eip;
			temp = temp + eip;
		}
		return temp;
	}

	/**
	 * 유니코드 값을 EUC-KR로 변환
	 * 
	 * @param String
	 * @return String
	 */
	public String Uni2Kor(String str) {
		try {
			return new String(str.getBytes("8859_1"), "EUC-KR");
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * EUC-KR을 유니코드로 변환
	 * 
	 * @param String
	 * @return String
	 */
	public String Kor2Uni(String str) {
		try {
			return new String(str.getBytes("EUC-KR"), "8859_1");
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 문자열을 캐릭터셋 엔코딩 문자열로 변환
	 * 
	 * @param 문자열
	 * @param 캐릭터셋
	 *            문자열
	 * @return
	 */
	public String encode(String str, String chr) {
		try {
			return java.net.URLEncoder.encode(str, chr);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 문자열을 캐릭터셋 디코딩 문자열로 변환
	 * 
	 * @param 문자열
	 * @param 캐릭터셋
	 *            문자열
	 * @return
	 */
	public String decode(String str, String chr) {
		try {
			return java.net.URLDecoder.decode(str, chr);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 문자열이 깨질때 문자열이 현재 어떤 charset인지 확인.
	 */
	public static void charSet(String str_kr)
			throws UnsupportedEncodingException {
		String charset[] = { "euc-kr", "ksc5601", "iso-8859-1", "8859_1",
				"ascii", "UTF-8" };

		for (int i = 0; i < charset.length; i++) {
			for (int j = 0; j < charset.length; j++) {
				if (i == j)
					continue;
				System.out.println(charset[i] + " : " + charset[j] + " :"
						+ new String(str_kr.getBytes(charset[i]), charset[j]));
			}
		}
	}
	
	/**
	 * 주민 번호 인증 
	 * @param regno 주민번호
	 * @return 주민번호 사용 여부
	 */
	public String certifyRegnos(String regno){
		if(regno.contains("-")){
			if(regno.length() != 14){
				return "주민번호가 틀립니다.";
			}
			regno = regno.replace("-", "");
		}
		if(!regno.contains("-")){
			if(regno.length() != 13){
				return "주민번호가 틀립니다.";
			}
		}
		
		int regno01 = Integer.parseInt(regno.substring(0, 1));
		int regno02 = Integer.parseInt(regno.substring(1, 2));
		int regno03 = Integer.parseInt(regno.substring(2, 3));
		int regno04 = Integer.parseInt(regno.substring(3, 4));
		int regno05 = Integer.parseInt(regno.substring(4, 5));
		int regno06 = Integer.parseInt(regno.substring(5, 6));
		int regno07 = Integer.parseInt(regno.substring(6, 7));
		int regno08 = Integer.parseInt(regno.substring(7, 8));
		int regno09 = Integer.parseInt(regno.substring(8, 9));
		int regno10 = Integer.parseInt(regno.substring(9, 10));
		int regno11 = Integer.parseInt(regno.substring(10, 11));
		int regno12 = Integer.parseInt(regno.substring(11, 12));
		int regno13 = Integer.parseInt(regno.substring(12));
		
		int sum = (regno01*2) + 
					(regno02*3) + 
					(regno03*4) + 
					(regno04*5) + 
					(regno05*6) + 
					(regno06*7) + 
					(regno07*8) + 
					(regno08*9) + 
					(regno09*2) + 
					(regno10*3) + 
					(regno11*4) + 
					(regno12*5);
		
		int checkValue = sum % 11;
		checkValue = 11 - checkValue;
		checkValue = checkValue % 10;
		if(regno13 == checkValue){
			return "정상적인 주민번호입니다.";
		}else{
			return "사용할수없는 주민번호입니다.";
		}
	}
}