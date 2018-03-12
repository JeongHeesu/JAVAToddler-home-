package robot.keyAutoInput;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class TestMain {
	private static int keyInput[] = { KeyEvent.VK_H,
							        KeyEvent.VK_E,
							        KeyEvent.VK_L,
							        KeyEvent.VK_L,
							        KeyEvent.VK_O,
							        KeyEvent.VK_SPACE};

	private static void notepadExecuteNInput(){
		try {
			Process notePad = Runtime.getRuntime().exec("notepad");
			
			// notepad 실행에따른 포커싱을 위한 대기 시간 설정
			Thread.sleep(1000);
			
			Robot robot = new Robot();
			// Hello글자 입력
			for(int i=0; i<keyInput.length; i++){
				robot.keyPress(keyInput[i]);
				robot.keyRelease(keyInput[i]);
				// 200 딜레이
				robot.delay(200);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		  
		  
	}
	public static void main(String[] args) {
		TestMain.notepadExecuteNInput();
	}

}
