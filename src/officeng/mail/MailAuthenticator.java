package officeng.mail;

import javax.mail.PasswordAuthentication;

public class MailAuthenticator extends javax.mail.Authenticator
{
	private String user;
	private String password;
	
	public MailAuthenticator(String user, String password){
		this.user = user;
		this.password = password;
	}
	
	@Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(this.user, this.password);
    }
}