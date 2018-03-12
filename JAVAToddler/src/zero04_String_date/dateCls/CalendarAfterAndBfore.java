package zero04_String_date.dateCls;

import java.util.*;

public class CalendarAfterAndBfore {
	// 해당일 바로 다음 날.
	public Date afterOneDay(Date date){
		// 1970.01.01 자정 이후 해당일까지의 밀리세컨즈 타임 long으로 반환
		long dateLong = date.getTime();
		// 밀리세컨즈 + 하루
		return new Date(dateLong+1000*60*60*24);
	}
	
	// 해당일 바로 전일.
	public Date beforOneDay(Date date){
		// 1970.01.01 자정 이후 해당일까지의 밀리세컨즈 타임 long으로 반환
		long dateLong = date.getTime();
		
		return new Date(dateLong-1000*60*60*24);
	}

	// 년 월 일 입력으로 해당 일의 Date 객체 반환.
	public Date setDate(int year,int month,int day){
		Calendar cal=Calendar.getInstance();
		cal.set(year,month-1,day);  // month는 0(1월)~11(12월)
		return new Date(cal.getTimeInMillis());
	}
}
