package zero08_timer;

import java.util.Date;
import java.util.Timer;

public class TimeTicker {
	
	public static void main(String[] args){
		// 스레드 종류인 Timer 객체를 통해 정기적 또는 비정기적 실행처리를 수행할수 있음.
		Timer demonTimer = new Timer(true);
		
		TimesGo tg = new TimesGo();
		
		// 1초후 한번 실행
		// demonTimer.schedule(tg, 1000);
		
		// 2초후 1초마다 무한 반복
		// demonTimer.schedule(tg, 2000,1000);
		
		// 현재시간 기준 3초후 1번 실행
		// demonTimer.schedule(tg, new Date(System.currentTimeMillis()+3000));

		// 현재시간 기준 3초후 1초마다 무한 반복		
		demonTimer.schedule(tg, new Date(System.currentTimeMillis()+3000), 1000);
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		demonTimer.cancel();
		
	}
}
