package officeng.mail;

import java.io.FileInputStream;
import java.io.IOException;
import javax.mail.Session;
import java.util.Properties;

public class SessionManager 
{
    public static Properties props = null;

    public static Session getSession(String user, String password) throws IOException
	{
        if(props==null)
		{
            props = new Properties();
            FileInputStream in = new FileInputStream("config/mail.properties");
            props.load(in);
            in.close();
        }
		
        return Session.getDefaultInstance(props, new MailAuthenticator(user, password));
    }
}
