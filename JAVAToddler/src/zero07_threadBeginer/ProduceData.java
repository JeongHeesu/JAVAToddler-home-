package zero07_threadBeginer;

import java.io.OutputStream;

public abstract class ProduceData implements Runnable {
	private OutputStream os;
	
	public ProduceData(OutputStream os) {
		this.os = os;
		Thread thread = new Thread(this);
		thread.start();
	}


	public abstract boolean dataProduction();

	@Override
	public void run() {
		while(dataProduction());
	}
}
