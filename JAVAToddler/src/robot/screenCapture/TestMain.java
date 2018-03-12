package robot.screenCapture;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class TestMain {

	public static void screenCapture(int seconds, String filename) {
		Robot robot = null;
		try {
			robot =new Robot();
			robot.delay(seconds);
			// jpg 파일 인코딩
			OutputStream outStream = new FileOutputStream(filename);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outStream);
			
			// 스크린 width, height 정보 취득
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			System.out.format("스크린 폭 : %d | 스크린 높이 : %d ", dimension.width, dimension.height );
			
			// 스크린캡쳐 영역(폭과 높이값 설정) 지정 및 캡쳐
			encoder.encode(robot.createScreenCapture(new Rectangle(dimension)));
			outStream.close();
		}catch(AWTException e1) {
		}catch(IOException e2) {
		}
	}

	public static void main(String[] args) {
		TestMain.screenCapture(1000, "d:\\temp\\screecapture.jpg");
	}
}
