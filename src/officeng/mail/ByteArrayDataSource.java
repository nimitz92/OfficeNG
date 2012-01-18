package officeng.mail;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.DataSource;

public class ByteArrayDataSource implements DataSource 
{
    private byte[] data;
    private String type;

    public ByteArrayDataSource(byte[] data, String type) 
	{
        super();
        this.data = data;
        this.type = type;
    }

    public ByteArrayDataSource(byte[] data) 
	{
        super();
        this.data = data;
		this.type = "application/octet-stream";
    }

    public void setType(String type) 
	{
		this.type = type;
    }

    public String getContentType() 
	{
		return type;
    }

    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(data);
    }

    public String getName() 
	{
		return "ByteArrayDataSource";
	}

    public OutputStream getOutputStream() throws IOException 
	{
            throw new IOException("Not Supported");
    }
}
