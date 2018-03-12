package zero04_String_date.dateCls;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import zero04_String_date.common.CommonUtil;


public class DateUtil {
	
	CommonUtil cut = new CommonUtil();
	private Calendar cal = Calendar.getInstance();
	private static final int START_YEAR = 1841;
	private static final int END_YEAR = 2043;
	private static final int minYear = 1980;
	private static final int maxYear = 2100;
	
	// 천간 - 한글
	@SuppressWarnings("unused")
	private static final String [] kganArray = {"갑", "을", "병", "정", "무", "기", "경", "신", "임", "계"};
	// 천간 - 한자
	@SuppressWarnings("unused")
	private static final String [] hganArray = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};

	// 지지 - 한글
	@SuppressWarnings("unused")
	private static final String [] kjiArray = {"자", "축", "인", "묘", "진", "사", "오", "미", "신", "유", "술", "해"};
	// 지지 - 한자
	@SuppressWarnings("unused")
	private static final String [] hjiArray = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};

	// 해석지지
	@SuppressWarnings("unused")
	private static final String [] kddiArray = {"쥐", "소", "호랑이", "토끼", "용", "뱀", "말", "양", "원숭이", "닭", "개", "돼지"};
	// 요일
	@SuppressWarnings("unused")
	private static final String [] kweekArray = {"일", "월", "화", "수", "목", "금", "토"};
	private static final int [] lastdayArray = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private static final int [] yearArray = {12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
	
	/**
	 * 현재 주를 Int형으로 반환
	 * @return int
	 */
	public int getWeek(){
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	/**
	 * 현재 년,월의 시작 요일을 반환
	 * @param int
	 * @param int
	 * @return int
	 */
	public int getWeek(int year, int month){
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, 1);
		return cal.get(Calendar.DAY_OF_WEEK);
	}
		
	/**
	 * 현재 날짜를 반환, 년월일 타입과, 시분초 타입을 적용후 String형으로 반환	
	 * @param String	. , - 
	 * @param String  :
	 * @return String
	 */
	public String getDateTime(String dateType, String timeType) {
		Calendar cal = Calendar.getInstance();
		
		dateType = cut.nullCheck(dateType, ".");
		timeType = cut.nullCheck(timeType, ":");
		
		String result = String.valueOf(cal.get(Calendar.YEAR)) + dateType; 
		result += String.valueOf(cal.get(Calendar.MONTH) + 1) + dateType;
		result += String.valueOf(cal.get(Calendar.DATE)) + " ";
		result += String.valueOf(cal.get(Calendar.HOUR_OF_DAY)) + timeType;
		result += String.valueOf(cal.get(Calendar.MINUTE)) + timeType;
		result += String.valueOf(cal.get(Calendar.SECOND));
		
		return result;
	}
	
	/**
	 * Date 객체를 format에 맞게 변환
	 * @param Date
	 * @param String yyyyMMdd HHmmSS
	 * @return String
	 */
	public String getDateFormat(Date date, String format) {
		if (date == null) 
			throw new NullPointerException("날짜값이 NULL 입니다.");
		if (format == null || format.equals("")) 
			throw new IllegalArgumentException("잘못된 포멧정보입니다.");
		
		String dateStr = null;
		dateStr = new SimpleDateFormat(format).format(date);		
		return dateStr;
	}
	
	/**
	 * 특정 날짜를 인자로 받아 그 일자로부터 주어일 기간만큼 추가한 날을 계산하여리턴
	 * @param  String sYear  : 년도
	 * @param  String sMonth : 월
	 * @param  String sDay   : 일
	 * @param  int    iTerm  : 기간
	 * @param  String sGuBun : 구분 - "day":일에 기간을 더함,"month":월에 기간을 더함,"year":년에 기간을 더함.
	 * @return String - 년+월+일
	 * 기타 : 기간을 빼고 싶으면 -를 붙여서 iTerm에 넣어주세요.
	 * 예 ) getCalDateStr("2000","1","1",3,"day") 결과값은 20000104
	 */
   @SuppressWarnings("static-access")
   public  String getCalDateStr(String sYearPara, String sMonthPara, String sDayPara, int iTerm, String sGuBun){
	  Calendar cd = new GregorianCalendar(Integer.parseInt(sYearPara),Integer.parseInt(sMonthPara)-1,Integer.parseInt(sDayPara));

	  String sYear  = null;
	  String sMonth = null;
	  String sDay   = null;

	  if( cut.stringCompare(sGuBun, "day") )
	  {
		 cd.add( cd.DATE, iTerm );
		 sYear  = cut.toLeadingZeroString(cd.get(cd.YEAR), 4);
		 sMonth = cut.toLeadingZeroString(cd.get(cd.MONTH)+1, 2);
		 sDay   = cut.toLeadingZeroString(cd.get(cd.DAY_OF_MONTH), 2);
	  }
	  else if( cut.stringCompare(sGuBun, "month") )
	  {
		 cd.add( cd.MONTH, iTerm );
		 sYear  = cut.toLeadingZeroString(cd.get(cd.YEAR), 4);
		 sMonth = cut.toLeadingZeroString(cd.get(cd.MONTH)+1, 2);
		 sDay   = cut.toLeadingZeroString(cd.get(cd.DAY_OF_MONTH), 2);
	  }
	  else if( cut.stringCompare(sGuBun, "year") )
	  {
		 cd.add( cd.YEAR, iTerm );
		 sYear  = cut.toLeadingZeroString(cd.get(cd.YEAR), 4);
		 sMonth = cut.toLeadingZeroString(cd.get(cd.MONTH)+1, 2);
		 sDay   = cut.toLeadingZeroString(cd.get(cd.DAY_OF_MONTH), 2);
	  }
	  return sYear+sMonth+sDay;
   }

	/**
	 * 연도값을 리턴
	 * @return int - 년
	 */
	@SuppressWarnings("static-access")
	public int getYear(){
		Calendar cd = new GregorianCalendar(Locale.KOREA);
		int year = cd.get(cd.YEAR);	
		return year;
	}

	/**
	 * 월을 리턴
	 * @return int - 월
	 */
	@SuppressWarnings("static-access")
	public int getMonth(){
		Calendar cd = new GregorianCalendar(Locale.KOREA);
		int month = cd.get(cd.MONTH) + 1;
		return month;
	}

	/**
	 * 일을 리턴
	 * @return int - 일
	 */
	@SuppressWarnings("static-access")
	public int getDay(){
		Calendar cd = new GregorianCalendar(Locale.KOREA);
		int day = cd.get(cd.DAY_OF_MONTH);
		return day;
	}

	/**
	 * 시간을 리턴
	 * @return int - 시
	 */
	@SuppressWarnings("static-access")
	public int getHour(){
		Calendar cd = new GregorianCalendar(Locale.KOREA);
		int hour = cd.get(cd.HOUR_OF_DAY);
		return hour;
	}

	/**
	 * 분을 리턴
	 * @return int - 분
	 */
	@SuppressWarnings("static-access")
	public int getMinute(){
		Calendar cd = new GregorianCalendar(Locale.KOREA);
		int minute = cd.get(cd.MINUTE);
		return minute;
	}

	/**
	 * 초를 리턴
	 * @return int - 초
	 */
	@SuppressWarnings("static-access")
	public int getSecond(){
		Calendar cd = new GregorianCalendar(Locale.KOREA);
		int second = cd.get(cd.SECOND);
		return second;
	}

	/**
	 * 밀리초를 리턴
	 * @return int - 밀리초
	 */
	@SuppressWarnings("static-access")
	public int getMilliSecond(){
		Calendar cd = new GregorianCalendar(Locale.KOREA);
		int milliSecond = cd.get(cd.MILLISECOND);
		return milliSecond;
	}


	/**
	 * 년도를 YYYY 형태로 리턴
	 * @return String - 년도(YYYY)
	 */
	@SuppressWarnings("static-access")
	public String getYearStr(){
		Calendar cd = new GregorianCalendar(Locale.KOREA);
		return cut.toLeadingZeroString(cd.get(cd.YEAR), 4);
	}

	/**
	 * 월을 MM 형태로 리턴
	 * @return String - 월(MM)
	 */
	@SuppressWarnings("static-access")
	public String getMonthStr(){
		Calendar cd = new GregorianCalendar(Locale.KOREA);
		return cut.toLeadingZeroString(cd.get(cd.MONTH) + 1, 2);
	}

	/**
	 * 일을 DD 형태로 리턴
	 * @return String - 일(DD)
	 */
	@SuppressWarnings("static-access")
	public String getDayStr(){
		Calendar cd = new GregorianCalendar(Locale.KOREA);
		return cut.toLeadingZeroString(cd.get(cd.DAY_OF_MONTH), 2);
	}

	/**
	 * 시간을 HH 형태로 리턴
	 * @return String - 시간(HH)
	 */
	@SuppressWarnings("static-access")
	public String getHourStr(){
		Calendar cd = new GregorianCalendar(Locale.KOREA);
		return cut.toLeadingZeroString(cd.get(cd.HOUR_OF_DAY), 2);
	}

	/**
	 * 분을 mm 형태로 리턴
	 * @return String - 분(mm)
	 */
	@SuppressWarnings("static-access")
	public String getMinuteStr(){
		Calendar cd = new GregorianCalendar(Locale.KOREA);
		return cut.toLeadingZeroString(cd.get(cd.MINUTE), 2);
	}

	/**
	 * 초를 ss 형태로 리턴
	 * @return String - 초(ss)
	 */
	@SuppressWarnings("static-access")
	public String getSecondStr(){
		Calendar cd = new GregorianCalendar(Locale.KOREA);
		return cut.toLeadingZeroString(cd.get(cd.SECOND), 2);
	}

	/**
	 * 밀리초를 sss 형태로 리턴
	 * @return String - 밀리초(sss)
	 */
	@SuppressWarnings("static-access")
	public String getMilliSecondStr(){
		Calendar cd = new GregorianCalendar(Locale.KOREA);
		return cut.toLeadingZeroString(cd.get(cd.MILLISECOND), 3);
	}

	/**
	 * 년월일을 합쳐서 String으로 리턴하는 메소드
	 * @return String 년+월+일 값
	 */
	@SuppressWarnings("static-access")
	public String getDateStr(){
		Calendar cd = new GregorianCalendar(Locale.KOREA);
		return cut.toLeadingZeroString(cd.get(cd.YEAR), 4)+cut.toLeadingZeroString(cd.get(cd.MONTH) + 1, 2)+cut.toLeadingZeroString(cd.get(cd.DAY_OF_MONTH), 2);
	}

	/**
	 * 시분초를 합쳐서 String으로 리턴하는 메소드
	 * @return String 시+분+초 값
	 */
	@SuppressWarnings("static-access")
	public String getTimeStr(){
		Calendar cd = new GregorianCalendar(Locale.KOREA);
		return cut.toLeadingZeroString(cd.get(cd.HOUR_OF_DAY), 2)+cut.toLeadingZeroString(cd.get(cd.MINUTE), 2)+cut.toLeadingZeroString(cd.get(cd.SECOND), 2);
	}

	/**
	 * 년월일시분초를 합쳐서 String으로 리턴하는 메소드
	 * @return String 년+월+일+시+분+초 값
	 */
	@SuppressWarnings("static-access")
	public String getDateTimeStr(){
		Calendar cd = new GregorianCalendar(Locale.KOREA);
		return cut.toLeadingZeroString(cd.get(cd.YEAR), 4)+cut.toLeadingZeroString(cd.get(cd.MONTH) + 1, 2)+cut.toLeadingZeroString(cd.get(cd.DAY_OF_MONTH), 2)+cut.toLeadingZeroString(cd.get(cd.HOUR_OF_DAY), 2)+cut.toLeadingZeroString(cd.get(cd.MINUTE), 2)+cut.toLeadingZeroString(cd.get(cd.SECOND), 2);
	}
 
	/**
	 * 년월일시분초밀리를 합쳐서 String으로 리턴하는 메소드
	 * @return String 년+월+일+시+분+초+밀리 값
	 */
	@SuppressWarnings("static-access")
	public String getDateTimeMileStr(){
		Calendar cd = new GregorianCalendar(Locale.KOREA);
		return cut.toLeadingZeroString(cd.get(cd.YEAR), 4)+cut.toLeadingZeroString(cd.get(cd.MONTH) + 1, 2)+cut.toLeadingZeroString(cd.get(cd.DAY_OF_MONTH), 2)+cut.toLeadingZeroString(cd.get(cd.HOUR_OF_DAY), 2)+cut.toLeadingZeroString(cd.get(cd.MINUTE), 2)+cut.toLeadingZeroString(cd.get(cd.SECOND), 2) +getMilliSecondStr() ;
	}
	
	/**
	 * 년월일시분초밀리를 합쳐서 String으로 리턴하는 메소드
	 * @return String 년+월+일+시+분+초+밀리 값
	 */
	@SuppressWarnings("static-access")
	public String getDateTimeMileMinusStr(int day){
		Calendar cd = new GregorianCalendar(Locale.KOREA);
		return cut.toLeadingZeroString(cd.get(cd.YEAR), 4)+cut.toLeadingZeroString(cd.get(cd.MONTH) + 1, 2)+cut.toLeadingZeroString(cd.get(cd.DAY_OF_MONTH) - day, 2)+cut.toLeadingZeroString(cd.get(cd.HOUR_OF_DAY), 2)+cut.toLeadingZeroString(cd.get(cd.MINUTE), 2)+cut.toLeadingZeroString(cd.get(cd.SECOND), 2) +getMilliSecondStr() ;
	}

	/**
	 * 해당 년,월,일을 받아 요일을 리턴하는 메소드
	 * @param String sYear : 년, String sMonth : 월, String sDay :일
	 * @return String 요일(한글)
	 */
	@SuppressWarnings("static-access")
	public  String getYoil(String sYear, String sMonth, String sDay ){
		String sYoil[] = {"일","월","화","수","목","금","토"};
		Calendar cd = new GregorianCalendar(Integer.parseInt(sYear),Integer.parseInt(sMonth)-1,Integer.parseInt(sDay));
		int myday = cd.get(cd.DAY_OF_WEEK);
		return sYoil[myday-1];
	}
 
	/**
	 * 해당 년의 특정월의 일자를 구한다.
	 * @param int iYear : 년도4자리, int iMonth : 월 1자리 또는 2자리
	 * @return int 특정월의 일자
	 * <p><B>기타:</B>
	 * <ul>
	 * <li> 윤년 날짜를 포함한다.
	 * <li> 메소드 내에서 자리수체크 및 일자체크는 하지 않는다.
	 * <li> 1월 ~ 9월 은 1자리로 입력, 10월 ~ 12월 까지는 2자리로 입력한다.
	 * </ul>
	*/
	public  int getMonthDate(int iYear, int iMonth){
		  int iCalender[][] = new int[2][12];
	
		  // 평년
		  iCalender[0][0]  = 31;
		  iCalender[0][1]  = 28;
		  iCalender[0][2]  = 31;
		  iCalender[0][3]  = 30;
		  iCalender[0][4]  = 31;
		  iCalender[0][5]  = 30;
		  iCalender[0][6]  = 31;
		  iCalender[0][7]  = 31;
		  iCalender[0][8]  = 30;
		  iCalender[0][9]  = 31;
		  iCalender[0][10] = 30;
		  iCalender[0][11] = 31;
	
		  // 윤년
		  iCalender[1][0]  = 31;
		  iCalender[1][1]  = 29;
		  iCalender[1][2]  = 31;
		  iCalender[1][3]  = 30;
		  iCalender[1][4]  = 31;
		  iCalender[1][5]  = 30;
		  iCalender[1][6]  = 31;
		  iCalender[1][7]  = 31;
		  iCalender[1][8]  = 30;
		  iCalender[1][9]  = 31;
		  iCalender[1][10] = 30;
		  iCalender[1][11] = 31;
	
		  // 윤년이면
		  if(0 == iYear%4)
		  {
			 return iCalender[1][iMonth - 1];
		  }
		  else
		  {
			 return iCalender[0][iMonth - 1];
		  }
	 }
 
	/**
	 * 년월일시분초밀리초를 합쳐서 16자리의 String으로 리턴하는 메소드
	 * @return String 년+월+일+시+분+초+밀리초(2자리만) 값
	 * <p><B>기타:</B>
	 * <ul>
	 * </ul>
	 */
	public String getDateTimeStr2(){
		return getDateStr()+getTimeStr()+cut.toSubString(getMilliSecondStr(),0,2);
	}

	/**
	 * 시분초밀리초를 합쳐서 8자리의 String으로 리턴하는 메소드
	 * @return String 시+분+초+밀리초(2자리만) 값
	 * <p><B>기타:</B>
	 * <ul>
	 * </ul>
	 */
	public String getTimeStr2(){
		return getTimeStr()+cut.toSubString(getMilliSecondStr(),0,2);
	}
 
	/**
	 * 날짜를 YYYY/MM/DD 형식으로 리턴하는 메소드
	 * @param String str - YYYYMMDD형식의 날짜
	 * @return String YYYY/MM/DD 형식의 날짜
	 * <p><B>기타:</B>
	 * <ul>
	 * <ul>
	 */
	public  String getFormatDate(String str){
		if(str == null || "".equals(str)){
			return null;
		}else{
			if( cut.getLength(str) > 7 ){
				return str.substring(0, 4)+"/"+str.substring(4, 6)+"/"+str.substring(6, 8);
			}else{
				return str;
			}
		}
	}
 
	/**
	 * Calendar객체를 String 포맷으로 리턴하는 메소드
	 * @param Calendar
	 * @param String YYYYMMDD, YYYY-MM-DD
	 * @return String
	 */
	public String formatCalendar(Calendar cal, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());
	} 
 
	/**
	 * date1 과 date2의 값을 포맷에 맞게 비교하여 int형으로 반환 메소드
	 * @param Date 20090101
	 * @param Date 20090202
	 * @param String YYYYMMDD
	 * @return int
	 */
	public int compareDate(String date1, String date2, String formatString) {
		Calendar c1 = stringToCalendar(date1, formatString);
		Calendar c2 = stringToCalendar(date2, formatString);
		return compareDate(c1, c2);
	}

	/**
	 * 위의 메소드 참조
	 * @param Calendar
	 * @param Calendar
	 * @return int
	 */
	public int compareDate(Calendar c1, Calendar c2) {
		int value = 0;
		if(c1.before(c2)) {
			value = -1;
		} else if(c1.after(c2)) {
			value = 1;
		} else if(c1.equals(c2)) {
			value = 0;
		}
		return value;
	}
	
	/**
	 * 위의 메소드 참조
	 * @param String
	 * @param String
	 * @return Calendar
	 */
	public Calendar stringToCalendar(String dateString, String formatString) {
		SimpleDateFormat format = new SimpleDateFormat(formatString);
		Date date = format.parse(dateString, new ParsePosition(0));
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	/**
	 * @param int
	 * @return Boolean
	 */
	public boolean isLeapYear(int year) {
		return ((year % 100 != 0 && year % 4 == 0) || year % 400 == 0);
	}
		
	
	
		
	/**
	 * 날짜값이 RegExp에 맞는지 검사 
	 * 검사결과 true : 날짜값에 해당하는 Calendar 객체
	 * 검사결과 false : 현재 날짜값에 해당하는 Calendar 객체
	 * @param regexp - 정규식 패턴문자열
	 * @param dateString - 날짜값
	 */
	public  Calendar getCreateCalendar(String regexp, String dateString) {
		if (regexp == null || regexp.trim().equals("") || regexp.trim().length() < 1)
			return null;
		
		if (dateString == null || dateString.trim().equals("") || dateString.trim().length() < 1)
			return null;

		Pattern p = Pattern.compile(regexp);
		Matcher m = p.matcher(dateString);
		
		try {
			if (m.matches()) {
				String[] dateArr = dateString.split(".");
				
				Calendar cal = Calendar.getInstance();
				cal.set(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]) - 1, Integer.parseInt(dateArr[2]));
				
				return cal;
			} else {
				return Calendar.getInstance();
			}
		} catch(NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 0000.00.00형태의 문자열값을 Calendar객체로 변환
	 * @param date
	 * @return
	 */
	public  Calendar getStringToCalendar(String date) {
		if (date == null || date.equals("")) {
			//return Calendar.getInstance();
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(4, 6)) - 1, Integer.parseInt(date.substring(6, 8)));
		} catch(NumberFormatException e) {
			e.printStackTrace();
			calendar = null;
		}
		
		return calendar;
	}
	
	
	/**
	 * 해당 년월의 최대 날 수를 반환
	 * @param year
	 * @param month
	 * @return lastDay
	 */
	public  int getLastDay(String year, String month) {
		if (year == null || year.equals("")
				|| month == null || month.equals("")) {
			throw new IllegalArgumentException("잘못된 년/월 정보입니다.");
		}
		
		int nYear = Integer.parseInt(year);
		int nMonth = Integer.parseInt(month);
		
		return getLastDay(nYear, nMonth);
	}
	
	/**
	 * 해당 년월의 최대 날 수를 반환
	 * @param year
	 * @param month
	 * @return
	 */
	public  int getLastDay(int year, int month) {
		Calendar cal = Calendar.getInstance();
		
		if (!isAvailableYear(year)) {
			year = cal.get(Calendar.YEAR);
		}
		
		if (!isAvailableMonth(month)) {
			month = cal.get(Calendar.MONTH) + 1;
		}
		
		cal.set(year, month - 1, 1);
		
		int lastDay = cal.getActualMaximum(Calendar.DATE);
		
		return lastDay;
	}
	
	/**
	 * 해당년월의 시작요일을 반환
	 * @param year
	 * @param month
	 * @return
	 */
	public  int getDayOfWeek(String year, String month) {
		if (year == null || year.equals("")
				|| month == null || month.equals("")) {
			throw new IllegalArgumentException("잘못된 년/월 정보입니다.");
		}
		
		int nYear = Integer.parseInt(year);
		int nMonth = Integer.parseInt(month);
		
		return getDayOfWeek(nYear, nMonth);
	}
	/**
	 * 해당년월의 시작요일을 반환
	 * @param year
	 * @param month
	 * @return
	 */
	public  int getDayOfWeek(int year, int month) {
		
		Calendar cal = Calendar.getInstance();

		if (!isAvailableYear(year)) {
			year = cal.get(Calendar.YEAR);
		}
		
		if (!isAvailableMonth(month)) {
			month = cal.get(Calendar.MONTH) + 1;
		}
		
		cal.set(year , month - 1 , 1);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		
		return dayOfWeek;
	}
	
	/**
	 * 오늘(현재) 날짜를 반환
	 * @return
	 */
	public  int getToday() {
		Calendar cal = Calendar.getInstance();
		
		return cal.get(Calendar.DATE);
	}
	
	/**
	 * 해당년월의 주의 수를 반환
	 * @param year
	 * @param month
	 * @return
	 */
	public  int getWeekOfMonth(String year, String month) {
		if (year == null || year.equals("")
				|| month == null || month.equals("")) {
			throw new IllegalArgumentException("잘못된 년/월 정보입니다.");
		}
		
		int nYear = Integer.parseInt(year);
		int nMonth = Integer.parseInt(month);
		
		return getWeekOfMonth(nYear, nMonth);
	}
	
	/**
	 * 해당년월의 주의 수를 반환
	 * @param year
	 * @param month
	 * @return
	 */
	public  int getWeekOfMonth(int year, int month) { 
		Calendar cal = Calendar.getInstance();
		
		if (!isAvailableYear(year)) {
			year = cal.get(Calendar.YEAR);
		}
		
		if (!isAvailableMonth(month)) {
			month = cal.get(Calendar.MONTH) + 1;
		}

		cal.set(Calendar.YEAR , year); 
		cal.set(Calendar.MONTH , month - 1); 
		cal.set(Calendar.DATE , getLastDay(year,month));
		
		int weekOfMonth = cal.get(Calendar.WEEK_OF_MONTH);
		
		return weekOfMonth;
	}
	
	/**
	 * 해당년월의 시작날짜를 반환(yyyy-MM-dd 형식)
	 * @param year
	 * @param month
	 * @return
	 */
	public  String getStartDateOfMonth(String year, String month) {
		if (year == null || year.equals("")
				|| month == null || month.equals("")) {
			throw new IllegalArgumentException("잘못된 년/월 정보입니다.");
		}
		
		int nYear = Integer.parseInt(year);
		int nMonth = Integer.parseInt(month);
		
		return getStartDateOfMonth(nYear, nMonth);
	}
	
	/**
	 * 해당년월의 시작날짜를 반환(yyyy.MM.dd 형식)
	 * @param year
	 * @param month
	 * @return
	 */
	public  String getStartDateOfMonth(int year, int month) {
		String startDate = null;
		
		Calendar cal = Calendar.getInstance();
		
		if (!isAvailableYear(year)) {
			year = cal.get(Calendar.YEAR);
		}
		
		if (!isAvailableMonth(month)) {
			month = cal.get(Calendar.MONTH) + 1;
		}
		
		
		String yearStr	= String.valueOf(year); 
		String monthStr = (month < 10) ? "0" + month : "" + month; 
		String dayStr	= "01";
		
		startDate = yearStr + "." + monthStr + "." + dayStr;
		
		return startDate;
	}
	
	/**
	 * 해당년월의 끝날짜를 반환(yyyy.MM.dd 형식)
	 * @param year
	 * @param month
	 * @return
	 */
	public  String getEndDateOfMonth(String year, String month) {
		if (year == null || year.equals("")
				|| month == null || month.equals("")) {
			throw new IllegalArgumentException("잘못된 년/월 정보입니다.");
		}
		
		int nYear = Integer.parseInt(year);
		int nMonth = Integer.parseInt(month);
		
		return getEndDateOfMonth(nYear, nMonth);
	}
	
	/**
	 * 해당년월의 끝날짜를 반환(yyyy.MM.dd 형식)
	 * @param year
	 * @param month
	 * @return
	 */
	public  String getEndDateOfMonth(int year, int month) {
		String endDate = null;
		
		Calendar cal = Calendar.getInstance();
		
		if (!isAvailableYear(year)) {
			year = cal.get(Calendar.YEAR);
		}
		
		if (!isAvailableMonth(month)) {
			month = cal.get(Calendar.MONTH) + 1;
		}
		
		String yearStr	= String.valueOf(year); 
		String monthStr = (month < 10) ? "0" + month : "" + month;
		int lastDay = getLastDay(year, month);
		String dayStr 	= (lastDay < 10) ? "0" + lastDay : "" + lastDay; 
		
		endDate = yearStr + "." + monthStr + "." + dayStr;
		
		return endDate;
	}
	
	/**
	 * 해당 Calendar 객체의 날짜정보를 반환
	 * @param cal
	 * @return
	 */
	public  int getDayOfMonth(Calendar cal) {
		
		if (cal == null) {
			throw new IllegalArgumentException("잘못된 Calendar 객채 정보입니다.");
		}
		
		int dayOfMonth = 0;
		
		dayOfMonth = cal.get(Calendar.DATE);
		
		return dayOfMonth;
	}
	/**
	 * 해당 Calendar 객체의 달 정보를 반환
	 * @param cal
	 * @return
	 */
	public  int getMonth(Calendar cal) {
		if (cal == null) {
			throw new IllegalArgumentException("잘못된 Calendar 객채 정보입니다.");
		}
		
		int month = cal.get(Calendar.MONTH);
		
		return month;
	}
	
	public  int getYear(Calendar cal) {
		if (cal == null) {
			throw new IllegalArgumentException("잘못된 Calendar 객채 정보입니다.");
		}
		
		int year = cal.get(Calendar.YEAR);
		
		return year;
	}
	
	/**
	 * 현재의 년도를 반환
	 * @return
	 */
	public  int getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		
		return cal.get(Calendar.YEAR);
	}
	
	/**
	 * 년정보가 정의된 범위 안에서 유효한지를 체크
	 * @param year
	 * @return
	 */
	public  boolean isAvailableYear(int year) {

		if (year < minYear || year > maxYear) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 년정보가 정의된 범위 안에서 유효한지를 체크
	 * @param year
	 * @return
	 */
	public  boolean isAvailableYear(String year) {
		if (year == null || year.equals("")) {
			return false;
		}
		
		int nYear = Integer.parseInt(year); 
		
		if (nYear < minYear || nYear > maxYear) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 월정보가 정의된 범위 안에서 유효한지를 체크
	 * @param month
	 * @return
	 */
	public  boolean isAvailableMonth(int month) {

		if (month < 1 || month > 12) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 월정보가 정의된 범위 안에서 유효한지를 체크
	 * @param month
	 * @return
	 */
	public  boolean isAvailableMonth(String month) {
		if (month == null || month.equals("")) {
			return false;
		}
		
		int nMonth = Integer.parseInt(month); 
		
		if (nMonth < 1 || nMonth > 12) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 현재의 월을 반환
	 * @return
	 */
	public  int getCurrentMonth() {
		Calendar cal = Calendar.getInstance();
		
		return cal.get(Calendar.MONTH) + 1;
	}
	
	/**
	 * 현재날짜의 요일 반환
	 * @return
	 */
	public   String getWeekKor() {
		String[] dayOfWeek={"일","월","화","수","목","금","토"};
		Calendar cal = Calendar.getInstance();
		int week = cal.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek[week-1];
    }
	
	/**
	 * 특정일의 날짜 요일
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public  String getDayWeek(int year, int month, int day){
		String[] dayOfWeek={"일","월","화","수","목","금","토"};
		
		Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH, day);
		
		int week = cal.get(Calendar.DAY_OF_WEEK);		
		return dayOfWeek[week-1];
	}
	
	/**
	 * 날짜값을 날짜형식에 맞춘다.
	 * @param year
	 * @param month
	 * @param day
	 * @param gubun 날짜구분값
	 * @return
	 */
	public  String getDate(int year, int month, int day, String gubun) {
		String date = "";
		
		String yearStr	= String.valueOf(year); 
		String monthStr = (month < 10) ? "0" + month : "" + month;
		String dayStr 	= (day < 10) ? "0" + day : "" + day; 
		
		date = yearStr + gubun + monthStr + gubun + dayStr;
		
		return date;
	}
	
	/**
	 * 날짜의 요일 반환
	 * @param date
	 * @return
	 */
	public  int getWeek(int year, int month, int day){

		java.util.Calendar cal = java.util.Calendar.getInstance();
		
		cal.set(year,month-1,day);
		
		return cal.get(java.util.Calendar.DAY_OF_WEEK);
	}
	
	/**
	 * 날짜의 요일 반환
	 * @param date
	 * @return
	 */
	public  int getWeek(String date){
		
		int year = 0;
		int month = 0;
		int day = 0;
		
		StringTokenizer tok = new StringTokenizer(date , ".");	
		if (tok.hasMoreTokens()) year = Integer.parseInt(tok.nextToken());
		if (tok.hasMoreTokens()) month = Integer.parseInt(tok.nextToken());
		if (tok.hasMoreTokens()) day = Integer.parseInt(tok.nextToken());
		
		Calendar cal = Calendar.getInstance ();
		
		cal.set(year,month-1,day);
		
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	/**
	 * 날짜로부터 전 후 날짜 구하기
	 * @param mode 년, 월, 일
	 * @param addMargin 몇일
	 * @param dtformat 데이터포맷
	 * @param num 1일때는 전날짜
	 * @param year 특정일 년
	 * @param month 특정일 월
	 * @param date 특정일 일
	 * @return
	 */
	public  String selectAfterDate(int mode, int addMargin, String dtformat, int num,
			int year, int month, int date){
		
		java.util.Calendar cal = java.util.Calendar.getInstance();
		java.util.Date day;
		
		String strDay = "";
		
		if(num == 1){
			addMargin = -addMargin;
		}		
		
		 // 년
		if(mode == 1){			
		   cal.set(java.util.Calendar.YEAR, year);
		   cal.set(java.util.Calendar.MONTH, month - 1);
		   cal.set(java.util.Calendar.DATE, date );
		   cal.add(java.util.Calendar.YEAR, addMargin);
		   day  = cal.getTime();		      
		   java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(dtformat);
		   strDay = format.format(day);			
		} else if(mode == 2) {
			// 월
		   cal.set(java.util.Calendar.YEAR, year);
		   cal.set(java.util.Calendar.MONTH, month - 1);
		   cal.set(java.util.Calendar.DATE, date );
		   cal.add(java.util.Calendar.MONTH, addMargin);
		   day  = cal.getTime();		      
		   java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(dtformat);
		   strDay = format.format(day);			
		} else if(mode == 3){
			 // 일
			   cal.set(java.util.Calendar.YEAR, year);
			   cal.set(java.util.Calendar.MONTH, month - 1);
			   cal.set(java.util.Calendar.DATE, date );
			   cal.add(java.util.Calendar.DATE, addMargin);
			   day  = cal.getTime();			      
			   java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(dtformat);
			   strDay = format.format(day);		
		}
		
		return strDay;		
	}
	
	/**
	 * 특정일로 부터 전후 날짜(스트링타입으로 날짜 넘어올겨우)
	 * @param mode
	 * @param addMargin
	 * @param dtformat
	 * @param num
	 * @param date
	 * @return
	 */
	public  String selectAfterDate(int mode, int addMargin, String dtformat, int num,
			String date){
		
		java.util.Calendar cal = java.util.Calendar.getInstance();
		java.util.Date day_;
		
		StringTokenizer tok = new StringTokenizer(date , ".");				
		
		int year = 0;
		int month = 0;
		int day = 0;
		
		if (tok.hasMoreTokens()) year = Integer.parseInt(tok.nextToken());
		if (tok.hasMoreTokens()) month = Integer.parseInt(tok.nextToken());
		if (tok.hasMoreTokens()) day = Integer.parseInt(tok.nextToken());

		String strDay = "";
		
		if(num == 1){
			addMargin = -addMargin;
		}		
		
		 // 년
		if(mode == 1){			
		   cal.set(java.util.Calendar.YEAR, year);
		   cal.set(java.util.Calendar.MONTH, month - 1);
		   cal.set(java.util.Calendar.DATE, day );
		   cal.add(java.util.Calendar.YEAR, addMargin);
		   day_  = cal.getTime();		      
		   java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(dtformat);
		   strDay = format.format(day_);			
		} else if(mode == 2) {
			// 월
		   cal.set(java.util.Calendar.YEAR, year);
		   cal.set(java.util.Calendar.MONTH, month - 1);
		   cal.set(java.util.Calendar.DATE, day );
		   cal.add(java.util.Calendar.MONTH, addMargin);
		   day_  = cal.getTime();		      
		   java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(dtformat);
		   strDay = format.format(day_);			
		} else if(mode == 3){
			 // 일
			   cal.set(java.util.Calendar.YEAR, year);
			   cal.set(java.util.Calendar.MONTH, month - 1);
			   cal.set(java.util.Calendar.DATE, day );
			   cal.add(java.util.Calendar.DATE, addMargin);
			   day_  = cal.getTime();			      
			   java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(dtformat);
			   strDay = format.format(day_);		
		}
		
		return strDay;		
	}
	
	/**
	 * 특정일의 주 시작일(일요일이 주 시작일경우)
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public  String getWeekStart(int year, int month, int day){		
		Calendar cal = Calendar.getInstance();
		
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH, day);
		
		int week = cal.get(Calendar.DAY_OF_WEEK);

		int[] margin = new int[]{0, 1, 2, 3, 4, 5, 6, 7};

		cal.set(year,month-1,day-margin[week]+1);
		year = cal.get(Calendar.YEAR); 
		month = cal.get(Calendar.MONTH)+1; 
		day = cal.get(Calendar.DATE); 
		
		java.util.Date date  = cal.getTime();
		
		String dtformat = "yyyy'.'MM'.'dd";
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(dtformat);		

		return format.format(date);	
	}
	
	/**
	 * 특정일의 주 종료일
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public  String getWeekEnd(int year, int month, int day){		
		Calendar cal = Calendar.getInstance();
		
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH, day);
		
		int week = cal.get(Calendar.DAY_OF_WEEK);

		int[] margin = new int[]{6, 5, 4, 3, 2, 1, 0, -1};
		
		cal.set(year,month-1,day+margin[week]+1); 
		year = cal.get(Calendar.YEAR); 
		month = cal.get(Calendar.MONTH)+1; 
		day = cal.get(Calendar.DATE); 
		
		java.util.Date date  = cal.getTime();
		
		String dtformat = "yyyy'.'MM'.'dd";
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(dtformat);		
		
		return format.format(date);	
	}
	
	/**
	 * 현재날짜를 데이터포맷을 맞춰서 반환
	 * @param dtformat
	 * @return
	 */
	public  String getToday(String dtformat){
		
		Calendar cal = Calendar.getInstance();
		cal.get(Calendar.DATE);
		
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(dtformat);
		
		java.util.Date date  = cal.getTime();
		
		return format.format(date);
		
	}
	
	/**
	 * 두날짜 사이의 일수를 리턴
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public  int getDiffDayCount(String fromDate, String toDate){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try{
			return (int)((sdf.parse(toDate).getTime() - sdf.parse(fromDate).getTime()) / 1000 / 60 / 60 /24);
		}catch (Exception e){
			return 0;
		}
			
	}
	
	/**
	  * 시작일부터 종료일까지 사이의 날짜를 배열에 담아 리턴
	  * ( 시작일과 종료일을 모두 포함한다 )
	  * @param fromDate yyyyMMdd 형식의 시작일
	  * @param toDate yyyyMMdd 형식의 종료일
	  * @return yyyyMMdd 형식의 날짜가 담긴 배열
	  */
	 public  String[] getDiffDays(String fromDate, String toDate) {
		 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		
		try {
			cal.setTime(sdf.parse(fromDate));
		} catch (Exception e) {	}
		
		int count = getDiffDayCount(fromDate, toDate);
		cal.add(Calendar.DATE, -1);
		
		ArrayList list = new ArrayList();
		
		for (int i = 0; i <= count; i++) {
			cal.add(Calendar.DATE, 1);
			list.add(sdf.format(cal.getTime()));
		}
		
		String[] result = new String[list.size()];
		list.toArray(result);

	  	return result;
	  
	 }
	 
	 /**
	  * 두날짜 사이의 개월 수
	  * @param startDate yyyy.MM.dd 형식
	  * @param endDate yyyy.MM.dd 형식
	  * @return
	  */
	 public  int getMonthCount(String startDate, String endDate){
		 int start_i = Integer.parseInt(startDate.substring(0,4)+startDate.substring(5,7)); //시작년월
		 int end_i = Integer.parseInt(endDate.substring(0,4)+endDate.substring(5,7)); //종료년월
		 System.out.println("start_i:"+start_i);
		 System.out.println("end_i:"+end_i);
		 
		 
		 return 10;
	 }
	 
	 /**
	  * 두날짜 사이의 주 수
	  * @param startDate yyyy.MM.dd 형식
	  * @param endDate yyyy.MM.dd 형식
	  * @return
	  */
	 public  int getDayCount(String startDate, String endDate, String format) throws java.text.ParseException {		 
		 java.text.SimpleDateFormat formatter =
	        new java.text.SimpleDateFormat (format, java.util.Locale.KOREA);
	        java.util.Date d1 = check(startDate, format);
	        java.util.Date d2 = check(endDate, format);
	
	        long duration = d2.getTime() - d1.getTime();
		 
		 return (int)( duration/(1000 * 60 * 60 * 24) );
	 }
	 
	 /**
	  * 두날짜 사이의 주 수
	  * @param startDate yyyy.MM.dd 형식
	  * @param endDate yyyy.MM.dd 형식
	  * @return
	  */
	 public  int getWeekCount(String startDate, String endDate, String format, String mode) throws java.text.ParseException {
		 int weekCount = 0;
		 int dayCount = 0;
		 
		 dayCount = getDayCount(startDate, endDate, format);
		 
		 int week_cnt = dayCount / 7;
	     int week_cnt2 = dayCount % 7;	     
	     int day = getWeek(startDate);
		 
	     if(day == 2){ // 시작일이 월요일일때
	    		if(dayCount <= 7) weekCount = 1; // 총일수가 7보다 작으면 1
	    		else if(week_cnt2 == 0) weekCount = week_cnt; // 나머지가 0 일때는 week_cnt
	    		else weekCount = week_cnt + 1; // 나머지가 0이 아닐때는 week_cnt+1
	    	} else if(day == 3){ // 시작일이 화요일일때
	    		if(dayCount <= 6) weekCount = 1; // 총일수가 6보다 작으면 1
	    		else weekCount = week_cnt + 1; // 나머지는 week_cnt+1
	    	} else if(day == 4){ // 시작일이 수요일일때
	    		if(dayCount <= 5) weekCount = 1; // 총일수가 5보다 작으면 1
	    		else if(week_cnt2 >= 6) weekCount = week_cnt + 2;
	    		else weekCount = week_cnt + 1; // 나머지는 week_cnt+1
	    	} else if(day == 5){ // 시작일이 목요일일때
	    		if(dayCount <= 4) weekCount = 1; // 총일수가 4보다 작으면 1
	    		else if(week_cnt == 0) weekCount = week_cnt + 2;
	    		else if(week_cnt2 >= 5) weekCount = week_cnt + 2;
	    		else weekCount = week_cnt + 1; // 나머지는 week_cnt+1
	    	} else if(day == 6){ // 시작일이 금요일일때
	    		if(dayCount <= 3) weekCount = 1; // 총일수가 3보다 작으면 1
	    		else if(week_cnt2 >= 4) weekCount = week_cnt + 2;
	    		else weekCount = week_cnt + 1; // 나머지는 week_cnt+1
	    	} else if(day == 7){ // 시작일이 토요일일때
	    		if(dayCount <= 2) weekCount = 1; // 총일수가 2보다 작으면 1
	    		else if(week_cnt2 >= 3) weekCount = week_cnt + 2;
	    		else weekCount = week_cnt + 1; // 나머지는 week_cnt+1
	    	} else if(day == 1){ // 시작일이 일요일일때
	    		if(dayCount <= 1) weekCount = 1; // 총일수가 1보다 작으면 1
	    		else if(week_cnt2 >= 2) weekCount = week_cnt + 2;
	    		else weekCount = week_cnt + 1; // 나머지는 week_cnt+1
	    	}
	    	
	    	if(mode.equals("C") || mode.equals("D")){
	    		if(day != 2) weekCount = weekCount - 1;
	    	}
	     
		 return weekCount;
		 
	 }
	 
	 /**
	  * 날짜 포맷으로
	  * @param s
	  * @param format
	  * @return
	  * @throws java.text.ParseException
	  */
	 public  java.util.Date check(String s, String format) throws java.text.ParseException {
		 
		  if ( s == null )
			  throw new java.text.ParseException("date string to check is null", 0);
		  if ( format == null )
			  throw new java.text.ParseException("format string to check date is null", 0);

		  java.text.SimpleDateFormat formatter =
		            new java.text.SimpleDateFormat (format, java.util.Locale.KOREA);
		  java.util.Date date = null;
		  try {
			  date = formatter.parse(s);
		  }
		  catch(java.text.ParseException e) {
	            throw new java.text.ParseException(" wrong date:\"" + s +
	            "\" with format \"" + format + "\"", 0);
		  }

		  if ( ! formatter.format(date).equals(s) )
			  throw new java.text.ParseException(
					  "Out of bound date:\"" + s + "\" with format \"" + format + "\"",
					  0
		  );		  
		  return date;
	 }
	 
	 /**
	  * 주간 캘린더 개발시 기준일의 일요일을 찾아 캘린더 객체로 반환
	  * @param dateStr - 20100101
	  * @param dateFormat - yyyy-MM-dd
	  * @return Calendar
	  */
	public Calendar weekStartDate(String dateStr, String dateFormat) {
		
		// 기준일
		Calendar rday = new GregorianCalendar();
		rday.set(
			Integer.parseInt(dateStr.substring(0, 4)), 
			Integer.parseInt(dateStr.substring(4, 6))-1, 
			Integer.parseInt(dateStr.substring(6, 8))
		);
		
		SimpleDateFormat sdFormat = new SimpleDateFormat (dateFormat);
		// 시작 요일 (일요일)
		Calendar sday = (Calendar)rday.clone();
		int _m = rday.get(Calendar.DAY_OF_WEEK)-1;
		sday.add(Calendar.DAY_OF_MONTH, _m*-1);		
		
		return sday;
	}
	
	 /**
	  * 주간 캘린더 개발시 기준일의 토요일을 찾아 캘린더 객체로 반환
	  * @param dateStr - 20100101
	  * @param dateFormat - yyyy-MM-dd
	  * @return Calendar
	  */
	public Calendar weekEndDate(String dateStr, String dateFormat) {

		// 기준일
		Calendar rday = new GregorianCalendar();
		rday.set(
			Integer.parseInt(dateStr.substring(0, 4)), 
			Integer.parseInt(dateStr.substring(4, 6))-1, 
			Integer.parseInt(dateStr.substring(6, 8))
		);
		
		SimpleDateFormat sdFormat = new SimpleDateFormat (dateFormat);
		// 마지막 요일 (토요일)
		Calendar eday = (Calendar)rday.clone();
		int _m2 = 7 - rday.get(Calendar.DAY_OF_WEEK);
		eday.add(Calendar.DAY_OF_MONTH, _m2);
		
		return eday;
	}
		 
}