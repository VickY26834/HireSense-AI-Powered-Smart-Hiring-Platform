package hiresense.utils;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

class MyAuthenticator extends Authenticator{
	private String username,password;
	public MyAuthenticator(String username,String password) {
		this.username=username;
		this.password=password;
	}
	protected PasswordAuthentication getPasswordAuthentication() {
		PasswordAuthentication pwdAuth=new PasswordAuthentication(this.username,this.password);
		return pwdAuth;
	}
}