package zero29.javaMail.res;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuthenticator extends Authenticator {
	private String id;
    private String pw;

    public MyAuthenticator(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }
    
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(id, pw);
	}
	
}
