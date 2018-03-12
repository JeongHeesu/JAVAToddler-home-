package zero07_threadBeginer;

import java.io.IOException;
import java.io.OutputStream;

public class SendProduction extends ProduceData {
	private OutputStream output;

	public SendProduction(OutputStream os) {
		super(os);
		this.output = os;
	}

	@Override
	public boolean dataProduction() {
		String message = "produce에서 consume으로 전달 메세지";
		boolean done = true;
		while(done){
			try {
				output.write(message.getBytes());
				System.out.println("메세지 송신");
				done = false;
			} catch (IOException e) {
				e.printStackTrace();
				done = true;
			}
		}
		try {
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return done;
	}

}
