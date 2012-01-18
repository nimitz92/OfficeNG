package officeng.entity;
//TODO determine setChar(), getChar()  alternative.
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import officeng.db.ConnectionManager;

public class Lists {
	public class Entity{
		public long did;
		public long entry;
		public char type;
	}
	
	public long add(long did, long entry, char type){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        long result = -1;
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("insert into `lists` (`did`, `entry`, `type`) values (?, ?, ?);");
            ps.setLong(1, did);
            ps.setLong(2, entry);
            ps.setChar(3, type);
            int res = ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs != null && rs.next()) {
              result = rs.getLong(1);
            }
        }   
        catch (Exception e){
            e.printStackTrace(System.out);
        }
        finally {
            try{
                if(rs != null)
                    rs.close();
                if(ps != null)
                    ps.close();
                if(conn != null)
                    conn.close();
            }
            catch(Exception e){
                e.printStackTrace(System.out);
            }
        }
        
        return result;
    }
    
    public boolean edit(long lid, long did, long entry, char type){
        Connection conn = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("update `lists` set `did`=?, `entry`=?, `type`=? where `lid`=?;");
            ps.setLong(1, did);
            ps.setLong(2, entry);
            ps.setChar(3, type);
            ps.setLong(4, lid);
            int res = ps.executeUpdate();
            result = res == 1;
        }   
        catch (Exception e){
            e.printStackTrace(System.out);
        }
        finally {
            try{
                if(ps != null)
                    ps.close();
                if(conn != null)
                    conn.close();
            }
            catch(Exception e){
                e.printStackTrace(System.out);
            }
        }
        
        return result;
    }
    
    public boolean remove(long lid){
        Connection conn = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("delete from `lists` where `lid`=?;");
            ps.setLong(1, lid);
            int res = ps.executeUpdate();
            result = res == 1;
        }   
        catch (Exception e){
            e.printStackTrace(System.out);
        }
        finally {
            try{
                if(ps != null)
                    ps.close();
                if(conn != null)
                    conn.close();
            }
            catch(Exception e){
                e.printStackTrace(System.out);
            }
        }
        
        return result;
    }
    
    
    public List<Lists.Entity> list(Long lid){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Lists.Entity> result = new ArrayList<Lists.Entity>();
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("select `did`, `entry`, `type` from `lists` where lid=?;");
            ps.setLong(1, lid);
            rs = ps.executeQuery();
            while(rs.next()){
                Lists.Entity e = new Lists.Entity();
                e.did = rs.getLong(1);
                e.entry = rs.getLong(2);
                e.type = rs.getChar(3);
                result.add(e);
            }
        }   
        catch (Exception e){
            e.printStackTrace(System.out);
        }
        finally {
            try{
                if(rs != null)
                    rs.close();
                if(ps != null)
                    ps.close();
                if(conn != null)
                    conn.close();
            }
            catch(Exception e){
                e.printStackTrace(System.out);
            }
        }
        
        return result;
    }
}
