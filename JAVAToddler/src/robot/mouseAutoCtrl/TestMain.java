package robot.mouseAutoCtrl;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class TestMain {

	private static Robot robot;
	public static void main(String[] args) {
		try {
			// Java 플랫폼 구현 테스트를 자동화 목적으로 활용 
			robot = new Robot();

			// 스크린 상 마우스 이동 및 클릭 처리
			// 화면 해상도 설정 확인 후 작업
			// 스크린 x좌표 0 y좌표 0 : 최상좌측
			// 스크린 x좌표 1920 y좌표 1080 : 최하우측
			robot.mouseMove(1920, 1080);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			// 클릭 후 1초간 딜레이
			robot.delay(1000);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
			// 0(현재의 마우스 포인터)을 기준으로 음수 위로 이동
			//                         양수 아래로 이동
			robot.mouseWheel(-100);
			
			robot.delay(2000);
			
			TestMain.mousePointerAutoMove(100, 100, 400);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	public static void mousePointerAutoMove(int startXPos, int startYPos, int endXPos){
		robot.mouseMove(startXPos, startYPos);
		for(; startXPos<endXPos; startXPos+=10){
			robot.mouseMove(startXPos, startYPos);
			robot.delay(100);
		}
	}

}
