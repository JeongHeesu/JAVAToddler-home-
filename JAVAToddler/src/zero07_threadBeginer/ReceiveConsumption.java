package zero07_threadBeginer;

import java.io.InputStream;

public class ReceiveConsumption extends ConsumeData {
	private InputStream input;
	
	public ReceiveConsumption(InputStream is) {
		super(is);
		this.input = is;
	}

	@Override
	public boolean dataConsumption() {
		int read = 0;
		boolean done = true;
		byte[] buffer = new byte[1024];
		try{
			for(;done;){
				read = input.read(buffer);
				if(read != -1){
					System.out.println("메세지 수신");
					System.out.println("읽어온값 : " + new String(buffer));
				}
				if(read == -1) done = false; 
			}
			input.close();
		}catch(Exception e){
			e.printStackTrace();
			done = false;
		}
		return done;
	}

}
