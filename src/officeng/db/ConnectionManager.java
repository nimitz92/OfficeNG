package officeng.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionManager {

    public static Properties props = null;

    public static Connection getConnection() throws SQLException, IOException, NamingException, ClassNotFoundException{
        if(props==null){
            props = new Properties();
            FileInputStream in = new FileInputStream("/officeng/conf/database.properties");
            props.load(in);
            in.close();
        }
        //System.out.print(props);
        String drivers = props.getProperty("jdbc.drivers");
        if (drivers != null) Class.forName(drivers);//System.setProperty("jdbc.drivers", drivers);
        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");
        return DriverManager.getConnection(url, username, password);
        
        /*InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/TNPDataSource");
        return ds.getConnection();*/
    }
}
