package funny;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class BeefSoundMain {
	public static float SAMPLE_RATE = 8000f;
	
	public static void sound(int hz, int msecs) throws LineUnavailableException{
		byte[] buf = new byte[1];
		AudioFormat af = new AudioFormat(SAMPLE_RATE, 8, 1, true, false);
		SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
		sdl.open(af);
		sdl.start();
		for(int i=0; i<msecs*8; i++){
			double angle = i/(SAMPLE_RATE/hz)*2.0*Math.PI;
			buf[0] = (byte)(Math.sin(angle)*110.0);
			sdl.write(buf, 0, 1);
		}
		sdl.drain();
		sdl.stop();
		sdl.close();
	}
	
	public static void main(String[] args) {
		try{
			Thread th =	new Thread(new Runnable() {
							@Override
							public void run() {
								int sCnt = 50;
								while(true){
									if(sCnt == 1000) break;
									try {
										BeefSoundMain.sound(sCnt, 50);
									} catch (LineUnavailableException e) {
										e.printStackTrace();
									}
									sCnt += 5;
								}
							}
						});
			th.start();
		}catch (Exception e) {}
	}
}
