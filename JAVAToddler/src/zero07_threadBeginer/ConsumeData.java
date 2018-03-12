package zero07_threadBeginer;

import java.io.InputStream;

public abstract class ConsumeData implements Runnable {
	private InputStream is;
	
	public ConsumeData(InputStream is) {
		this.is = is;
		Thread thread = new Thread(this);
		thread.start();
	}


	public abstract boolean dataConsumption();

	@Override
	public void run() {
		while(dataConsumption());
	}
}
