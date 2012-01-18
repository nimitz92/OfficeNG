package officeng.mail;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
    private Session session;
    private Properties props;	
    private String user;	
    private String password;	
    private String recipients;    
    
    static {        
        //Security.addProvider(new org.apache.harmony.xnet.provider.jsse.JSSEProvider());    
    }    
    
    public MailSender() throws IOException {
        if(props==null){
            props = new Properties();            
            FileInputStream in = new FileInputStream("config/admin.properties");            
            props.load(in);            
            in.close();        
        }        
        this.user = props.getProperty("gmail.user");        
        this.password = props.getProperty("gmail.password");		
        this.recipients = props.getProperty("gmail.recipients");		        
        this.session = SessionManager.getSession(this.user, this.password);    
    }    
    
    public synchronized void sendMail(String subject, String body) throws Exception {        
        MimeMessage message = new MimeMessage(session);        
        DataHandler handler = new DataHandler(new ByteArrayDataSource(body.getBytes(), "text/plain"));        
        message.setSender(new InternetAddress(this.user));        
        message.setSubject(subject);        
        message.setDataHandler(handler);        
        if (this.recipients.indexOf(',') > 0)            
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.recipients));        
        else            
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(this.recipients));        
        Transport.send(message);    
    }
}