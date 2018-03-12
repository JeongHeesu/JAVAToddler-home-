package zero04_String_date.stringCls;

import java.io.UnsupportedEncodingException;
import java.lang.Character.UnicodeBlock;
import java.text.DecimalFormat;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	/**
	 * String 변수에 원하는 문자가 몇 개 포함되어 있는지 파악하는 함수
	 * 
	 * @param String
	 * @param String
	 * @return int
	 */
	public int charCounter(String str, String delim) {
		int indx = 0;
		int icount = 0;
		indx = str.indexOf(delim);
		for (; indx != -1; icount++) {
			str = str.substring(str.indexOf(delim) + 1, str.length());
			indx = str.indexOf(delim);
		}
		return icount;
	}

	/**
	 * String변수의 글자수를 Max값까지. 그이상 넘어갔을 경우 ..을 붙인후 반환
	 * 
	 * @param String
	 * @param int
	 * @return String
	 */
	public String sub_String(String title, int max) {
		int strlen = max;
		int bylen = 0;
		char c;
		int c_lenc = title.length();

		if (c_lenc > max - 1) {
			strlen = 0;
			for (int x = 0; bylen < (max - 1) * 2 && strlen < c_lenc; x++) {
				c = title.charAt(strlen);
				bylen++;
				strlen++;
				if (c > 255 || (c > 64 && c < 91))
					bylen++;
			}
		}

		if (c_lenc > strlen) {
			title = title.substring(0, strlen) + "..";
		}

		return title;
	}

	/**
	 * String변수의 글자수를 Max값까지. 그이상 넘어갔을 경우 String변수의 값을 붙인후 반환
	 * 
	 * @param String
	 * @param int
	 * @param String
	 * @return String
	 */
	public String sub_String(String title, int max, String type) {
		int strlen = max;
		int bylen = 0;
		char c;
		int c_lenc = title.length();
		if (c_lenc > max - 1) {
			strlen = 0;
			for (int x = 0; bylen < (max - 1) * 2 && strlen < c_lenc; x++) {
				c = title.charAt(strlen);
				bylen++;
				strlen++;
				if (c > 255 || (c > 64 && c < 91))
					bylen++;
			}
		}
		if (type == null)
			type = "..";
		if (c_lenc > strlen) {
			title = title.substring(0, strlen) + type;
		}
		return title;
	}

	/**
	 * double 변수의 값을 KB, MB단위로 문자열 만들어주기
	 * 
	 * @param double
	 * @return String
	 * @throws Exception
	 */
	public String getFileSizeForm(double size_n) throws Exception {
		DecimalFormat format_mk = new DecimalFormat("###.#");
		DecimalFormat format_b = new DecimalFormat("###");
		String size;

		if (size_n >= 1000 * 1000)
			size = format_mk.format(size_n / (1024 * 1024)) + "MB";
		else if (size_n >= 1000)
			size = format_mk.format(size_n / 1024) + "KB";
		else
			size = format_b.format(size_n) + "B";

		return size;
	}

	/**
	 * long 변수의 값을 KB 단위로 문자열로 반환
	 * 
	 * @param bytevalue
	 * @return String
	 * @throws Exception
	 */
	public String getFileSizeFormat(long bytevalue) throws Exception {
		return getFileSizeFormat(bytevalue, null);
	}

	/**
	 * long 변수의 값을 KB or MB 단위로 문자열로 반환
	 * 
	 * @param long
	 * @param String
	 * @return String
	 * @throws Exception
	 */
	public String getFileSizeFormat(long bytevalue, String module)
			throws Exception {
		if (module == null)
			module = "";
		DecimalFormat format_mk = new DecimalFormat("###.#");

		String size;

		if (module.equalsIgnoreCase("MB")) {
			size = format_mk.format((double) bytevalue / (1024 * 1024));
		} else {
			size = format_mk.format((double) bytevalue / (1024));
		}
		return size;
	}

	/**
	 * String 변수와 String 변수의 값을 비교하여 같다면 "checked" 문자열을 반환 HTML의 checkbox에 쓰임
	 * 
	 * @param String
	 * @param String
	 * @return String
	 */
	public String getCheckedOption(String dbVal, String selected) {
		String result = "";
		if (dbVal.equals(selected)) {
			result = "checked";
		}
		return result;
	}

	/**
	 * String 변수와 String 변수의 값을 비교하여 같다면 "selected" 문자열을 반환 HTML의 selectbox에 쓰임
	 * 
	 * @param String
	 * @param String
	 * @return String
	 */
	public String getSelectObjOption(String dbVal, String selected) {
		String result = "";
		if (dbVal.equals(selected)) {
			result = "selected";
		}
		return result;
	}

	/**
	 * 특정 문자열로 연결된 문자열을 분리한다
	 * 
	 * @param String
	 * @param String
	 * @return String[]
	 */
	@SuppressWarnings("null")
	public String[] splitComma(String src, String delim) {
		StringTokenizer srcToken = new StringTokenizer(src, delim);
		String[] splitList = null;

		int idx = 0;
		while (srcToken.hasMoreTokens()) {
			splitList[idx] = (srcToken.nextToken().trim());
			idx++;
		}
		return splitList;
	}

	/**
	 * 기존의 문자를 새로운 문자로 바꾸는 함수
	 * 
	 * @param String
	 * @param String
	 * @param String
	 * @return String
	 */
	public static String getReplace(String src, String oldstr, String newstr) {
		if (src == null)
			return null;

		StringBuffer dest = new StringBuffer("");
		int len = oldstr.length();
		int srclen = src.length();
		int pos = 0;
		int oldpos = 0;

		while ((pos = src.indexOf(oldstr, oldpos)) >= 0) {
			dest.append(src.substring(oldpos, pos));
			dest.append(newstr);
			oldpos = pos + len;
		}

		if (oldpos < srclen)
			dest.append(src.substring(oldpos, srclen));

		return dest.toString();
	}

	/**
	 * EDITOR가 없는 TextArea의 입력 및 수정값 중 Enter값이나 공백값을 변환
	 * 
	 * @param String
	 * @return String
	 */
	public String inputArea(String s) {
		s = getReplace(s, " ", "&nbsp;");
		String result = s.trim();
		result = getReplace(result, "'", "''");
		result = getReplace(result, "\n", "<br>");
		return result;
	}

	/**
	 * FCK EDITOR 사용시 입력 및 수정값 중 싱글 따음표 오류를 방지하기 위한 변환
	 * 
	 * @param String
	 * @return String
	 */
	public String inputFckArea(String s) {
		String result = s.trim();
		result = getReplace(s, "'", "''");
		return result;
	}

	/**
	 * EDITOR가 없는 TextArea의 수정폼에서 Enter값이나 공백값을 변환
	 * 
	 * @param String
	 * @return String
	 */
	public String outputArea(String s) {
		s = getReplace(s, "&nbsp;", " ");
		String result = s;
		result = getReplace(getReplace(result, "<br>", "\n"), "<br/>", "\n");
		return result;
	}

	/**
	 * String 변수의 값중에서 싱글따음표를 변환 TextBox 입력폼에 쓰임
	 * 
	 * @param String
	 * @return String
	 */
	public String inputText(String s) {
		String result = s.trim();
		result = getReplace(result, "'", "''");
		return result;
	}

	/**
	 * String 변수의 값중에서 싱글따음표를 변환 TextBox 수정폼에 쓰임
	 * 
	 * @param String
	 * @return String
	 */
	public String outputText(String s) {
		String result = s.trim();
		result = getReplace(result, "\"", "&quot;");
		return result;
	}

	/**
	 * String변수의 태그 및 스크립트를 없애줌
	 * 
	 * @param String
	 * @return String
	 */
	public String htmlCleaner(String s) {

		Pattern SCRIPTS = Pattern.compile(
				"<(no)?script[^>]*>.*?</(no)?script>", Pattern.DOTALL);
		Pattern STYLE = Pattern.compile("<style[^>]*>.*</style>",
				Pattern.DOTALL);
		Pattern TAGS = Pattern.compile("<(\"[^\"]*\"|\'[^\']*\'|[^\'\">])*>");
		Pattern nTAGS = Pattern.compile("<\\w+\\s+[^<]*\\s*>");
		Pattern ENTITY_REFS = Pattern.compile("&[^;]+;");
		Pattern WHITESPACE = Pattern.compile("\\s\\s+");

		Matcher m;

		m = SCRIPTS.matcher(s);
		s = m.replaceAll("");
		m = STYLE.matcher(s);
		s = m.replaceAll("");
		m = TAGS.matcher(s);
		s = m.replaceAll("");
		m = nTAGS.matcher(s);
		s = m.replaceAll("");
		m = ENTITY_REFS.matcher(s);
		s = m.replaceAll("");
		m = WHITESPACE.matcher(s);
		s = m.replaceAll(" ");

		return s;
	}

	/**
	 * 대소문자를 상관하지 않고 str 문자열에 포함된 keyword 를 찾아서 원래의 문자에 붉은색 폰트태그를 삽입한 문자열 반환
	 * Method markKeyword.
	 * 
	 * @param String
	 * @param String
	 * @return String
	 */
	public String markKeyword(String str, String keyword) {
		keyword = replace(replace(replace(keyword, "[", "\\["), ")", "\\)"),
				"(", "\\(");

		Pattern p = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(str);
		int start = 0;
		int lastEnd = 0;
		StringBuffer sbuf = new StringBuffer();
		while (m.find()) {
			start = m.start();
			sbuf.append(str.substring(lastEnd, start)).append(
					"<font color='red'>" + m.group() + "</font>");
			lastEnd = m.end();
		}
		return sbuf.append(str.substring(lastEnd)).toString();
	}

	/**
	 * Method replace 문자열에서 일정 부분을 다른 부분으로 대치하는 메소드
	 * 
	 * @param String
	 * @param String
	 * @param String
	 * @return
	 */
	public String replace(String str, String oldStr, String newStr) {

		if (str == null)
			return null;
		if (oldStr == null || newStr == null || oldStr.length() == 0)
			return str;

		int i = str.lastIndexOf(oldStr);
		if (i < 0)
			return str;

		StringBuffer sbuf = new StringBuffer(str);

		while (i >= 0) {
			sbuf.replace(i, (i + oldStr.length()), newStr);
			i = str.lastIndexOf(oldStr, i - 1);
		}
		return sbuf.toString();
	}

	/**
	 * 한글이 포함된 문자열인지 체크
	 */
	public boolean koreanCheck(String str) {
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.of(ch);
			if (UnicodeBlock.HANGUL_SYLLABLES.equals(unicodeBlock)
					|| UnicodeBlock.HANGUL_COMPATIBILITY_JAMO
							.equals(unicodeBlock)
					|| UnicodeBlock.HANGUL_JAMO.equals(unicodeBlock))
				return true;
		}
		return false;
	}

	/**
	 * 
	 문자열이 깨질때 제가 사용하던 문자열 테스트용 소스입니다. charSet(문자열); 로 호출하시면. "euc-kr",
	 * "ksc5601", "iso-8859-1", "8859_1", "ascii", "UTF-8" 등으로 변환하여 화면에 뿌려줍니다.
	 * 매개변수로 전달한 문자열이 현재 어떤 charset인지 확인하실 수 있을겁니다. 도움이 되시길 바래요.
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

	public static boolean isNumber(String str) {
		boolean result = true;
		if (str.equals("")) {
			return false;
		}
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c < 48 || c > 59) {
				return false;
			}
		}
		return result;
	}
	
	public static String moneyFormat(String targetValue){
		String convertedAmount = "0";
		if(targetValue != null && targetValue.length() != 0){
			StringBuffer buffer = new StringBuffer();
			for(int i=0; i<targetValue.length(); i++){
				int j = (targetValue.length() - (i+1)) % 3;
				if(i != (targetValue.length() - 1) && j == 0){
					buffer.append(targetValue.charAt(i));
					buffer.append(",");
				}else{
					buffer.append(targetValue.charAt(i));
				}
			}
			convertedAmount = buffer.toString();
		}
		return convertedAmount;
	}
}














