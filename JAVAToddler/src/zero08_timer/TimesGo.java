package zero08_timer;

import java.util.*;

// 정기적 또는 비정기적 실행을위한 타겟 객체로서 TimerTask를 상속받아 구현됨.
public class TimesGo extends TimerTask{
	
	public void run(){
		printDays();
	}
	
	private void printDays(){
		Calendar cal=Calendar.getInstance();
		System.out.print("Year: "+cal.get(Calendar.YEAR));
		System.out.print("/Month: "+(cal.get(Calendar.MONTH)+1));
		System.out.print("/Day: "+cal.get(Calendar.DATE));
		System.out.print("/Hour: "+cal.get(Calendar.HOUR));
		System.out.print("/Minute: "+cal.get(Calendar.MINUTE));
		System.out.println("/Second: "+cal.get(Calendar.SECOND));
	}
}
